package com.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.smart4j.framework.ConfigHelper;
import com.smart4j.framework.util.ClassUtil;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Service;

/**
@ClassName: ClassHelper
@Description: ����ظ�����
@author BEE 
@date 2016-4-6 ����2:39:35
 */
public final class ClassHelper {
	/**
	 * �����༯�ϣ����ڴ�������ص��ࣩ
	 */
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}

	/**
	@Description: ��ȡӦ�ð����µ�������
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:17:50 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	@Description: ��ȡӦ�ð���������ע����Service����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:37:59 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	@Description: ��ȡӦ�ð���������ע����Controller����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:38:19 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	@Description: ��ȡӦ�ð���������Bean��(������Service��Controller�ȣ�
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:38:44 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}

	/**
	@Description: ��ȡӦ�ð�����ĳ���ࣨ��ӿڣ����������ࣨ��ʵ���ࣩ
	@param @param superClass
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����2:39:00 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			// ��Class��������ʾ�����ӿ��Ƿ���ͬ��ǰ�����ϵ�Ǹ����ӹ�ϵ��ӿ��뱻ʵ�ֹ�ϵ�����ֲ�ͬ��
			// ��ϵ�� ����.isAssignableFrom(��)
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	@Description: ��ȡӦ�ð����´���ĳע�������������ȡ����ע�⣨Annotation����Controller���ࣨ�����еĿ������࣬����������Ҫ�����Ŀ���ࣩ
	@param @param annotationClass
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����3:00:31 
	@version 1.0
	@return Set<Class<?>>    ��������
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(annotationClass)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
}
