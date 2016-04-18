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
@Description: 基于Smart的自定义Realm（需要实现SmartSecurity接口）
@author BEE 
@date 2016-4-11 下午2:04:40
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
		//获取已认证用户的用户名
		String username = (String)super.getAvailablePrincipal(principals);
		//通过SmartSecurity接口并根据用户名获取角色名集合
		Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);
		//通过SmartSecurity接口并根据角色名获取与其对应的权限名集合
		Set<String> permissionNameSet = new HashSet<String>();
		if(roleNameSet != null && roleNameSet.size() > 0){
			for(String roleName : roleNameSet){
				Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
				permissionNameSet.addAll(currentPermissionNameSet);
			}
		}
		//将角色名集合与权限名集合放入AuthorizationInfo对象中，便于后续的授权操作
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
		//通过AuthenticationToken对象获取从表单中提交过来的用户名
		String username = ((UsernamePasswordToken) token).getUsername();
		//通过SmartSecurity接口并根据用户名获取数据库中存放的密码
		String password = smartSecurity.getPassword(username);
		//将用户名和密码放入AuthenticationInfo对象中，便于后续的认证操作
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
		authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
		authenticationInfo.setCredentials(password);
		return authenticationInfo;
	}

}
