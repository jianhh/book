package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.PageNotFoundException;
import com.ithxh.xhh.service.BookTypeService;
import com.ithxh.xhh.vo.formbean.BookTypeVo;

@RequestMapping(value="bookTypeBack")
@Controller
public class BookTypeBackCtrl extends BaseController{

	@Autowired
	BookTypeService bookTypeService;
	
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(){
		
		return "operating/bookTypeList";
	}
	
	
	@RequestMapping(value="doList",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pager){
		ReturnMessage<Object> rm = bookTypeService.getPager(pager);
		return rm;
	}
	
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(@RequestParam(value = "articleid", required = false) String articleid) {
		
		if(StringUtil.isEmpty(articleid)){
			
			return "operating/articleEditor";
		}
		
		//获取该文章的信息
		BookTypeVo obj = bookTypeService.getById(articleid);
		if(obj==null){
			throw new PageNotFoundException();
		}
		this.set("obj", obj);
		return "operating/bookTypeEditor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> save(BookTypeVo vo) {
		ReturnMessage<Object> rm = null;
		if(StringUtil.isEmpty(vo.getBooktypeid())){
			vo.setBooktypeid(IDGenerator.uuidGenerate());
			rm = bookTypeService.add(vo);
		}else{
			rm = bookTypeService.update(vo);
		}
		
		return rm;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage delete(@RequestParam(value = "booktypeid", required = true) String[] booktypeid) {
		ReturnMessage<Object> rm = bookTypeService.del(booktypeid);
		return rm;
	}
	
}
