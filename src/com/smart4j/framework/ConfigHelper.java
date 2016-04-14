package com.smart4j.framework;

import java.util.Properties;

import com.smart4j.cc.util.PropsUtil;

/**
@ClassName: ConfigHelper
@Description: ��ȡ�����ļ��е�����ֵ
@author BEE 
@date 2016-4-13 ����2:37:31
 */
public final class ConfigHelper {

	private static final Properties CONFIG_PROPS = PropsUtil.LoadProps(ConfigConstant.CONFIG_FILE);

	/**
	 * ��ȡJDBC����
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}

	/**
	 * ��ȡJDBC URL
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}

	/**
	 * ��ȡJDBC �û���
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}

	/**
	 * ��ȡJDBC ����
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}

	/**
	 * @Description: ��ȡӦ�û�������
	 * @param @return �趨�ļ�
	 * @date ����ʱ�䣺2016-4-6 ����2:12:12
	 * @version 1.0
	 * @return String ��������
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS,
				ConfigConstant.APP_BASE_PACKAGE);
	}

	/**
	 * ��ȡӦ��JSP·��
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
	}

	/**
	 * ��ȡӦ�þ�̬��Դ·��
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,
				"/asset/");
	}

	/**
	 * ��ȡӦ���ļ��ϴ�����
	 */
	public static int getAppUploadLimit() {
		return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT,
				10);
	}

	/**
	 * ��ȡ String ���͵�����ֵ
	 */
	public static String getString(String key) {
		return PropsUtil.getString(CONFIG_PROPS, key);
	}

	/**
	 * ��ȡ boolean ���͵�����ֵ
	 */
	public static boolean getBoolean(String key) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key);
	}

	/**
	 * ��ȡ int ���͵�����ֵ����ָ��Ĭ��ֵ��
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key, defaultValue);
	}
}
