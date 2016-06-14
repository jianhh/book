package com.ithxh.baseCore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * @category 自定义注�?验证VO 中的字段是否符合规范，本类是接口�?
 * @author 
 *
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckerValidator.class)
public @interface Checker {

	String message() default "输入的数据不符合规范，请重新输入";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	// �?��值�?�?��长度
	int min() default Integer.MIN_VALUE;

	// �?��值�?�?��长度
	int max() default Integer.MAX_VALUE;

	// 是否必填
	boolean required() default false;

	// 不能为空字符�?
	boolean notBlank() default false;

	// 正则表达�?
	String regexp() default "";

}
