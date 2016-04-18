package com.smart4j.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.smart4j.cc.util.StringUtil;
import com.smart4j.framework.bean.FormParam;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.util.ArrayUtil;
import com.smart4j.framework.util.CodeUtil;
import com.smart4j.framework.util.StreamUtil;

/**
@ClassName: RequestHelper
@Description: 请求助手类
@author BEE 
@date 2016-4-6 上午10:21:20
 */
public final class RequestHelper {

	/**
	@Description: 创建请求对象
	@param @param request
	@param @return
	@param @throws IOException    设定文件
	@date 创建时间：2016-4-6 上午10:24:27 
	@version 1.0
	@return Param    返回类型
	 */
	public static Param createParam(HttpServletRequest request)throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return new Param(formParamList);
	}
	
	private static List<FormParam> parseParameterNames(HttpServletRequest request){
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String fieldName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(fieldName);
			if(ArrayUtil.isNotEmpty(fieldValues)){
				Object fieldValue;
				if(fieldValues.length == 1){
					fieldValue = fieldValues[0];
				}else{
					StringBuilder sb = new StringBuilder("");
					for(int i=0;i<fieldValues.length;i++){
						sb.append(fieldValues[i]);
						if(i != fieldValues.length - 1){
							sb.append(StringUtil.SEPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				formParamList.add(new FormParam(fieldName,fieldValue));
			}
		}
		return formParamList;
	}
	
	private static List<FormParam> parseInputStream(HttpServletRequest requset) throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodeUtil.decodeURL(StreamUtil.getString(requset.getInputStream()));
		if(StringUtil.isNotEmpty(body)){
			String[] kvs = StringUtil.splitString(body, "&");
			if(ArrayUtil.isNotEmpty(kvs)){
				for(String kv : kvs){
					String[] array = StringUtil.splitString(kv, "=");
					if(ArrayUtil.isNotEmpty(array) && array.length == 2){
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName,fieldValue));
					}
				}
			}
		}
		return formParamList;
	}
}
