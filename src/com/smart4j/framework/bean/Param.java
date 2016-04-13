package com.smart4j.framework.bean;

import java.util.Map;

import com.smart4j.cc.util.CastUtil;

public class Param {
	private Map<String,Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**
	 * ���ݲ�������ȡlong �Ͳ���ֵ
	 */
	public Long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * ��ȡ�����ֶ���Ϣ
	 */
	public Map<String,Object> getMap(){
		return paramMap;
	}
}
