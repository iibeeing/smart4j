package com.smart4j.plugin.security.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.smart4j.framework.annotation.Aspect;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.proxy.AspectProxy;
import com.smart4j.plugin.security.annotation.User;
import com.smart4j.plugin.security.exception.AuthzException;

/**
@ClassName: AuthzAnnotationAspect
@Description: ��Ȩע�����
@author BEE 
@date 2016-4-12 ����11:00:35
 */
@Aspect(Controller.class)
public class AuthzAnnotationAspect extends AspectProxy {

	private static final Class[] ANNOTATION_CLASS_ARRAY = {User.class};
	
	@Override
	public void before(Class<?> cls, Method method, Object[] params)
			throws Throwable {
		
		//��Ŀ������Ŀ�귽���л�ȡ��Ӧ��ע��
		Annotation annotation = getAnnotation(cls,method);
		if(annotation != null){
			//�ж���Ȩע�������
			Class<?> annotationType = annotation.annotationType();
			if(annotationType.equals("User.class")){
				handleUser();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Annotation getAnnotation(Class<?> cls,Method method){
		//�������е���Ȩע��
		for(Class<? extends Annotation> annotationClass : ANNOTATION_CLASS_ARRAY){
			//�����ж�Ŀ�귽�����Ƿ������Ȩע��
			if(method.isAnnotationPresent(annotationClass)){
				return method.getAnnotation(annotationClass);
			}
			
			//Ȼ���ж�Ŀ�������Ƿ������Ȩע��
			if(cls.isAnnotationPresent(annotationClass)){
				return cls.getAnnotation(annotationClass);
			}
		}
		//��Ŀ�귽������Ŀ�����Ͼ�δ������Ȩע�⣬�򷵻ؿն���
		return null;
	}
	
	private void handleUser() throws AuthzException{
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection principals = currentUser.getPrincipals();
		if(principals ==  null || principals.isEmpty()){
			throw new AuthzException("��ǰ�û���δ��¼");
		}
	}
}
