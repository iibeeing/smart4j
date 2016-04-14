package com.smart4j.cc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;
/*import com.smart4j.plugin.security.SecurityHelper;
import com.smart4j.plugin.security.exception.AuthcException;*/
import com.smart4j.plugin.security.SecurityHelper;
import com.smart4j.plugin.security.exception.AuthcException;

/**
@ClassName: SystemController
@Description: ����ϵͳ����
@author BEE 
@date 2016-4-7 ����4:40:34
 */
@Controller
public class SystemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
	
	/**
	@Description: ������ҳ����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:41:58 
	@version 1.0
	@return View    ��������
	 */
	@Action("GET:/")
	public View index(){
		return new View("index.jsp");
	}
	
	/**
	@Description: �����¼����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:42:28 
	@version 1.0
	@return View    ��������
	 */
	@Action("GET:/login")
	public View login(){
		return new View("login.jsp");
	}
	
	/**
	@Description: �ύ��¼��
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:43:10 
	@version 1.0
	@return View    ��������
	 */
	@Action("POST:/login")
	public View loginSubmit(Param param){
		String username = param.getString("username");
		String password = param.getString("password");
		try{
			SecurityHelper.login(username, password);
		}catch (AuthcException e) {
			LOGGER.error("login failure",e);
			return new View("/login");
		}
		return new View("/customer");
	}
	
	/**
	@Description: �ύע������
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:46:25 
	@version 1.0
	@return View    ��������
	 */
	@Action("GET:/logout")
	public View logout(){
		SecurityHelper.logout();
		return new View("/");
	}
}
