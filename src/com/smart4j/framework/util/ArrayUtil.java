package com.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

public final class ArrayUtil {
	
	/**
	 * �ж������Ƿ�Ϊ��
	*/
	public static boolean isNotEmpty(Object[] array){
		return !ArrayUtils.isEmpty(array);
	}
	
	/**
	 * �ж������Ƿ�Ϊ��
	*/
	public static boolean isEmpty(Object[] array){
		return ArrayUtils.isEmpty(array);
	}
}
