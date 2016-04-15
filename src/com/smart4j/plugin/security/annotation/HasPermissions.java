package com.smart4j.plugin.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: HasPermissions
 * @Description: �жϵ�ǰ�û��Ƿ�ӵ��ĳ��Ȩ��
 * @author BEE
 * @date 2016-4-14 ����5:32:47
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermissions {
	/**
	 * Ȩ���ַ���
	 */
	String value();
}
