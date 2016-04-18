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
@Description: 类加载辅助类
@author BEE 
@date 2016-4-6 下午2:39:35
 */
public final class ClassHelper {
	/**
	 * 定义类集合（用于存放所记载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}

	/**
	@Description: 获取应用包名下的所有类
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:17:50 
	@version 1.0
	@return Set<Class<?>>    返回类型
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	@Description: 获取应用包名下所有注解是Service的类
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:37:59 
	@version 1.0
	@return Set<Class<?>>    返回类型
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
	@Description: 获取应用包名下所有注解是Controller的类
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:38:19 
	@version 1.0
	@return Set<Class<?>>    返回类型
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
	@Description: 获取应用包名下所有Bean类(包括：Service、Controller等）
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:38:44 
	@version 1.0
	@return Set<Class<?>>    返回类型
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}

	/**
	@Description: 获取应用包名下某父类（或接口）的所有子类（或实现类）
	@param @param superClass
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午2:39:00 
	@version 1.0
	@return Set<Class<?>>    返回类型
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			// 此Class对象所表示的类或接口是否相同，前后则关系是父与子关系或接口与被实现关系，但又不同名
			// 关系是 父亲.isAssignableFrom(子)
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	@Description: 获取应用包名下带有某注解的所有类比如获取所有注解（Annotation）是Controller的类（即所有的控制器类，就是切面类要管理的目标类）
	@param @param annotationClass
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午3:00:31 
	@version 1.0
	@return Set<Class<?>>    返回类型
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
