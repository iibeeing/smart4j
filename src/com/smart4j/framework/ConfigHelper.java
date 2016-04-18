package com.smart4j.framework;

import java.util.Properties;

import com.smart4j.cc.util.PropsUtil;

/**
@ClassName: ConfigHelper
@Description: 获取属性文件中的属性值
@author BEE 
@date 2016-4-13 下午2:37:31
 */
public final class ConfigHelper {

	private static final Properties CONFIG_PROPS = PropsUtil.LoadProps(ConfigConstant.CONFIG_FILE);

	/**
	 * 获取JDBC驱动
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}

	/**
	 * 获取JDBC URL
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}

	/**
	 * 获取JDBC 用户名
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}

	/**
	 * 获取JDBC 密码
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}

	/**
	 * @Description: 获取应用基础包名
	 * @param @return 设定文件
	 * @date 创建时间：2016-4-6 下午2:12:12
	 * @version 1.0
	 * @return String 返回类型
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS,
				ConfigConstant.APP_BASE_PACKAGE);
	}

	/**
	 * 获取应用JSP路径
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
	}

	/**
	 * 获取应用静态资源路径
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH,
				"/asset/");
	}

	/**
	 * 获取应用文件上传限制
	 */
	public static int getAppUploadLimit() {
		return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT,
				10);
	}

	/**
	 * 获取 String 类型的属性值
	 */
	public static String getString(String key) {
		return PropsUtil.getString(CONFIG_PROPS, key);
	}

	/**
	 * 获取 boolean 类型的属性值
	 */
	public static boolean getBoolean(String key) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key);
	}

	/**
	 * 获取 int 类型的属性值（可指定默认值）
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		return PropsUtil.getBoolean(CONFIG_PROPS, key, defaultValue);
	}
}
