package com.smart4j.plugin.security;

import java.util.Set;

import com.smart4j.cc.helper.DatabaseHelper;

/**
@ClassName: AppSecurity
@Description: 应用安全控制
@author BEE 
@date 2016-4-7 下午4:05:46
 */
public class AppSecurity implements SmartSecurity {

	@Override
	public String getPassword(String username) {
		String sql = "select password from user where username = ?";
		return DatabaseHelper.query(sql,username);
	}

	@Override
	public Set<String> getRoleNameSet(String username) {
		String sql = "select r.role_name from user u,user_role ur,role r " 
				+ " where u.id = ur.user_id and r.id = ur.role_id and u.usename = ?";
		return DatabaseHelper.querySet(sql,username);
	}

	@Override
	public Set<String> getPermissionNameSet(String roleName) {
		String sql = "select p.permission_name from role r,role_permission rp"
				+ " permission p where r.id=rp.role_id and p.id = rp.permission_id"
				+ " and r.role_name = ?";
		return DatabaseHelper.querySet(sql,roleName);
	}

}
