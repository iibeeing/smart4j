package com.smart4j.framework.proxy;

/**
 * @ClassName: Proxy
 * @Description: 链式代理接口
 * @author BEE
 * @date 2016-3-31 下午5:00:29
 */
public interface Proxy {
	/**
	 * @Title: doProxy
	 * @Description: 执行链式代理
	 * @param @param proxyChain
	 * @param @return
	 * @param @throws Throwable 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	Object doProxy(ProxyChain proxyChain) throws Throwable;
}
