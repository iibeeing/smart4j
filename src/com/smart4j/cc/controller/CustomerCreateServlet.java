package com.smart4j.cc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart4j.cc.model.Customer;
import com.smart4j.cc.service.CustomerService;

/*@WebServlet("/customer")
public class CustomerCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService customerService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		customerService = new CustomerService();
		System.out.println(customerService);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
       

}*/
