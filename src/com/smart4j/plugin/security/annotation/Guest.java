package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target; 

/**
@ClassName: Guest
@Description: �жϵ�ǰ�û��Ƿ�δ��¼��������δ��֤ �� δ��ס�������ÿ͡���ݣ�
@author BEE 
@date 2016-4-14 ����5:30:12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) 
public @interface Guest {

}
