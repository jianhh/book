package com.ithxh.baseCore.freeMarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.ithxh.xhh.tools.SysConfigUtil;

public class BaseFreeMarkerView extends FreeMarkerView {

	
	private static final String CONTEXT_PATH = "BASE_PATH";
	private static final String QINIU_URL = "QINIU_URL";

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {

		// model.put(CONTEXT_PATH, request.getContextPath());
		model.put(CONTEXT_PATH, SysConfigUtil.getSysConfig().getSysBasePath());
		
		model.put(QINIU_URL, SysConfigUtil.getSysConfig().getQiniuUrl());
		
		// System.out.println(CONTEXT_PATH+"=================================:"+SysConfigUtil.getSysConfig().getSysBasePath());

		// model.put("check_power", new CheckPower(request.getSession()));

		super.exposeHelpers(model, request);
	}
}
