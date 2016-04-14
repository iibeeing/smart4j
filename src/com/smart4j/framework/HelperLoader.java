package com.smart4j.framework;

import com.smart4j.framework.helper.AopHelper;
import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ClassHelper;
import com.smart4j.framework.helper.ControllerHelper;
import com.smart4j.framework.helper.IocHelper;
import com.smart4j.framework.util.ClassUtil;

/**
* @ClassName: HelperLoader
* @Description: 加载相应的Helper类
* @author BEE 
* @date 2016-3-28 下午3:31:56
 */
public final class HelperLoader {
	public static void init(){
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		
		for(Class<?> cls : classList){
			ClassUtil.loadClass(cls.getName());
		}
	}
}
