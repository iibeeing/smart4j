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
@Description: 处理系统请求
@author BEE 
@date 2016-4-7 下午4:40:34
 */
@Controller
public class SystemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
	
	/**
	@Description: 进入首页界面
	@param @return    设定文件
	@date 创建时间：2016-4-7 下午4:41:58 
	@version 1.0
	@return View    返回类型
	 */
	@Action("GET:/")
	public View index(){
		return new View("index.jsp");
	}
	
	/**
	@Description: 进入登录界面
	@param @return    设定文件
	@date 创建时间：2016-4-7 下午4:42:28 
	@version 1.0
	@return View    返回类型
	 */
	@Action("GET:/login")
	public View login(){
		return new View("login.jsp");
	}
	
	/**
	@Description: 提交登录表单
	@param @return    设定文件
	@date 创建时间：2016-4-7 下午4:43:10 
	@version 1.0
	@return View    返回类型
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
	@Description: 提交注销请求
	@param @return    设定文件
	@date 创建时间：2016-4-7 下午4:46:25 
	@version 1.0
	@return View    返回类型
	 */
	@Action("GET:/logout")
	public View logout(){
		SecurityHelper.logout();
		return new View("/");
	}
}
