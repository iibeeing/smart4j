package com.smart4j.plugin.security.exception;

/**
@ClassName: AuthzException
@Description: ��Ȩ�쳣����Ȩ����Чʱ�׳���
@author BEE 
@date 2016-4-7 ����4:39:51
 */
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
