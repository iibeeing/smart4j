package com.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@ClassName: ObjectUtil
@Description: �������������
@author BEE 
@date 2016-4-13 ����2:48:46
 */
public class ObjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    /**
     * ���ó�Ա����
     */
    public static void setField(Object obj, String fieldName, Object fieldValue) {
        try {
            if (PropertyUtils.isWriteable(obj, fieldName)) {
                PropertyUtils.setProperty(obj, fieldName, fieldValue);
            }
        } catch (Exception e) {
            logger.error("���ó�Ա��������", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * ��ȡ��Ա����
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object propertyValue = null;
        try {
            if (PropertyUtils.isReadable(obj, fieldName)) {
                propertyValue = PropertyUtils.getProperty(obj, fieldName);
            }
        } catch (Exception e) {
            logger.error("��ȡ��Ա��������", e);
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    /**
     * �������г�Ա����
     */
    public static void copyFields(Object source, Object target) {
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                // ����Ϊ static ��Ա����������и��Ʋ���
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true); // �ɲ���˽�г�Ա����
                    field.set(target, field.get(source));
                }
            }
        } catch (Exception e) {
            logger.error("���Ƴ�Ա��������", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * ͨ�����䴴��ʵ��
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            logger.error("����ʵ������", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * ��ȡ������ֶ�ӳ�䣨�ֶ��� => �ֶ�ֵ�������� static �ֶ�
     */
    public static Map<String, Object> getFieldMap(Object obj) {
        return getFieldMap(obj, true);
    }

    /**
     * ��ȡ������ֶ�ӳ�䣨�ֶ��� => �ֶ�ֵ��
     */
    public static Map<String, Object> getFieldMap(Object obj, boolean isStaticIgnored) {
        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (isStaticIgnored && Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String fieldName = field.getName();
            Object fieldValue = ObjectUtil.getFieldValue(obj, fieldName);
            fieldMap.put(fieldName, fieldValue);
        }
        return fieldMap;
    }
}
