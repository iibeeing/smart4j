package com.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
* @ClassName: ProxyManager
* @Description: ��������
* @author BEE 
* @date 2016-3-31 ����5:17:10
 */
public class ProxyManager {

	/**
	@Description: ����������̬����������һ��Ŀ������Ա�������Ĳ���ִ�У�������б���˳��һ��һ��ִ�У�
	@param @param targetClass �����棬��������
	@param @param proxyList �������б��������б�
	@param @return    �趨�ļ�
	@date ����ʱ�䣺2016-4-6 ����3:22:11 
	@version 1.0
	@return T    ��������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass,final List<Proxy> proxyList) {
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			@Override
			public Object intercept(Object targetObject, Method targetMethod,Object[] methodParams, MethodProxy methodProxy)throws Throwable {
				return new ProxyChain(targetClass, targetObject, targetMethod,methodProxy, methodParams, proxyList).doProxyChain();
			}
		});
	}
}
