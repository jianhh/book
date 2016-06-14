package com.ithxh.baseCore.annotation.DB;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 表主键
 * @author hxh
 * @date 2016年1月13日 下午8:18:16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {

	String value() default "";
}
