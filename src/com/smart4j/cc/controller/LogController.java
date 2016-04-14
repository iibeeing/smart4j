package com.smart4j.cc.controller;

import java.util.List;
import com.smart4j.cc.model.Log;
import com.smart4j.cc.service.LoggerService;
import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;

@Controller
public class LogController {
	
	@Inject
	private LoggerService loggerService;

	@Action("GET:/log")
	public View index(Param param) {
		List<Log> list = loggerService.getlist();
		return new View("log/list.jsp").addModel("list", list);
	}
	
	@Action("GET:/log")
	public View index() {
		List<Log> list = loggerService.getlist();
		return new View("log/list.jsp").addModel("list", list);
	}
}
