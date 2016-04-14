package com.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart4j.cc.util.StringUtil;
import com.smart4j.framework.bean.Data;
import com.smart4j.framework.bean.Handler;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;
import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ControllerHelper;
import com.smart4j.framework.helper.RequestHelper;
import com.smart4j.framework.helper.ServletHelper;
import com.smart4j.framework.helper.UploadHelper;
import com.smart4j.framework.util.ArrayUtil;
import com.smart4j.framework.util.CodeUtil;
import com.smart4j.framework.util.JsonUtil;
import com.smart4j.framework.util.ReflectionUtil;
import com.smart4j.framework.util.StreamUtil;

/**
* @ClassName: DispatcherServlet
* @Description: ����ת����
* @author BEE 
* @date 2016-3-28 ����3:45:18
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig serveltConfig) throws ServletException {
		//��ʼ�����Helper�࣬ʵ��������Bean������ע��
		HelperLoader.init();
		//��ȡServletContext ��������ע��Servlet)
		ServletContext servletContext = serveltConfig.getServletContext();
		//ע�ᴦ��JSP �� Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		String appJspPath = ConfigHelper.getAppJspPath() + "*";
		jspServlet.addMapping(appJspPath);
		//ע�ᴦ��̬��Դ��Ĭ��Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
		//��ʼ���ļ��ϴ�����
		UploadHelper.init(servletContext);
	}
	
	//@Override
	protected void dservice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���󷽷�������·�� ����ȡ�ͻ���������ʽ
		String requestMethod = request.getMethod();
		String requestPath = request.getPathInfo();
		
		//��ȡAction ������
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler != null){
			//��ȡController�༫��Beanʵ��
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			//���������������
			Map<String,Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if(StringUtil.isNotEmpty(body)){
				String[] params = StringUtil.splitString(body,"&");
				if(ArrayUtil.isNotEmpty(params)){
					for(String param : params){
						String[] array = StringUtil.splitString(param,"=");
						if(ArrayUtil.isNotEmpty(array) && array.length == 2){
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			
			Param param = new Param(paramMap);
			//����Action����
			Method actionMethod = handler.getActionMethod();
			Object result;
			//-----------------------------------------------------------------------------------------------//
			// ������� param  ����
			if(paramMap != null && paramMap.size() > 0){
				result = ReflectionUtil.invokedMethod(controllerBean, actionMethod, param);
			}else{
				result = ReflectionUtil.invokedMethod(controllerBean, actionMethod);
			}
			
			//-----------------------------------------------------------------------------------------------//
			//����Action��������ֵ
			if(result instanceof View){
				//����JSPҳ��
				View view = (View)result;
				String path = view.getPath();
				if(StringUtil.isNotEmpty(path)){
					if(path.startsWith("/")){
						response.sendRedirect(request.getContextPath() + path);
					}else{
						Map<String,Object> model = view.getModel();
						for(Map.Entry<String, Object>entry : model.entrySet()){
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
					}
				}
			}else if(result instanceof Data){
				//����JSON����
				Data data = (Data)result;
				Object model = data.getModel();
				if(model != null){
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletHelper.init(request, response);
		try{
			//��ȡ���󷽷�������·�� ����ȡ�ͻ���������ʽ
			String requestMethod = request.getMethod();
			String requestPath = request.getPathInfo();
			
			if(requestPath.equals("/favicon.ico")){
				return;
			}
			
			//��ȡAction ������
			Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
			if(handler != null){
				//��ȡController�༫��Beanʵ��
				Class<?> controllerClass = handler.getControllerClass();
				Object controllerBean = BeanHelper.getBean(controllerClass);
				
				Param param;
				if(UploadHelper.isMultipart(request)){
					param = UploadHelper.createParam(request);
				}else{
					param = RequestHelper.createParam(request);
				}
				
				Object result;
				Method actionMethod = handler.getActionMethod();
				if(param.isEmpty()){
					result = ReflectionUtil.invokedMethod(controllerBean, actionMethod);
				}else{
					result = ReflectionUtil.invokedMethod(controllerBean, actionMethod, param);
				}
				
				if(result instanceof View){
					handleViewResult((View)result, request, response);
				}else if( result instanceof Data){
					handleDataResult((Data) result,response);
				}
			}
		}finally{
			ServletHelper.destory();
		}
	}
	private void handleViewResult(View view,HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String path = view.getPath();
		if(StringUtil.isNotEmpty(path)){
			if(path.startsWith("/")){
				response.sendRedirect(request.getContextPath() + path);
			}else{
				Map<String,Object> model = view.getModel();
				for(Map.Entry<String, Object> entry : model.entrySet()){
					request.setAttribute(entry.getKey(), entry.getValue());
				}
				request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);			}
		}
	}
	
	private void handleDataResult(Data data,HttpServletResponse response)throws IOException{
		Object model = data.getModel();
		if(model != null){
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			String json = JsonUtil.toJson(model);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}
}
