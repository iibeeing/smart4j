package com.smart4j.plugin.security.password;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.smart4j.framework.util.CodeUtil;

/**
@ClassName: Md5CredentialMatcher
@Description: MD5密码匹配器
@author BEE 
@date 2016-4-11 上午11:47:16
 */
public class Md5CredentialMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		//获取从表单提交过来的渺茫、明文、尚未通过的MD5加密
		String submitted = String.valueOf(((UsernamePasswordToken)token).getPassword());
		//获取数据库中存储的密码，已通过MD5加密
		String encrypted = String.valueOf(info.getCredentials());
		return CodeUtil.md5(submitted).equals(encrypted);
	}
}
