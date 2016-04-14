package com.smart4j.cc.model;

/**
* @ClassName: Log
* @Description: 操作日志
* @author BEE 
* @date 2016-4-5 上午11:26:58
 */
public class Log {
	
	private long id;
	private String statement;
	private String result;
	private String operator;
	private String operatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
}
