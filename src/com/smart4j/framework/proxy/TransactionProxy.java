package com.smart4j.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.cc.helper.DatabaseHelper;
import com.smart4j.framework.annotation.Transaction;

/**
* @ClassName: TransactionProxy
* @Description: 事务代理
* @author BEE 
* @date 2016-4-5 上午10:21:22
 */
public class TransactionProxy implements Proxy {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
	
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)){
			FLAG_HOLDER.set(true);
			try{
				DatabaseHelper.beginTransaction();
				LOGGER.info("begin transaction");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransaction();
				LOGGER.info("commit transaction");
			}catch (Exception e) {
				DatabaseHelper.rollbackTransaction();
				LOGGER.info("rollback transaction");
				throw e;
			}finally{
				FLAG_HOLDER.remove();
			}
		}else{
			result = proxyChain.doProxyChain();
		}
		return result;
	}
}
