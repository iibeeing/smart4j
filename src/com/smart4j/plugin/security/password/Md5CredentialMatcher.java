package com.smart4j.plugin.security.password;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.smart4j.framework.util.CodeUtil;

/**
@ClassName: Md5CredentialMatcher
@Description: MD5����ƥ����
@author BEE 
@date 2016-4-11 ����11:47:16
 */
public class Md5CredentialMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		//��ȡ�ӱ��ύ��������ã�����ġ���δͨ����MD5����
		String submitted = String.valueOf(((UsernamePasswordToken)token).getPassword());
		//��ȡ���ݿ��д洢�����룬��ͨ��MD5����
		String encrypted = String.valueOf(info.getCredentials());
		return CodeUtil.md5(submitted).equals(encrypted);
	}
}
