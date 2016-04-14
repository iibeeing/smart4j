package com.smart4j.cc.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.cc.util.CollectionUtil;
import com.smart4j.cc.util.PropsUtil;

public final class DatabaseHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	//private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final QueryRunner QUERY_RUNNER;
	// private static Connection conn = null;
	//private static final ThreadLocal<Connection> CONNCETION_HOLDER = new ThreadLocal<Connection>();
	private static final ThreadLocal<Connection> CONNCETION_HOLDER;
	private static final BasicDataSource DATA_SOURCE;
	static {
		CONNCETION_HOLDER = new ThreadLocal<Connection>();
		QUERY_RUNNER = new QueryRunner();
		
		Properties conf = PropsUtil.LoadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(DRIVER);
		DATA_SOURCE.setUrl(URL);
		DATA_SOURCE.setUsername(USERNAME);
		DATA_SOURCE.setPassword(PASSWORD);
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}
	}

	public static Connection getConnection() {
		// conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Connection conn = CONNCETION_HOLDER.get();
		if (conn == null) {
			try {
				//conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			}finally{
				CONNCETION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	public static void executeSqlFile(String filePath){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try{
			String sql;
			while((sql=reader.readLine()) != null){
				executeUpdate(sql);
			}
		}catch (Exception e) {
			LOGGER.error("execute sql file failure",e);
			throw new RuntimeException(e);
		}
	}
	
	@Deprecated
	public static void closeConnection() {
		Connection conn = CONNCETION_HOLDER.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure", e);
				throw new RuntimeException(e);
			} finally{
				CONNCETION_HOLDER.remove();
			}
		}
	}

	public static <T> List<T> queryEntiryList(Class<T> entityClass, String sql,Object... params) {
		List<T> entityList;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		}
		/*finally {
			closeConnection();
		}*/
		return entityList;
	}
	
	public static <T> T queryEntity(Class<T> entityClass,String sql,Object... params){
		T entity;
		try{
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql,new BeanHandler<T>(entityClass),params);
		}catch (SQLException e) {
			LOGGER.error("query entity failure",e);
			throw new RuntimeException(e);
		}
		return entity;
	}
	
	public static int executeUpdate(String sql,Object...objects){
		int rows = 0;
		try{
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn,sql,objects);
		}catch (SQLException e) {
			LOGGER.error("execute update failure",e);
		}
/*		finally{
			closeConnection();
		}*/
		return rows;
	}
	
	/**
	* @Title: insertEntity
	* @Description: ����ʵ��
	* @param @param entityClass
	* @param @param fieldMap
	* @param @return    �趨�ļ�
	* @return boolean    ��������
	* @throws
	 */
	public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
		if(CollectionUtil.isEmpty(fieldMap)){
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		String sql = "insert into " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for(String fieldName : fieldMap.keySet()){
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		//columns �����һ��, ����)
		columns.replace(columns.lastIndexOf(", "),columns.length(),")");
		//values �����һ��, ����)
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " values " + values;
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql,params) == 1;
	}
	
	/**
	* @Title: updateEntity
	* @Description: ����ʵ��
	* @param @param entityClass
	* @param @param id
	* @param @param fieldMap
	* @param @return    �趨�ļ�
	* @return boolean    ��������
	* @throws
	 */
	public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap){
		if(CollectionUtil.isEmpty(fieldMap)){
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}
		String sql = " update " + getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder();
		for(String fieldName : fieldMap.keySet()){
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		return executeUpdate(sql,params) == 1;
	}
	
	/**
	* @Title: deleteEntity
	* @Description: ɾ��ʵ��
	* @param @param entityClass
	* @param @param id
	* @param @return    �趨�ļ�
	* @return boolean    ��������
	* @throws
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass,long id){
		String sql = " delete from " + getTableName(entityClass) + " where id=?";
		return executeUpdate(sql, id) == 1;
	}
	
	/**
	* @Title: getTableName
	* @Description: ��ȡ������ʵ��������Ҫ�����ݿ������ʵ��������ͬ��
	* @param @param entityClass
	* @param @return    �趨�ļ�
	* @return String    ��������
	* @throws
	 */
	public static String getTableName(Class<?> entityClass){
		return entityClass.getSimpleName();
	}
	
	/**
	@Description: ��������
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����11:48:32 
	@version 1.0
	@return void    ��������
	 */
	public static void beginTransaction(){
		Connection conn = getConnection();
		if(conn != null){
			try{
				conn.setAutoCommit(false);
			}catch (Exception e) {
				LOGGER.error("begin transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNCETION_HOLDER.set(conn);
			}
		}
	}
	
	/**
	@Description: �ύ����
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����11:48:46 
	@version 1.0
	@return void    ��������
	 */
	public static void commitTransaction(){
		Connection conn = getConnection();
		if(conn != null){
			try{
				conn.commit();
				conn.close();
			}catch (Exception e) {
				LOGGER.error("commit transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNCETION_HOLDER.remove();
			}
		}
	}
	
	/**
	@Description: �ع�����
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����11:48:54 
	@version 1.0
	@return void    ��������
	 */
	public static void rollbackTransaction(){
		Connection conn = getConnection();
		if(conn != null){
			try{
				conn.rollback();
				conn.close();
			}catch (Exception e) {
				LOGGER.error("rollback transaction failure",e);
				throw new RuntimeException(e);
			}finally{
				CONNCETION_HOLDER.remove();
			}
		}
	}
}
