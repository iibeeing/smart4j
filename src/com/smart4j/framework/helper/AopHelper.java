package com.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smart4j.framework.annotation.Aspect;
import com.smart4j.framework.annotation.Service;
import com.smart4j.framework.proxy.AspectProxy;
import com.smart4j.framework.proxy.Proxy;
import com.smart4j.framework.proxy.ProxyManager;
import com.smart4j.framework.proxy.TransactionProxy;

/**
* @ClassName: AopHelper
* @Description: 切面辅助类
* @author BEE 
* @date 2016-3-31 下午5:03:31
 */
public final class AopHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
	
	/**
	 * 静态块初始化AOP框架
	 */
	static{
		try{
			//代理类和目标类集合的映射关系Map，包含普通切面类和事务切面类
			Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
			//key-value 转换 即目标类（被切类）- 切面类
			Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()){
				//目标类
				Class<?> targetClass = targetEntry.getKey();
				//切面类，有可能一个目标类有多个切面类，比如权限切面、日志切面等
				List<Proxy> proxyList = targetEntry.getValue();
				//动态生成某个类具体的切面（代理）类
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		}catch (Exception e) {
			LOGGER.error("aop failure",e);
		}
	}
	

	/**
	@Description: 获取注解类是aspect.value()的所有类，比如aspect.value()=Controller ，那就是所有带注解是Controller类的
	@param @param aspect
	@param @return
	@param @throws Exception    设定文件
	@date 创建时间：2016-4-6 下午2:57:24 
	@version 1.0
	@return Set<Class<?>>    返回类型
	 */
	private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		//其实就是Aspect注解
		Class<? extends Annotation> annotation = aspect.value();
		if(annotation != null && !annotation.equals(Aspect.class)){
			//比如aspect.value()是 controller，那么本此的目标类就是注解是Controller的类（所有的控制器类）
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
	/**
	@Description: 获取目标类和代理类的映射关系
	@param @return
	@param @throws Exception    设定文件
	@date 创建时间：2016-4-6 下午2:48:58 
	@version 1.0
	@return Map<Class<?>,Set<Class<?>>>    返回类型
	 */
	private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
		Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
		//表示继承了AspectProxy 的类可能会是代理类，下面会过滤，如果Annotation是Aspect那么就是代理类
/*		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass : proxyClassSet){
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}*/
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}
	
	/**
	 * 根据映射关系分析目标类和代理对象列表之间的映射关系(key-value 转换)
	 * 将原来的1个切面类对应一个被切类（委托类）集合变换成1个被切类（目标类OR被代理类）
	 * 对应一个切面类（代理类），也就是添加一一对应关系可以直接从Map中找到
	 *							    ***********					    	A---------->1
	 * 							 	*	A、B		*							B---------->1
	 * 1切面类----------> 	*				*  修改为												------>目标类 -> 代理类
	 * 					 		    *		  D 	*							D---------->1				------> A---->1
	 * 					 			***********															------> B---->1、2
	 *					 			***********							B---------->2				------> C---->2
	 * 					 			*		B		*							C---------->2				------> D---->1、2
	 * 2切面类---------->	*	C、		*  修改为				
	 * 					 			*		  D 	*							D---------->2
	 * 					 			***********
	 */					 
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
		for(Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
			//切面类
			Class<?> proxyClass = proxyEntry.getKey();
			//被切类
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet){
				//切面类实例
				Proxy proxy = (Proxy) proxyClass.newInstance();
				//如果目标类（被切类）已经包含了，则将该切面类的实例映射到该目标类上
				if(targetMap.containsKey(targetClass)){
					targetMap.get(targetClass).add(proxy);
				}else{
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	}
	
	/**
	@Description: 添加普通切面代理
	@param @param proxyMap （切面类 - 被切类集合）
	@param @throws Exception    设定文件
	@date 创建时间：2016-4-6 下午2:49:22 
	@version 1.0
	@return void    返回类型
	 */
	private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		//普通切面类必须是继承AspectProxy，获取所有切面类
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass : proxyClassSet){
			//普通切面类必须是有Aspect的标识，获取所有切面类
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				//获取Aspect的值，即获取该切面类要横切的对象或者哪一类对象
				//（比如@Aspect(Controller.class) 表示该切面类横切带Controller注解的类，即控制器类）
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				//创建被横切的类，比如所有的Controller类
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass,targetClassSet);
			}
		}
	}
	
	/**
	@Description: 添加事务切面代理
	@param @param proxyMap    设定文件
	@date 创建时间：2016-4-6 下午2:49:45 
	@version 1.0
	@return void    返回类型
	 */
	private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}
}
