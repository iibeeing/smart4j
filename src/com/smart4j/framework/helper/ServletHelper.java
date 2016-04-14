package com.smart4j.framework.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@ClassName: ServletHelper
@Description: Servelt助手类
@author BEE 
@date 2016-4-7 上午9:15:27
 */

public final class ServletHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	/**
	 * 是每个线程独自有一份ServletHelper实例
	 */
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	/**
	@Description: 初始化
	@param @param request
	@param @param response    设定文件
	@date 创建时间：2016-4-7 上午9:20:58 
	@version 1.0
	@return void    返回类型
	 */
	public static void init(HttpServletRequest request,HttpServletResponse response){
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
	}
	
	/**
	@Description: 销毁
	@param     设定文件
	@date 创建时间：2016-4-7 上午9:21:27 
	@version 1.0
	@return void    返回类型
	 */
	public static void destory(){
		SERVLET_HELPER_HOLDER.remove();
	}
	
	/**
	@Description: 获取Request对象
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:22:08 
	@version 1.0
	@return HttpServletRequest    返回类型
	 */
	private static HttpServletRequest getRequest(){
		return SERVLET_HELPER_HOLDER.get().request;
	}
	
	/**
	@Description: 获取Response对象
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:25:46 
	@version 1.0
	@return HttpServletResponse    返回类型
	 */
	private static HttpServletResponse getResponse(){
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	/**
	@Description: 获取Session对象
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:25:22 
	@version 1.0
	@return HttpSession    返回类型
	 */
	private static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	@Description: 获取ServletContext对象
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:25:05 
	@version 1.0
	@return ServletContext    返回类型
	 */
	private static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	
	/**
	@Description: 将属性放入Request中
	@param @param key
	@param @param value    设定文件
	@date 创建时间：2016-4-7 上午9:28:54 
	@version 1.0
	@return void    返回类型
	 */
	public static void setRequestAttribute(String key,Object value){
		getRequest().setAttribute(key, value);
	}
	
	/**
	@Description: 从Request中获取属性
	@param @param key
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:31:10 
	@version 1.0
	@return T    返回类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key){
		return (T)  getRequest().getAttribute(key);
	}
	
	/**
	@Description: 从Request中移除属性
	@param @param key    设定文件
	@date 创建时间：2016-4-7 上午9:32:05 
	@version 1.0
	@return void    返回类型
	 */
	public static void remoteRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	/**
	@Description: 发生重定向响应
	@param @param location    设定文件
	@date 创建时间：2016-4-7 上午9:35:53 
	@version 1.0
	@return void    返回类型
	 */
	public static void sendRedirect(String location){
		try{
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		}catch (Exception e) {
			LOGGER.error("redirect failure",e);
		}
	}
	
	/**
	@Description: 将属性放入Session中
	@param @param key
	@param @param value    设定文件
	@date 创建时间：2016-4-7 上午9:37:02 
	@version 1.0
	@return void    返回类型
	 */
	public static void setSessionAttribute(String key,Object value){
		getSession().setAttribute(key, value);
	}
	
	/**
	@Description: 从Session中获取属性
	@param @param key
	@param @return    设定文件
	@date 创建时间：2016-4-7 上午9:39:47 
	@version 1.0
	@return T    返回类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key){
		return (T) getRequest().getSession().getAttribute(key);
	}
	
	/**
	@Description: 从Session中移除属性
	@param @param key    设定文件
	@date 创建时间：2016-4-7 上午9:41:14 
	@version 1.0
	@return void    返回类型
	 */
	public static void removeSessionAttribute(String key){
		getRequest().getSession().removeAttribute(key);
	}
	
	/**
	@Description: 是Session失效
	@param     设定文件
	@date 创建时间：2016-4-7 上午9:41:54 
	@version 1.0
	@return void    返回类型
	 */
	public static void InvalidateSession(){
		getRequest().getSession().invalidate();
	}
}
