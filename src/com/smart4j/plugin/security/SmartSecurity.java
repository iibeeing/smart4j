package com.smart4j.plugin.security;

import java.util.Set;

public interface SmartSecurity {

	/**
	@Description: 根据用户名获取密码
	@param @param username
	@param @return    设定文件
	@date 创建时间：2016-4-7 下午3:54:24 
	@version 1.0
	@return String    返回类型
	 */
	String getPassword(String username);
	
	/**
	@Description: 根据用户名获取角色名集合
	@param @param username 用户名
	@param @return    角色名集合
	@date 创建时间：2016-4-7 下午3:55:21 
	@version 1.0
	@return Set<String>    返回类型
	 */
	Set<String> getRoleNameSet(String username);
	
	/**
	@Description: 根据角色名获取权限名集合
	@param @param roleName 角色名
	@param @return    权限名集合
	@date 创建时间：2016-4-7 下午3:57:02 
	@version 1.0
	@return Set<String>    返回类型
	 */
	Set<String> getPermissionNameSet(String roleName);
}
