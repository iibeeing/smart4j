package com.smart4j.cc.aspect;

import com.smart4j.framework.proxy.AspectProxy;
import com.smart4j.framework.proxy.ProxyChain;

//@Aspect(Service.class)
public class ServiceAspect extends AspectProxy {

	@Override
	public void begin() {
		System.out.println("ServiceAspect���� ************ Service������");
	}
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object obj = super.doProxy(proxyChain);
		System.out.println("ServiceAspect���淵��ֵ ************ " + obj);
		return obj;
	}
}
