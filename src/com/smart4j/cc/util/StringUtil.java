package com.smart4j.cc.util;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
	/**
	 * 字符串分隔符
	 */
	public static final String SEPARATOR = String.valueOf((char) 29);
	public static boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	/**
	@Description: 用指定标识符拆分String为String数组
	@param @param body
	@param @param separator 标识符
	@param @return    设定文件
	@date 创建时间：2016-4-6 上午10:52:34 
	@version 1.0
	@return String[]    返回类型
	 */
	public static String[] splitString(String body, String separator) {
		if(StringUtil.isNotEmpty(separator) && StringUtil.isNotEmpty(body)){
			String[] result = body.split(separator);
			return result;
		}else{
			return null;
		}
	}
}
