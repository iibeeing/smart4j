package com.smart4j.plugin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smart4j.framework.ConfigHelper;

/**
@ClassName: SecurityConfig
@Description: 从配置文件中获取相关属性
@author BEE 
@date 2016-4-12 上午10:14:12
 */
public final class SecurityConfig {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	public static String getRealms(){
		return ConfigHelper.getString(SecurityConstant.REALMS);
	}
	
	public static SmartSecurity getSmartSecurity(){
		//String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
		//return (SmartSecurity) ReflectionUtil.newInstance(className);
        String className = ConfigHelper.getString(SecurityConstant.SMART_SECURITY);
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("无法从 " + SecurityConstant.SMART_SECURITY + " 配置中找到对应的类", e);
        }
        SmartSecurity smartSecurity = null;
        if (cls != null) {
            try {
                smartSecurity = (SmartSecurity) cls.newInstance();
            } catch (Exception e) {
                logger.error(SmartSecurity.class.getSimpleName() + " 实例化异常", e);
            }
        }
        return smartSecurity;
	}
	
	public static String getJdbcAuthcQuery(){
		return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
	}
	
	public static String getJdbcPermissionQuery(){
		return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSION_QUERY);
	}
	
	public static boolean isCacheable(){
		return ConfigHelper.getBoolean(SecurityConstant.CACHEABLE);
	}
	
	public static boolean isCache(){
		return ConfigHelper.getBoolean(SecurityConstant.CACHEABLE);
	}
    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }
}
