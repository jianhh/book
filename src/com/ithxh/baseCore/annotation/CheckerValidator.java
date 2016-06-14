package com.ithxh.baseCore.annotation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * @category 自定义注�?验证VO 中的字段是否符合规范，本类是实现�?
 * @author 
 */

public class CheckerValidator implements ConstraintValidator<Checker, Object> {

	private int min;

	private int max;

	private boolean required;

	private boolean notBlank;

	private String regexp;

	@Override
	public void initialize(Checker arg) {

		this.min = arg.min();
		this.max = arg.max();

		this.required = arg.required();
		this.notBlank = arg.notBlank();

		this.regexp = arg.regexp();
	}

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {

		if (null == arg0) {
			if (required)
				return false;
		} else {
			// 如果字段不为�?

			if (arg0 instanceof String) { // 如果是字符串
				String s = arg0.toString();

				if (notBlank) {
					// 空字符串
					if (s.trim().equals(""))
						return false;
				}

				// 字符长度少于下限
				if (s.length() < min)
					return false;

				// 字符长度超出上限
				if (s.length() > max)
					return false;

				// 正则表达�?
				if (!"".equals(regexp)) {
					return Pattern.compile(regexp).matcher(arg0.toString()).matches();
				}

			} else if (arg0 instanceof Integer) { // 如果是整�?
				int i = Integer.parseInt(arg0.toString());

				// 数字过小
				if (i < min)
					return false;

				// 数字过大
				if (i > max)
					return false;

			}
		}

		return true;
	}
}
