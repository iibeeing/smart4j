package com.smart4j.cc.service;

import java.util.List;
import com.smart4j.cc.helper.DatabaseHelper;
import com.smart4j.cc.model.Log;
import com.smart4j.framework.annotation.Service;

@Service
public class LoggerService {
	public List<Log> getlist() {
		String sql = "select * from log";
		return DatabaseHelper.queryEntiryList(Log.class, sql);
	}
}
