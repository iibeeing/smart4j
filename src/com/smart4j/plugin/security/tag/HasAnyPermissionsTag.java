package com.smart4j.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * @ClassName: HasAnyPermissionsTag
 * @Description: 判断当前用户是否拥有其中某一种权限（逗号分隔，表示“或”的关系）
 * @author BEE
 * @date 2016-4-14 下午4:41:19
 */
@SuppressWarnings("serial")
public class HasAnyPermissionsTag extends PermissionTag {

	private static final String PERMISSION_NAMES_DELIMETER = ",";

	@Override
	protected boolean showTagBody(String permissionNames) {
		boolean hasAnyPermission = false;
		Subject subject = getSubject();
		if (subject != null) {
			for (String permissionName : permissionNames
					.split(PERMISSION_NAMES_DELIMETER)) {
				if (subject.isPermitted(permissionName.trim())) {
					hasAnyPermission = true;
					break;
				}
			}
		}
		return hasAnyPermission;
	}

}
