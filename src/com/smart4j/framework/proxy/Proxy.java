package com.smart4j.framework.proxy;

/**
 * @ClassName: Proxy
 * @Description: ��ʽ����ӿ�
 * @author BEE
 * @date 2016-3-31 ����5:00:29
 */
public interface Proxy {
	/**
	 * @Title: doProxy
	 * @Description: ִ����ʽ����
	 * @param @param proxyChain
	 * @param @return
	 * @param @throws Throwable �趨�ļ�
	 * @return Object ��������
	 * @throws
	 */
	Object doProxy(ProxyChain proxyChain) throws Throwable;
}
