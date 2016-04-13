package com.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: JsonUtil
 * @Description: Json ������
 * @author BEE
 * @date 2016-3-28 ����5:09:35
 */
public final class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * ��POJO תΪ JSON
	 */
	public static <T> String toJson(T obj){
		String json;
		try{
			json = OBJECT_MAPPER.writeValueAsString(obj);
		}catch (Exception e) {
			LOGGER.error("convert POJO to JSON failure",e);
			throw new RuntimeException(e);
		}
		return json;
	}
	
	/**
	 * ��JSONתΪPOJO
	 */
	public static <T> T fromJson(String json,Class<T> type){
		T pojo;
		try{
			pojo = OBJECT_MAPPER.readValue(json,type);
		}catch (Exception e) {
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
