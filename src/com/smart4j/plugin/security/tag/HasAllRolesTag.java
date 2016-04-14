package com.smart4j.plugin.security.tag;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

/**
@ClassName: HasAllRolesTag
@Description: �жϵ�ǰ�û��Ƿ�ӵ���������еĽ�ɫ�����ŷָ��ʾ���롱�Ĺ�ϵ��
@author BEE 
@date 2016-4-12 ����10:45:36
 */
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
