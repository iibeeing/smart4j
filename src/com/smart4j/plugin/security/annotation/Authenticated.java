package com.smart4j.plugin.security.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
/**
@ClassName: Authenticated
@Description: 判断当前用户是否已认证
@author BEE 
@date 2016-4-14 下午5:31:19
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) 
public @interface Authenticated {

}
