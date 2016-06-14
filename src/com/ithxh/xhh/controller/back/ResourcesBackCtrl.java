package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.vo.formbean.UploadFileVo;


@RequestMapping(value="resourcesBack")
@Controller
public class ResourcesBackCtrl extends BaseController{
	
	@Autowired
	UploadFileService uploadFileService;

	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(){
		
		return "operating/resourcesList";
	}
	
	
	@RequestMapping(value="doList",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pager){
		ReturnMessage<Object> rm = uploadFileService.getPager(pager);
		return rm;
	}
	
	@RequestMapping(value="toView",method=RequestMethod.GET)
	public String toView(@RequestParam String uploadid){
		UploadFileVo obj = uploadFileService.getById(uploadid);
		this.set("obj", obj);
		return "operating/resourcesView";
	}
}
