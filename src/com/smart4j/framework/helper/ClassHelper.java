package com.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import com.smart4j.framework.ConfigHelper;
import com.smart4j.framework.util.ClassUtil;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Service;

public final class ClassHelper {
	/**
	 * �����༯�ϣ����ڴ�������ص��ࣩ
	 */
	private static final Set<Class<?>> CLASS_SET;
	static{
		String basePackage = ConfigHelper.getAppBasePackage();
		//System.out.println(" ------------- > " + basePackage);
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * ��ȡӦ�ð����µ�������
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * ��ȡӦ�ð���������Service��
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * ��ȡӦ�ð���������Controller��
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * ��ȡӦ�ð���������Bean��(������Service��Controller�ȣ�
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
