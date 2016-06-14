package com.ithxh.xhh.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.PageNotFoundException;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookTypeService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.vo.formbean.BookTypeVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@RequestMapping(value="bookBack")
@Controller
public class BookBackCtrl extends BaseController{
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	BookUserService bookUserService;
	
	@Autowired
	BookTypeService bookTypeService;
	
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(){
		return "operating/bookList";
	}
	
	
	@RequestMapping(value="doList",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pager){
		ReturnMessage<Object> rm = bookService.getPager(pager);
		return rm;
	}
	
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(@RequestParam(value = "bookid", required = false) String bookid) {
		//获取分类
		List<BookTypeVo> bookTypeVos = bookTypeService.getAll();
		this.set("tList", bookTypeVos);
		
		if(StringUtil.isEmpty(bookid)){
			
			return "operating/bookEditor";
		}
		
		//获取该书的信息
		BookVo obj = bookService.getById(bookid);
		if(obj==null){
			throw new PageNotFoundException();
		}
		this.set("obj", obj);
		
		UploadFileVo pic = uploadFileService.getById(obj.getBookpic());
		obj.setBookpicpath(pic.getUploadfilepath());
		return "operating/bookEditor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> save(BookVo vo) {
		ReturnMessage<Object> rm = null;
		if(StringUtil.isEmpty(vo.getBookid())){
			vo.setBookid(IDGenerator.uuidGenerate());
			vo.setBookaddtime(DateUtils.getNowDateTime());
			
			rm = bookService.add(vo);
		}else{
			rm = bookService.update(vo);
		}
		
		return rm;
	}
	
	
	@RequestMapping(value="toView",method=RequestMethod.GET)
	public String toView(@RequestParam String id){
		BookVo bookVo = bookService.getById(id);
		
		UploadFileVo pic = uploadFileService.getById(bookVo.getBookpic());
		bookVo.setBookpicpath(pic.getUploadfilepath());
		
		BookTypeVo typeVo = bookTypeService.getById(bookVo.getBooktypeid());
		bookVo.setBooktypename(typeVo.getBooktypename());
		
		this.set("obj", bookVo);
		return "operating/bookView";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage delete(@RequestParam(value = "id", required = true) String[] id) {
		ReturnMessage<Object> rm = bookService.del(id);
		return rm;
	}
	
}
