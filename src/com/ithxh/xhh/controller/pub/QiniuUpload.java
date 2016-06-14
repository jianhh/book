package com.ithxh.xhh.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.baseCore.plugins.smartupload.JspSmartUploadUtil;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.entity.UploadFile;

@Controller
@RequestMapping("qiniu")
public class QiniuUpload extends BaseController{

	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<UploadFile> doPicUpload(HttpServletRequest request){
		ReturnMessage<UploadFile> r = uploadSimple1(request,JspSmartUploadUtil.getSmartUpload("pic", this.getClass().getClassLoader().getResourceAsStream("/smartUpload.xml")));
		return r;
	}
}
