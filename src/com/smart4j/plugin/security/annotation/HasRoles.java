package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
@ClassName: HasRoles
@Description: �жϵ�ǰ�û��Ƿ�ӵ��ĳ�ֽ�ɫ
@author BEE 
@date 2016-4-14 ����5:33:26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRoles {
/**
* ��ɫ�ַ���
*/
String value();
} 
