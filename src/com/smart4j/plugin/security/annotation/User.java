package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@ClassName: User
@Description: �жϵ�ǰ�û��Ƿ��ѵ�¼(����������֤���Ѽ�ס��
@author BEE 
@date 2016-4-12 ����10:58:26
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface User {

}
