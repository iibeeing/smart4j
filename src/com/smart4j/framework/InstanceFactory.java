package com.smart4j.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smart4j.cc.util.StringUtil;
import com.smart4j.framework.ds.DataSourceFactory;
import com.smart4j.framework.ds.impl.DefaultDataSourceFactory;
import com.smart4j.framework.util.ObjectUtil;

public class InstanceFactory {
	   /**
     * ���ڻ����Ӧ��ʵ��
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * ClassScanner
     */
    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";

    /**
     * DataAccessor
     */
    private static final String DATA_ACCESSOR = "smart.framework.custom.data_accessor";

    /**
     * HandlerMapping
     */
    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    /**
     * HandlerInvoker
     */
    private static final String HANDLER_INVOKER = "smart.framework.custom.handler_invoker";

    /**
     * HandlerExceptionResolver
     */
    private static final String HANDLER_EXCEPTION_RESOLVER = "smart.framework.custom.handler_exception_resolver";

    /**
     * ViewResolver
     */
    private static final String VIEW_RESOLVER = "smart.framework.custom.view_resolver";

    /**
     * ��ȡ ClassScanner
     */
/*    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }*/

    /**
     * ��ȡ DataSourceFactory
     */
    public static DataSourceFactory getDataSourceFactory() {
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
    }

 /*   *//**
     * ��ȡ DataAccessor
     *//*
    public static DataAccessor getDataAccessor() {
        return getInstance(DATA_ACCESSOR, DefaultDataAccessor.class);
    }

    *//**
     * ��ȡ HandlerMapping
     *//*
    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    *//**
     * ��ȡ HandlerInvoker
     *//*
    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_INVOKER, DefaultHandlerInvoker.class);
    }

    *//**
     * ��ȡ HandlerExceptionResolver
     *//*
    public static HandlerExceptionResolver getHandlerExceptionResolver() {
        return getInstance(HANDLER_EXCEPTION_RESOLVER, DefaultHandlerExceptionResolver.class);
    }

    *//**
     * ��ȡ ViewResolver
     *//*
    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, DefaultViewResolver.class);
    }*/

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // �������д��ڶ�Ӧ��ʵ�����򷵻ظ�ʵ��
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // �������ļ��л�ȡ��Ӧ�Ľӿ�ʵ��������
        String implClassName = ConfigHelper.getString(cacheKey);
        // ��ʵ�������ò����ڣ���ʹ��Ĭ��ʵ����
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        // ͨ�����䴴����ʵ�����Ӧ��ʵ��
        T instance = ObjectUtil.newInstance(implClassName);
        // ����ʵ����Ϊ�գ�������뻺��
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        // ���ظ�ʵ��
        return instance;
    }
}
