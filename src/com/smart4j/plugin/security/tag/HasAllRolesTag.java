package com.smart4j.plugin.security.tag;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

/**
@ClassName: HasAllRolesTag
@Description: 判断当前用户是否拥有其中所有的角色（逗号分割，表示“与”的关系）
@author BEE 
@date 2016-4-12 上午10:45:36
 */
@SuppressWarnings("serial")
public class HasAllRolesTag extends RoleTag {

	private static final String ROLE_NAMES_DELIMITER = ",";
	@Override
	protected boolean showTagBody(String roleNames) {
		boolean hasAllRole = false;
		Subject subject = getSubject();
		if(subject != null){
			if(subject.hasAllRoles(Arrays.asList(roleNames.split(ROLE_NAMES_DELIMITER)))){
				hasAllRole = true;
			}
		}
		return hasAllRole;
	}

}
