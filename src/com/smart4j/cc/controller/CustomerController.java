package com.smart4j.cc.controller;

import java.util.List;
import java.util.Map;

import com.smart4j.cc.model.Customer;
import com.smart4j.cc.service.CustomerService;
import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.bean.Data;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;

@Controller
public class CustomerController {

	@Inject
	private CustomerService customerService;

	@Action("GET:/customer")
	public View index(Param param) {
		List<Customer> customerList = customerService.getCustomerList();
		return new View("customer.jsp").addModel("customerList", customerList);
	}

	@Action("get:/customer_show")
	public View show(Param param) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_show.jsp").addModel("customer", customer);
	}

	@Action("get:/customer_create")
	public View create(Param param) {
		return new View("customer_create.jsp");
	}

	@Action("post:/customer_create")
	public Data createSubmit(Param param) {
		Map<String, Object> fieldMap = param.getMap();
		boolean result = customerService.createCustomer(fieldMap);
		return new Data(result);
	}

/*	@Action("POST:/customer_edit")
	public Data edit(Param param) {
		long id = param.getLong("id");
		Map<String, Object> fieldMap = param.getMap();
		boolean result = customerService.updateCustomer(id, fieldMap);
		return new Data(result);
	}*/

	@Action("GET:/customer_edit")
	public View edit(Param param) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_show.jsp").addModel("customer", customer);
	}
	
	@Action("delete:/customer_edit")
	public Data delete(Param param) {
		long id = param.getLong("id");
		boolean result = customerService.deleteCustomer(id);
		return new Data(result);
	}
}
