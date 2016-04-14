package com.smart4j.plugin.security.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.smart4j.plugin.security.SecurityConstant;
import com.smart4j.plugin.security.SmartSecurity;
import com.smart4j.plugin.security.password.Md5CredentialMatcher;

import sun.security.provider.MD4;

/**
@ClassName: SmartCustomRealm
@Description: ����Smart���Զ���Realm����Ҫʵ��SmartSecurity�ӿڣ�
@author BEE 
@date 2016-4-11 ����2:04:40
 */
public class SmartCustomRealm extends AuthorizingRealm {

	private final SmartSecurity smartSecurity;

	public SmartCustomRealm(SmartSecurity smartSecurity){
		this.smartSecurity = smartSecurity;
		super.setName(SecurityConstant.REALMS_CUSTOM);
		super.setCredentialsMatcher(new Md5CredentialMatcher());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthenticationException{
		if(principals == null){
			throw new AuthorizationException("parameter principals is null");
		}
		//��ȡ����֤�û����û���
		String username = (String)super.getAvailablePrincipal(principals);
		//ͨ��SmartSecurity�ӿڲ������û�����ȡ��ɫ������
		Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);
		//ͨ��SmartSecurity�ӿڲ����ݽ�ɫ����ȡ�����Ӧ��Ȩ��������
		Set<String> permissionNameSet = new HashSet<String>();
		if(roleNameSet != null && roleNameSet.size() > 0){
			for(String roleName : roleNameSet){
				Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
				permissionNameSet.addAll(currentPermissionNameSet);
			}
		}
		//����ɫ��������Ȩ�������Ϸ���AuthorizationInfo�����У����ں�������Ȩ����
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleNameSet);
		authorizationInfo.setStringPermissions(permissionNameSet);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if(token == null){
			throw new AuthenticationException("parameter token is null");
		}
		//ͨ��AuthenticationToken�����ȡ�ӱ����ύ�������û���
		String username = ((UsernamePasswordToken) token).getUsername();
		//ͨ��SmartSecurity�ӿڲ������û�����ȡ���ݿ��д�ŵ�����
		String password = smartSecurity.getPassword(username);
		//���û������������AuthenticationInfo�����У����ں�������֤����
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
		authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
		authenticationInfo.setCredentials(password);
		return authenticationInfo;
	}

}
