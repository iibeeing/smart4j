package com.smart4j.framework.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@ClassName: FileUtil
@Description: �ļ�����������
@author BEE 
@date 2016-4-6 ����9:57:32
 */
public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	@Description: ��ȡ��ʵ�ļ������Զ�ȥ���ļ�·����
	@param @param fileName
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:58:57 
	@version 1.0
	@return String    ��������
	 */
	public static String getRealFileName(String fileName){
		return FilenameUtils.getName(fileName);
	}
	
	/**
	@Description: �����ļ�
	@param @param filePath
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����10:03:51 
	@version 1.0
	@return File    ��������
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
