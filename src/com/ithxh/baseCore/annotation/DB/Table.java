package com.ithxh.baseCore.annotation.DB;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 表名 
 * @author hxh
 * @date 2016年1月13日 下午8:18:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

	String value() default "";
}
