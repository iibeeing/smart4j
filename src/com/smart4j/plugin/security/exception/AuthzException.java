package com.smart4j.plugin.security.exception;

/**
@ClassName: AuthzException
@Description: 授权异常（当权限无效时抛出）
@author BEE 
@date 2016-4-7 下午4:39:51
 */
@SuppressWarnings("serial")
public class AuthzException extends Exception {
	public AuthzException(){
		super();
	}
	
	public AuthzException(String message){
		super(message);
	}
	
	public AuthzException(String message,Throwable cause){
		super(message,cause);
	}
	
	public AuthzException(Throwable cause){
		super(cause);
	}
}
