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
	static{
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		//System.out.println("beanClassSet size ---------- >  " + beanClassSet.size());
		for(Class<?> cls : beanClassSet){
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
			System.out.println("实例化类 ---------- > " + cls + " 的实例为 ------------ > " +obj);
			//System.out.println("BEAN_MAP size ---------- >  " + BEAN_MAP.size());
		}
	}
	
	/**
	 * 获取Bean映射
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 获取Bean实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	
}
