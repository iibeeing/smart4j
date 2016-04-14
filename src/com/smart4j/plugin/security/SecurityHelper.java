package com.smart4j.plugin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.plugin.security.exception.AuthcException;

/**
@ClassName: SecurityHelper
@Description: Security������
@author BEE 
@date 2016-4-7 ����4:34:47
 */
public final class SecurityHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

	/**
	@Description: ��¼
	@param @param username
	@param @param password
	@param @throws AuthenticationException    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:35:28 
	@version 1.0
	@return void    ��������
	 */
	public static void login(String username,String password)throws AuthcException{
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser != null){
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			try{
				currentUser.login(token);
			}catch (AuthenticationException e) {
				LOGGER.error("login failure",e);
				throw new AuthcException();
			}
		}
	}
	
	/**
	@Description: ע��
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����4:35:49 
	@version 1.0
	@return void    ��������
	 */
	public static void logout(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser != null){
			currentUser.logout();
		}
	}
}
