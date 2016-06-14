package com.ithxh.xhh.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.servlet.KaptchaServlet;

@SuppressWarnings("serial")
public class MyKaptchaServlet extends KaptchaServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		
		super.init(arg0);
	}
}
