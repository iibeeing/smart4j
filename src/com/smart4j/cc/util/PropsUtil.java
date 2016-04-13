package com.smart4j.cc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @ClassName: PropsUtil
* @Description: �����ļ�������
* @author BEE 
* @date 2016-3-25 ����10:02:18
 */
public final class PropsUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropsUtil.class);
	
	/**
	* @Title: LoadProps
	* @Description: ���������ļ�
	* @param @param fileName
	* @param @return    �趨�ļ�
	* @return Properties    ��������
	* @throws
	 */
	public static Properties LoadProps(String fileName){
		Properties props = null;
		InputStream is = null;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is ==  null){
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(is);
		}catch (Exception e) {
			LOGGER.error("Load properties file failure",e);
		}finally{
			if(is != null){
				try{
					is.close();
				}catch (IOException e) {
					LOGGER.error("close input stream failure",e);
				}
			}
		}
		return props;
	}
	
	/**
	* @Title: getString
	* @Description: ��ȡ�ַ����ԣ�Ĭ��ֵΪ���ַ�����
	* @param @param props
	* @param @param key
	* @param @return    �趨�ļ�
	* @return String    ��������
	* @throws
	 */
	public static String getString(Properties props,String key){
		return getString(props,key,"");
	}
	
	/**
	* @Title: getString
	* @Description: ��ȡ�ַ������ԣ���ָ��Ĭ��ֵ��
	* @param @param props
	* @param @param key
	* @param @param defaultValue
	* @param @return    �趨�ļ�
	* @return String    ��������
	* @throws
	 */
	public static String getString(Properties props,String key,String defaultValue){
		String value = defaultValue;
		if(props.containsKey(key)){
			value = props.getProperty(key);
		}
		return value;
	}
	
	public static int getInt(Properties props,String key){
		return getInt(props, key,0);
	}
	
	public static int getInt(Properties props,String key,int defaultValue){
		int value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}
	
	public static boolean getBoolean(Properties props,String key){
		return getBoolean(props, key,false);
	}
	
	public static boolean getBoolean(Properties props,String key,boolean defaultValue){
		boolean value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}
}
