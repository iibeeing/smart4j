package com.smart4j.framework.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.cc.util.CollectionUtil;
import com.smart4j.cc.util.StringUtil;
import com.smart4j.framework.ConfigHelper;
import com.smart4j.framework.bean.FileParam;
import com.smart4j.framework.bean.FormParam;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.util.FileUtil;
import com.smart4j.framework.util.StreamUtil;

public final class UploadHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
	

	/**
	 * Apache Commons FileUpload �ṩ��Servlet �ļ��ϴ�����
	 */
	private static ServletFileUpload servletFileUpload;
	
	/**
	@Description: ��ʼ��
	@param @param servletContext    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:51:09 
	@version 1.0
	@return void    ��������
	 */
	public static void init(ServletContext servletContext){
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));;
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if(uploadLimit != 0){
			servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024); 
		}
	}
	
	/**
	@Description: �ж������Ƿ�Ϊmultipart����
	@param @param requset
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:51:19 
	@version 1.0
	@return boolean    ��������
	 */
	public static boolean isMultipart(HttpServletRequest request){
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/**
	@Description: �����������
	@param @param request
	@param @return
	@param @throws IOException    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:50:14 
	@version 1.0
	@return Param    ��������
	 */
	public static Param createParam(HttpServletRequest request) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		try{
			Map<String,List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if(CollectionUtil.isNotEmpty(fileItemListMap)){
				for(Map.Entry<String, List<FileItem>> fileItemListEntry: fileItemListMap.entrySet()){
					String fieldName = fileItemListEntry.getKey();
					List<FileItem> fileItemList = fileItemListEntry.getValue();
					if(CollectionUtil.isNotEmpty(fileItemListMap)){
						for(FileItem fileItem : fileItemList){
							//��ͨ���ֶ�
							if(fileItem.isFormField()){
								String fieldValue = fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName, fieldValue));
							}
							//�ļ��ϴ��ֶ�
							else{
								String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(),"UTF-8"));
								if(StringUtil.isNotEmpty(fileName)){
									long fileSize = fileItem.getSize();
									String contentType = fileItem.getContentType();
									InputStream inputStream = fileItem.getInputStream();
									fileParamList.add(new FileParam(fieldName,fileName,fileSize,contentType,inputStream));
								}
							}
						}
					}
				}
			}
		}catch (FileUploadException e) {
			LOGGER.error("create param failure",e);
			throw new RuntimeException(e);
		}
		return new Param(formParamList,fileParamList);
	}
	
	/**
	@Description: �ϴ��ļ�
	@param @param basePath
	@param @param fileParam    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:48:47 
	@version 1.0
	@return void    ��������
	 */
	public static void uploadFile(String basePath,FileParam fileParam){
		try{
			if(fileParam != null){
				String filePath = basePath + fileParam.getFileName();
				FileUtil.createFile(filePath);
				InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(inputStream,outputStream);
			}
		}catch (Exception e) {
			LOGGER.error("upload file failure",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	@Description: �����ϴ��ļ�
	@param @param basePath
	@param @param fileParamList    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����9:49:04 
	@version 1.0
	@return void    ��������
	 */
	public static void uploadFile(String basePath,List<FileParam> fileParamList){
		try{
			if(CollectionUtil.isNotEmpty(fileParamList)){
				for(FileParam fileParam : fileParamList){
					uploadFile(basePath,fileParam);
				}
			}
		}catch (Exception e) {
			LOGGER.error("upload file failure",e);
			throw new RuntimeException(e);
		}
	}
}
