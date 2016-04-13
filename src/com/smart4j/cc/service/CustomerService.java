package com.smart4j.cc.service;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.util.PropsUtil;*/
import com.smart4j.cc.helper.DatabaseHelper;
import com.smart4j.cc.model.Customer;
import com.smart4j.framework.annotation.Service;
import com.smart4j.framework.annotation.Transaction;
import com.smart4j.framework.bean.FileParam;
import com.smart4j.framework.helper.UploadHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class CustomerService {
/*	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropsUtil.class);*/
	/*private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	static {
		Properties conf = PropsUtil.LoadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}

	}*/

	
	
	public List<Customer> getCustomerList(String keyword) {
		return null;
	}

/*	public List<Customer> getCustomerList() {
		Connection conn = null;
		List<Customer> customerList = null;
		try {
			customerList = new ArrayList<Customer>();
			String sql = "select * from customer";
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("name"));
				customer.setContact(rs.getString("contact"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setEmail(rs.getString("email"));
				customer.setRemark(rs.getString("remark"));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error("close connection failure", e);
				}
			}
		}
		return customerList;
	}*/
	public List<Customer> getCustomerList() {
		String sql = "select * from customer";
		return DatabaseHelper.queryEntiryList(Customer.class, sql);
	}

	public Customer getCustomer(long id) {
		String sql = "select * from customer where id=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		Object[] params = paramList.toArray();
		return DatabaseHelper.queryEntity(Customer.class,sql,params);
	}

	public boolean createCustomer(Map<String, Object> fieldMap) {
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}

	@Transaction
	public boolean createCustomer(Map<String,Object>fieldMap,FileParam fileParam){
		boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
		if(result){
			UploadHelper.uploadFile("/tm/upload/",fileParam);
		}
		return result;
	}
	
	
	public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}

	public boolean deleteCustomer(long id) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
