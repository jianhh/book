package com.ithxh.xhh.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ithxh.baseCore.baseUtils.Log4jUtil;

/**
 * @category 异常统一处理
 * @Copyright Copyright(c) 2012
 * @Company
 * @author 伍志平
 * @version V1.0
 * @date 2012-11-19
 * 
 */
@Component
public class MyException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception ex) {

		Map<String, Object> model = new HashMap<String, Object>();
		String contextPath = arg0.getContextPath();
		String requestURI = arg0.getRequestURI();
		if (!"".equals(contextPath)) {
			requestURI = requestURI.replaceFirst(contextPath, "");
		}
		model.put("url", requestURI);
		model.put("ex", ex);
		ex.printStackTrace();
		Log4jUtil.error(requestURI, ex);
		return new ModelAndView("/errorPage.jsp", model);
	}
}
