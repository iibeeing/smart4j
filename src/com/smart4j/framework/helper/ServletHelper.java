package com.smart4j.framework.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@ClassName: ServletHelper
@Description: Servelt������
@author BEE 
@date 2016-4-7 ����9:15:27
 */

public final class ServletHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	/**
	 * ��ÿ���̶߳�����һ��ServletHelperʵ��
	 */
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	/**
	@Description: ��ʼ��
	@param @param request
	@param @param response    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:20:58 
	@version 1.0
	@return void    ��������
	 */
	public static void init(HttpServletRequest request,HttpServletResponse response){
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
	}
	
	/**
	@Description: ����
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:21:27 
	@version 1.0
	@return void    ��������
	 */
	public static void destory(){
		SERVLET_HELPER_HOLDER.remove();
	}
	
	/**
	@Description: ��ȡRequest����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:22:08 
	@version 1.0
	@return HttpServletRequest    ��������
	 */
	private static HttpServletRequest getRequest(){
		return SERVLET_HELPER_HOLDER.get().request;
	}
	
	/**
	@Description: ��ȡResponse����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:25:46 
	@version 1.0
	@return HttpServletResponse    ��������
	 */
	private static HttpServletResponse getResponse(){
		return SERVLET_HELPER_HOLDER.get().response;
	}
	
	/**
	@Description: ��ȡSession����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:25:22 
	@version 1.0
	@return HttpSession    ��������
	 */
	private static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	@Description: ��ȡServletContext����
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:25:05 
	@version 1.0
	@return ServletContext    ��������
	 */
	private static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	
	/**
	@Description: �����Է���Request��
	@param @param key
	@param @param value    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:28:54 
	@version 1.0
	@return void    ��������
	 */
	public static void setRequestAttribute(String key,Object value){
		getRequest().setAttribute(key, value);
	}
	
	/**
	@Description: ��Request�л�ȡ����
	@param @param key
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:31:10 
	@version 1.0
	@return T    ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key){
		return (T)  getRequest().getAttribute(key);
	}
	
	/**
	@Description: ��Request���Ƴ�����
	@param @param key    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:32:05 
	@version 1.0
	@return void    ��������
	 */
	public static void remoteRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	/**
	@Description: �����ض�����Ӧ
	@param @param location    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:35:53 
	@version 1.0
	@return void    ��������
	 */
	public static void sendRedirect(String location){
		try{
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		}catch (Exception e) {
			LOGGER.error("redirect failure",e);
		}
	}
	
	/**
	@Description: �����Է���Session��
	@param @param key
	@param @param value    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:37:02 
	@version 1.0
	@return void    ��������
	 */
	public static void setSessionAttribute(String key,Object value){
		getSession().setAttribute(key, value);
	}
	
	/**
	@Description: ��Session�л�ȡ����
	@param @param key
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:39:47 
	@version 1.0
	@return T    ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key){
		return (T) getRequest().getSession().getAttribute(key);
	}
	
	/**
	@Description: ��Session���Ƴ�����
	@param @param key    �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:41:14 
	@version 1.0
	@return void    ��������
	 */
	public static void removeSessionAttribute(String key){
		getRequest().getSession().removeAttribute(key);
	}
	
	/**
	@Description: ��SessionʧЧ
	@param     �趨�ļ�
	@date ����ʱ�䣺2016-4-7 ����9:41:54 
	@version 1.0
	@return void    ��������
	 */
	public static void InvalidateSession(){
		getRequest().getSession().invalidate();
	}
}
