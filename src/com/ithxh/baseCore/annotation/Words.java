package com.ithxh.baseCore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * @category 自定义注�?验证VO 中的字段值是否包含有关键字，本类是接口类
 * @author five 
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WordsValidator.class)
public @interface Words {

	String message() default "您的输入包含有敏感关键字，请重新输入";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
