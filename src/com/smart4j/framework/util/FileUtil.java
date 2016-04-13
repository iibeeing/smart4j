package com.smart4j.framework.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@ClassName: FileUtil
@Description: 文件操作工具类
@author BEE 
@date 2016-4-6 上午9:57:32
 */
public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	@Description: 获取真实文件名（自动去掉文件路径）
	@param @param fileName
	@param @return    设定文件
	@date 创建时间：2016-4-6 上午9:58:57 
	@version 1.0
	@return String    返回类型
	 */
	public static String getRealFileName(String fileName){
		return FilenameUtils.getName(fileName);
	}
	
	/**
	@Description: 创建文件
	@param @param filePath
	@param @return    设定文件
	@date 创建时间：2016-4-6 上午10:03:51 
	@version 1.0
	@return File    返回类型
	 */
	public static File createFile(String filePath){
		File file;
		try{
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if(!parentDir.exists()){
				FileUtils.forceMkdir(parentDir);
			}
		}catch (Exception e) {
			LOGGER.error("create file failure",e);
			throw new RuntimeException(e);
		}
		return file;
	}
}
