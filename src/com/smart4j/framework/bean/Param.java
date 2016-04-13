package com.smart4j.framework.bean;

import java.util.Map;

import com.smart4j.cc.util.CastUtil;

public class Param {
	private Map<String,Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**
	 * 根据参数名获取long 型参数值
	 */
	public Long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * 获取所有字段信息
	 */
	public Map<String,Object> getMap(){
		return paramMap;
	}
}
