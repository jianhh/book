package com.ithxh.baseCore.baseUtils;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.ithxh.baseCore.model.ReturnMessage;

/**
 * @category 错误信息处理
 * @author z
 * @date 2014-10-21
 * @time 下午2:47:45
 */
public class ErrorUtils {

	// 错误信息分隔符
	static final String MESSAGE_SEPARATOR = "<br/>";

	/**
	 * @category 检查是否有错误，如果有，返回错误信息
	 * @author z
	 * @date 2014-10-21
	 * @time 下午2:59:26
	 * @return ReturnMessage
	 */
	@SuppressWarnings("rawtypes")
	public static ReturnMessage checkErrors(Errors errors) {

		ReturnMessage rm = new ReturnMessage();

		if (errors.hasErrors()) {
			StringBuilder errorSb = new StringBuilder();
			List<ObjectError> ls = errors.getAllErrors();

			for (int i = 0; i < ls.size(); i++) {
				if (i != 0)
					errorSb.append(MESSAGE_SEPARATOR);
				errorSb.append(ls.get(i).getDefaultMessage());
				System.out.println("错误信息error:" + ls.get(i).getDefaultMessage());
			}

			rm.setResult(false);
			rm.setMessage(errorSb.toString());
		} else {
			rm.setResult(true);
		}

		return rm;
	}
}
