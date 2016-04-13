package com.smart4j.cc.util;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
	public static boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	public static String[] splitString(String body, String separator) {
		if(StringUtil.isNotEmpty(separator) && StringUtil.isNotEmpty(body)){
			String[] result = body.split(separator);
			return result;
		}else{
			return null;
		}
	}
}
