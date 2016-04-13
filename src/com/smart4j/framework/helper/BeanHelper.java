package com.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smart4j.framework.util.ReflectionUtil;

/**
* @ClassName: BeanHelper
* @Description: Bean������
* @author BEE 
* @date 2016-3-28 ����11:43:12
 */
public final class BeanHelper {

	/**
	 * ����Beanӳ�䣨���ڴ��Bean����Beanʵ����ӳ���ϵ��
	 */
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	static{
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		//System.out.println("beanClassSet size ---------- >  " + beanClassSet.size());
		for(Class<?> cls : beanClassSet){
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
			System.out.println("ʵ������ ---------- > " + cls + " ��ʵ��Ϊ ------------ > " +obj);
			//System.out.println("BEAN_MAP size ---------- >  " + BEAN_MAP.size());
		}
	}
	
	/**
	 * ��ȡBeanӳ��
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * ��ȡBeanʵ��
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	
}
