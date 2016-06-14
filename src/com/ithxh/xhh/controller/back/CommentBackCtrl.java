package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.service.BookCommentService;


@RequestMapping(value="commentBack")
@Controller
public class CommentBackCtrl extends BaseController{
	
	@Autowired
	BookCommentService bookCommentService;

	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(){
		
		return "operating/commentList";
	}
	
	
	@RequestMapping(value="doList",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pager){
		ReturnMessage<Object> rm = bookCommentService.getPager(pager);
		return rm;
	}
	
}
