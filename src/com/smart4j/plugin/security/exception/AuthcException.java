package com.smart4j.plugin.security.exception;

/**
@ClassName: AuthcException
@Description: ��֤�쳣�����Ƿ�����ʱ�׳���
@author BEE 
@date 2016-4-7 ����4:37:07
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
