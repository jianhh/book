package com.ithxh.baseCore.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @category 自定义注解验证VO 中的字段值是否包含有关键字，本类是实现类
 * @author Administrator
 */
public class WordsValidator implements ConstraintValidator<Words, String> {

	@Override
	public void initialize(Words arg0) {

	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

		return true;
	}
}
