package com.smart4j.framework.bean;

import java.io.InputStream;

/**
@ClassName: FileParam
@Description: 上传文件参数
@author BEE 
@date 2016-4-6 上午10:13:22
 */
public class FileParam {

	private String fieldName;
	private String fileName;
	private long fileSize;
	private String contentType;
	private InputStream inputStream;

	public FileParam(String fieldName, String fileName, long fileSize,
			String contentType, InputStream inputStream) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFileName() {
		return fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
