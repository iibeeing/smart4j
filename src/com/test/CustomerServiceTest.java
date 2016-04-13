package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import com.smart4j.cc.helper.DatabaseHelper;
import com.smart4j.cc.model.Customer;
import com.smart4j.cc.service.CustomerService;

public class CustomerServiceTest {
	private final CustomerService customerService;
	public CustomerServiceTest(){
		customerService = new CustomerService();
	}
	
	@Before
	public void init(){
		String filePath = "sql/customer_init.sql";
		DatabaseHelper.executeSqlFile(filePath);
/*		try{
			String file = "sql/customer_init.sql";
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String sql;
			while((sql=reader.readLine()) != null){
				DatabaseHelper.executeUpdate(sql);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
	}
	
	@Test
	public void getCustomerListTest() throws Exception {
		List<Customer> customerList = customerService.getCustomerList();
		System.out.println(customerList);
		Assert.assertEquals(2, customerList.size());
	}

	@Test
	public void getCustomerTest() throws Exception {
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		System.out.println(customer);
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void createCustomerTest() throws Exception {
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telephone", "13512345678");
		boolean result = customerService.createCustomer(fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void updateCustomerTest() throws Exception {
		long id = 1;
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("contact", "Eric");
		boolean result = customerService.updateCustomer(id,fieldMap);
		System.out.println(result);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		long id = 1;
		boolean result = customerService.deleteCustomer(id);
		System.out.println(result);
		Assert.assertTrue(result);
	}
}
