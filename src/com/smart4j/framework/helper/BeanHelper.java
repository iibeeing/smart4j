package com.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smart4j.framework.util.ReflectionUtil;

/**
* @ClassName: BeanHelper
* @Description: Bean助手类
* @author BEE 
* @date 2016-3-28 上午11:43:12
 */
public final class BeanHelper {

	/**
	 * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	
	/**
	 * 将加载的类包实例化到BEAN_MAP中，并可以根据类名获取
	 */
	static{
		//获取该包下所有的类
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> cls : beanClassSet){
			//实例化类
			Object obj = ReflectionUtil.newInstance(cls);
			//将类名和实例化的类映射 并装入到Map中
			BEAN_MAP.put(cls, obj);
		}
	}
	
	/**
	@Description: 获取Bean映射
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:44:48 
	@version 1.0
	@return Map<Class<?>,Object>    返回类型
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	@Description: 获取Bean实例
	@param @param cls
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:46:10 
	@version 1.0
	@return T    返回类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	/**
	@Description: 设置Bean实例
	@param @param cls
	@param @param obj    设定文件
	@date 创建时间：2016-4-6 下午2:46:35 
	@version 1.0
	@return void    返回类型
	 */
	public static void setBean(Class<?> cls,Object obj){
		BEAN_MAP.put(cls, obj);
	}
}
