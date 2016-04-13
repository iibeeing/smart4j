package com.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @ClassName: Inject
* @Description: ÒÀÀµ×¢Èë×¢½â
* @author BEE 
* @date 2016-3-28 ÉÏÎç11:08:59
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}
