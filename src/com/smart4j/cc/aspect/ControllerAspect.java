package com.smart4j.cc.aspect;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smart4j.framework.annotation.Aspect;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.proxy.AspectProxy;

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;

/*	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object obj = proxyChain.doProxyChain();
		return obj;
	}*/
	
	@Override
	public void before(Class<?> cls, Method method, Object[] params)
			throws Throwable {
		LOGGER.info("ControllerAspect------------------------------------- begin -------------------------------------");
		//LOGGER.info(String.format("class: %s", cls.getName()));
		LOGGER.info(String.format("类名: %s", cls.getName()));
		//LOGGER.info(String.format("method: %s",method.getName()));
		LOGGER.info(String.format("调用方法: %s",method.getName()));
		begin = System.currentTimeMillis();
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params,
			Object result) throws Throwable {
		//LOGGER.info(String.format("time: %dms", System.currentTimeMillis() - begin));
		LOGGER.info(String.format("耗时: %dms", System.currentTimeMillis() - begin));
		LOGGER.info("ControllerAspect------------------------------------- end -------------------------------------");
	}
}
