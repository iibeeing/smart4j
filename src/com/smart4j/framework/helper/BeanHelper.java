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
	
	/**
	 * �����ص����ʵ������BEAN_MAP�У������Ը���������ȡ
	 */
	static{
		//��ȡ�ð������е���
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> cls : beanClassSet){
			//ʵ������
			Object obj = ReflectionUtil.newInstance(cls);
			//��������ʵ��������ӳ�� ��װ�뵽Map��
			BEAN_MAP.put(cls, obj);
		}
	}
	
	/**
	@Description: ��ȡBeanӳ��
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:44:48 
	@version 1.0
	@return Map<Class<?>,Object>    ��������
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	@Description: ��ȡBeanʵ��
	@param @param cls
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:46:10 
	@version 1.0
	@return T    ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class: " + cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
	
	/**
	@Description: ����Beanʵ��
	@param @param cls
	@param @param obj    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:46:35 
	@version 1.0
	@return void    ��������
	 */
	public static void setBean(Class<?> cls,Object obj){
		BEAN_MAP.put(cls, obj);
	}
}
