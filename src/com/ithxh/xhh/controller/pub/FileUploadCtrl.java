package com.ithxh.xhh.controller.pub;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.baseCore.plugins.smartupload.JspSmartUploadUtil;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.entity.UploadFile;


@Controller
@RequestMapping("fileUpload")
public class FileUploadCtrl extends BaseController{
	
	@RequestMapping(value="doPicUpload",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<UploadFile> doPicUpload(HttpServletRequest request){
		ReturnMessage<UploadFile> r = uploadSimple(request,JspSmartUploadUtil.getSmartUpload("pic", this.getClass().getClassLoader().getResourceAsStream("/smartUpload.xml")));
		return r;
	}
	
	@RequestMapping(value="")
	public Object upload(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReturnMessage<UploadFile> r = uploadSimple(request,JspSmartUploadUtil.getSmartUpload("pic", this.getClass().getClassLoader().getResourceAsStream("/smartUpload.xml")));
			
		if(r.isResult()){
			response.getWriter().write(r.getO().getUploadfilepath());
		}else{
			response.getWriter().write("error|上传失败");
		}
		return null;
	}

}
