package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: HasPermissions
 * @Description: 判断当前用户是否拥有某种权限
 * @author BEE
 * @date 2016-4-14 下午5:32:47
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermissions {
	/**
	 * 权限字符串
	 */
	String value();
}
