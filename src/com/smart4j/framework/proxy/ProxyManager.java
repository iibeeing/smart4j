package com.smart4j.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
* @ClassName: ProxyManager
* @Description: 代理辅助类
* @author BEE 
* @date 2016-3-31 下午5:17:10
 */
public class ProxyManager {

	/**
	@Description: 创建代理（动态创建代理），一个目标类可以被切面类的操作执行（如果是列表，则按顺序一个一个执行）
	@param @param targetClass 被切面，被代理类
	@param @param proxyList 切面类列表、代理类列表
	@param @return    设定文件
	@date 创建时间：2016-4-6 下午3:22:11 
	@version 1.0
	@return T    返回类型
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
