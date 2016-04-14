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
* @Description: ���渨����
* @author BEE 
* @date 2016-3-31 ����5:03:31
 */
public final class AopHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
	
	/**
	 * ��̬���ʼ��AOP���
	 */
	static{
		try{
			//�������Ŀ���༯�ϵ�ӳ���ϵMap��������ͨ�����������������
			Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
			//key-value ת�� ��Ŀ���ࣨ�����ࣩ- ������
			Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()){
				//Ŀ����
				Class<?> targetClass = targetEntry.getKey();
				//�����࣬�п���һ��Ŀ�����ж�������࣬����Ȩ�����桢��־�����
				List<Proxy> proxyList = targetEntry.getValue();
				//��̬����ĳ�����������棨������
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		}catch (Exception e) {
			LOGGER.error("aop failure",e);
		}
	}
	

	/**
	@Description: ��ȡע������aspect.value()�������࣬����aspect.value()=Controller ���Ǿ������д�ע����Controller���
	@param @param aspect
	@param @return
	@param @throws Exception    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:57:24 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		//��ʵ����Aspectע��
		Class<? extends Annotation> annotation = aspect.value();
		if(annotation != null && !annotation.equals(Aspect.class)){
			//����aspect.value()�� controller����ô���˵�Ŀ�������ע����Controller���ࣨ���еĿ������ࣩ
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
	/**
	@Description: ��ȡĿ����ʹ������ӳ���ϵ
	@param @return
	@param @throws Exception    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:48:58 
	@version 1.0
	@return Map<Class<?>,Set<Class<?>>>    ��������
	 */
	private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
		Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
		//��ʾ�̳���AspectProxy ������ܻ��Ǵ����࣬�������ˣ����Annotation��Aspect��ô���Ǵ�����
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
	 * ����ӳ���ϵ����Ŀ����ʹ�������б�֮���ӳ���ϵ(key-value ת��)
	 * ��ԭ����1���������Ӧһ�������ࣨί���ࣩ���ϱ任��1�������ࣨĿ����OR�������ࣩ
	 * ��Ӧһ�������ࣨ�����ࣩ��Ҳ�������һһ��Ӧ��ϵ����ֱ�Ӵ�Map���ҵ�
	 *							    ***********					    	A---------->1
	 * 							 	*	A��B		*							B---------->1
	 * 1������----------> 	*				*  �޸�Ϊ												------>Ŀ���� -> ������
	 * 					 		    *		  D 	*							D---------->1				------> A---->1
	 * 					 			***********															------> B---->1��2
	 *					 			***********							B---------->2				------> C---->2
	 * 					 			*		B		*							C---------->2				------> D---->1��2
	 * 2������---------->	*	C��		*  �޸�Ϊ				
	 * 					 			*		  D 	*							D---------->2
	 * 					 			***********
	 */					 
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
		for(Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
			//������
			Class<?> proxyClass = proxyEntry.getKey();
			//������
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet){
				//������ʵ��
				Proxy proxy = (Proxy) proxyClass.newInstance();
				//���Ŀ���ࣨ�����ࣩ�Ѿ������ˣ��򽫸��������ʵ��ӳ�䵽��Ŀ������
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
	@Description: �����ͨ�������
	@param @param proxyMap �������� - �����༯�ϣ�
	@param @throws Exception    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:49:22 
	@version 1.0
	@return void    ��������
	 */
	private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
		//��ͨ����������Ǽ̳�AspectProxy����ȡ����������
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass : proxyClassSet){
			//��ͨ�������������Aspect�ı�ʶ����ȡ����������
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				//��ȡAspect��ֵ������ȡ��������Ҫ���еĶ��������һ�����
				//������@Aspect(Controller.class) ��ʾ����������д�Controllerע����࣬���������ࣩ
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				//���������е��࣬�������е�Controller��
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass,targetClassSet);
			}
		}
	}
	
	/**
	@Description: ��������������
	@param @param proxyMap    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:49:45 
	@version 1.0
	@return void    ��������
	 */
	private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}
}
