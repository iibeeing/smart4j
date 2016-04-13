package com.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import com.smart4j.cc.util.CollectionUtil;
import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.util.ArrayUtil;
import com.smart4j.framework.util.ReflectionUtil;

/**
* @ClassName: IocHelper
* @Description: ����Inject��ע�룩
* @author BEE 
* @date 2016-3-30 ����9:24:36
 */
public final class IocHelper {
	static{
		//��ȡ���е�Bean����Beanʵ��֮���ӳ���ϵ����� Bean Map��
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		//System.out.println("beanMap size --------- > " + beanMap.size());
		if(CollectionUtil.isNotEmpty(beanMap)){
			//����Bean Map
			for(Map.Entry<Class<?>, Object>beanEntry : beanMap.entrySet()){
				//��BeanMap �л�ȡBean����Beanʵ��
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//��ȡBean �ඨ������г�Ա���������Bean Field��
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtil.isNotEmpty(beanFields)){
					//����Bean Field
					for(Field beanField : beanFields){
						//�жϵ�ǰBean Field �Ƿ� ���� Inject ע��
						if(beanField.isAnnotationPresent(Inject.class)){
							//��Bean Map�л�ȡBean Field��Ӧ��ʵ��
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null){
								//ͨ�������ʼ��BeanField��ֵ ��ĳ����ֵ���õ���Ӧ��ʵ����������
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
