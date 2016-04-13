package com.smart4j.framework.bean;

/**
* @ClassName: Data
* @Description: 返回数据对象
* @author BEE 
* @date 2016-3-28 下午3:42:39
 */
public class Data {
	/**
	*模型数据
	*/
	private Object model;

	public Data(Object model) {
		this.model = model;
	}

	public Object getModel() {
		return model;
	}
}
