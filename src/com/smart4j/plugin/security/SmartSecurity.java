package com.smart4j.plugin.security;

import java.util.Set;

public interface SmartSecurity {

	/**
	@Description: �����û�����ȡ����
	@param @param username
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����3:54:24 
	@version 1.0
	@return String    ��������
	 */
	String getPassword(String username);
	
	/**
	@Description: �����û�����ȡ��ɫ������
	@param @param username �û���
	@param @return    ��ɫ������
	@date ����ʱ�䣺2016-4-7 ����3:55:21 
	@version 1.0
	@return Set<String>    ��������
	 */
	Set<String> getRoleNameSet(String username);
	
	/**
	@Description: ���ݽ�ɫ����ȡȨ��������
	@param @param roleName ��ɫ��
	@param @return    Ȩ��������
	@date ����ʱ�䣺2016-4-7 ����3:57:02 
	@version 1.0
	@return Set<String>    ��������
	 */
	Set<String> getPermissionNameSet(String roleName);
}
