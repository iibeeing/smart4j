package com.smart4j.cc.util;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
	/**
	 * �ַ����ָ���
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
	@Description: ��ָ����ʶ�����StringΪString����
	@param @param body
	@param @param separator ��ʶ��
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����10:52:34 
	@version 1.0
	@return String[]    ��������
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
