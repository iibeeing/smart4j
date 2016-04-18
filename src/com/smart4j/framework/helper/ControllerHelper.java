package com.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.smart4j.cc.util.CollectionUtil;
import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.bean.Handler;
import com.smart4j.framework.bean.Request;
import com.smart4j.framework.util.ArrayUtil;

/**
* @ClassName: ControllerHelper
* @Description: 解析Controller（控制器）
* @author BEE 
* @date 2016-3-30 上午9:25:31
 */
public final class ControllerHelper {
	//用于存放请求与处理器映射关系（简称Action Map）
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
	static{
		//获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			for(Class<?> controllerClass : controllerClassSet){
				//获取Controller类中定义的方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)){
					//遍历这些Controller中的方法
					for(Method method : methods){
						//判断当前方法是否带有Action 注解
						if(method.isAnnotationPresent(Action.class)){
							//从Action注解中获取URL 映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//验证URL映射规则
							if(mapping.matches("\\w+:/\\w*")){
								String[] array = mapping.split(":");
								if(ArrayUtil.isNotEmpty(array) && array.length == 2){
									//获取请求方法与请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									//初始化Action Map
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 获取Handler
	 */
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
