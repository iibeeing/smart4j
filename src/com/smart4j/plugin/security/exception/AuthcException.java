package com.smart4j.plugin.security.exception;

/**
@ClassName: AuthcException
@Description: 认证异常（当非法访问时抛出）
@author BEE 
@date 2016-4-7 下午4:37:07
 */
public class AuthcException extends Exception {

	public AuthcException(){
		super();
	}
	
	public AuthcException(String message){
		super(message);
	}
	
	public AuthcException(String message,Throwable cause){
		super(message,cause);
	}
	
	public AuthcException(Throwable cause){
		super(cause);
	}
}
