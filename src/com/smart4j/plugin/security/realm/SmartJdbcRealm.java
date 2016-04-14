package com.smart4j.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import com.smart4j.cc.helper.DatabaseHelper;
import com.smart4j.plugin.security.SecurityConfig;
import com.smart4j.plugin.security.password.Md5CredentialMatcher;

/**
@ClassName: SmartJdbcRealm
@Description: ����Smart��JDBC Realm����Ҫ�ṩ���smart.plugin.security.jdbc.*�����
@author BEE 
@date 2016-4-11 ����11:38:51
 */
public class SmartJdbcRealm extends JdbcRealm {

	public SmartJdbcRealm(){
		super.setDataSource(DatabaseHelper.getDataSource());
		super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
		super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
		super.setPermissionsQuery(SecurityConfig.getJdbcPermissionQuery());
		super.setPermissionsLookupEnabled(true);
		super.setCredentialsMatcher(new Md5CredentialMatcher());
	}
}
