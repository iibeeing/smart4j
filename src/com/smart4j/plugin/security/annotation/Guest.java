package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target; 

/**
@ClassName: Guest
@Description: 判断当前用户是否未登录（包括：未认证 或 未记住，即“访客”身份）
@author BEE 
@date 2016-4-14 下午5:30:12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) 
public @interface Guest {

}
