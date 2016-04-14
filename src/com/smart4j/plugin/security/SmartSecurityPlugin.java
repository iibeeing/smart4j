package com.smart4j.plugin.security;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

/**
@ClassName: SmartSecurityPlugin
@Description: SmartSecurity插件
@author BEE 
@date 2016-4-11 上午10:40:41
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> handlesTypes,ServletContext servletContext) throws ServletException{
		//设置初始化参数
		servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");
		//注册Listener
		servletContext.addListener(EnvironmentLoaderListener.class	);
		//注册Filter
		FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("SmartSecurityFilter", SmartSecurityFilter.class);
		smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
