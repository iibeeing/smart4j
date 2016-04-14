package com.smart4j.plugin.security;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

/**
@ClassName: SmartSecurityPlugin
@Description: SmartSecurity���
@author BEE 
@date 2016-4-11 ����10:40:41
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> handlesTypes,ServletContext servletContext) throws ServletException{
		//���ó�ʼ������
		servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");
		//ע��Listener
		servletContext.addListener(EnvironmentLoaderListener.class	);
		//ע��Filter
		FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("SmartSecurityFilter", SmartSecurityFilter.class);
		smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
