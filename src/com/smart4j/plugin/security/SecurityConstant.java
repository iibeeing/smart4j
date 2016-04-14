package com.smart4j.plugin.security;

/**
@ClassName: SecurityConstant
@Description: 常量接口
@author BEE 
@date 2016-4-12 上午10:19:17
 */
public interface SecurityConstant {

	String REALMS = "smart.plugin.security.realms";
	String REALMS_JDBC = "jdbc";
	String REALMS_CUSTOM = "custom";
	
	String SMART_SECURITY = "smart.plugin.security.custom.class";
	
	String JDBC_AUTHC_QUERY = "smart.plugin.security.jdbc.authc_query";
	String JDBC_ROLES_QUERY = "smart.plugin.security.jdbc.roles_query";
	String JDBC_PERMISSION_QUERY = "smart.plugin.security.jdbc.permission_query";
	
	String CACHE = "smart.plugin.security.cache";
	
//	boolean CACHEABLE = true;
	String CACHEABLE = "smart.plugin.security.cacheable";
}
