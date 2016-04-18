package com.smart4j.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smart4j.cc.util.CastUtil;
import com.smart4j.cc.util.CollectionUtil;
import com.smart4j.cc.util.StringUtil;

public class Param {
/*	private Map<String,Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	*//**
	 * 根据参数名获取long 型参数值
	 *//*
	public Long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	*//**
	 * 获取所有字段信息
	 *//*
	public Map<String,Object> getMap(){
		return paramMap;
	}*/
	private List<FormParam> formParamList;
	private List<FileParam> fileParamList;
	
	/////////////////////////////////////////////////////////////
	private Map<String,Object> paramMap;
	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	/**
	 * 根据参数名获取long 型参数值
	 */
	public Long getLong(String name){
		//return CastUtil.castLong(paramMap.get(name));
		return CastUtil.castLong(getFieldMap().get(name));
	}
	
	/**
	 * 获取所有字段信息
	 */
	public Map<String,Object> getMap(){
		return paramMap;
	}
	/////////////////////////////////////////////////////////////
	
	public Param(List<FormParam> formParamList) {
		this.formParamList = formParamList;
	}

	public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}

	/**
	@Description: 获取请求参数映射
	@param @return    设定文件
	@date 创建时间：2016-4-6 上午10:15:03 
	@version 1.0
	@return Map<String,Object>    返回类型
	 */
	public Map<String, Object> getFieldMap() {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		if (CollectionUtil.isNotEmpty(formParamList)) {
			for (FormParam formParam : formParamList) {
				String fieldName = formParam.getFieldName();
				Object fieldValue = formParam.getFieldValue();
				if (fieldMap.containsKey(fieldName)) {
					fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
				}
				fieldMap.put(fieldName, fieldValue);
			}
		}
		return fieldMap;
	}

	/**
	 * 获取上传文件映射
	 */
	public Map<String, List<FileParam>> getFileMap() {
		Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
		if (CollectionUtil.isNotEmpty(fileParamList)) {
			for (FileParam fileParam : fileParamList) {
				String fieldName = fileParam.getFieldName();
				List<FileParam> fileParamList;
				if (fileMap.containsKey(fieldName)) {
					fileParamList = fileMap.get(fieldName);
				} else {
					fileParamList = new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName, fileParamList);
			}
		}
		return fileMap;
	}

	/**
	 * 获取所有上传文件
	 */
	public List<FileParam> getFileList(String fieldName) {
		return getFileMap().get(fieldName);
	}

	/**
	 * 获取唯一上传文件
	 */
	public FileParam getFile(String fieldName) {
		List<FileParam> fileParamList = getFileList(fieldName);
		if (CollectionUtil.isNotEmpty(fileParamList)
				&& fileParamList.size() == 1) {
			return fileParamList.get(0);
		}
		return null;
	}

	/**
	 * 验证参数是否为空
	 */

	public boolean isEmpty() {
		return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
	}

	/**
	 * 根据参数名获取String型参数值
	 */
	public String getString(String name){
		return CastUtil.castString(getFieldMap().get(name));
	}
	
	/**
	 * 根据参数名获取Double型参数值
	 */
	public double getDouble(String name){
		return CastUtil.castDouble(getFieldMap().get(name));
	}
	
	/**
	 * 根据参数名获取Long型参数值
	 */
	public long getLong2(String name){
		return CastUtil.castLong(getFieldMap().get(name));
	}
	
	/**
	 * 根据参数名获取int型参数值
	 */
	public int getInt(String name){
		return CastUtil.castInt(getFieldMap().get(name));
	}
	
	/**
	 * 根据参数名获取boolean型参数值
	 */
	public boolean getBoolean(String name){
		return CastUtil.castBoolean(getFieldMap().get(name));
	}
	/*
	 * private Map<String,Object> paramMap; public Param(Map<String, Object>
	 * paramMap) { this.paramMap = paramMap; }
	 *//**
	 * 根据参数名获取long 型参数值
	 */
	/*
	 * public Long getLong(String name){ return
	 * CastUtil.castLong(paramMap.get(name)); }
	 *//**
	 * 获取所有字段信息
	 */
	/*
	 * public Map<String,Object> getMap(){ return paramMap; }
	 *//**
	 * 验证参数是否为空
	 */
	/*
	 * public boolean isEmpty(){ return CollectionUtil.isEmpty(paramMap); }
	 */
}
