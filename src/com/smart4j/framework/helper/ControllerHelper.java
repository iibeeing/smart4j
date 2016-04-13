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
* @Description: ����Controller����������
* @author BEE 
* @date 2016-3-30 ����9:25:31
 */
public final class ControllerHelper {
	//���ڴ�������봦����ӳ���ϵ�����Action Map��
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
	static{
		//��ȡ���е�Controller��
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			for(Class<?> controllerClass : controllerClassSet){
				//��ȡController���ж���ķ���
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtil.isNotEmpty(methods)){
					//������ЩController�еķ���
					for(Method method : methods){
						//�жϵ�ǰ�����Ƿ����Action ע��
						if(method.isAnnotationPresent(Action.class)){
							//��Actionע���л�ȡURL ӳ�����
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							//��֤URLӳ�����
							if(mapping.matches("\\w+:/\\w*")){
								String[] array = mapping.split(":");
								if(ArrayUtil.isNotEmpty(array) && array.length == 2){
									//��ȡ���󷽷�������·��
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									//��ʼ��Action Map
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
	 * ��ȡHandler
	 */
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
