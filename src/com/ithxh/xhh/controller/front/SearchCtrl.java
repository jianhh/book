package com.ithxh.xhh.controller.front;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.CharUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst.NETBOOKSOURCE;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.vo.formbean.BookVo;

@Controller
@RequestMapping("search")
public class SearchCtrl extends BaseController{

	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="index")
	public String searchByWord(){
		//随机获取书籍
		List<BookVo> randList = bookService.getByOrderAndLength(" RAND() ", "0", "10");
		this.set("randList", randList);
		return "front/search";
	}
	
	@RequestMapping(value="")
	public String searchByWord(@RequestParam String key) throws UnsupportedEncodingException{
		String word = new String(key.getBytes("iso8859-1"),"utf-8");
		if(CharUtil.isChinese(word)){
			key = word;
		}
		this.set("key", key);
		return "front/search";
	}
	
	@RequestMapping(value="getPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<BookVo> getPager(Pager pager,@RequestParam String key) throws UnsupportedEncodingException{
		//如果是中文，则使用百度搜索
		NETBOOKSOURCE net = NETBOOKSOURCE.JD;
		if(CharUtil.isChinese(key)){
			net = NETBOOKSOURCE.BD;
		}
		//否则使用京东搜索
		ReturnMessage<BookVo> rm = bookService.getBooksByName(new Pager(),true, key, getRealPath(), net);
		return rm;
	}
	
	@RequestMapping(value="jd")
	@ResponseBody
	public ReturnMessage<BookVo> jd(@RequestParam String key) throws UnsupportedEncodingException{
		String word = new String(key.getBytes("iso8859-1"),"utf-8");
		if(CharUtil.isChinese(word)){
			key = word;
		}
		ReturnMessage<BookVo> rm = bookService.getBooksByName(new Pager(),false, key, getRealPath(), NETBOOKSOURCE.JD);
		return rm;
	}
	
	@RequestMapping(value="bd")
	@ResponseBody
	public ReturnMessage<BookVo> bd(HttpServletRequest request,@RequestParam String key) throws UnsupportedEncodingException{
		String word = new String(key.getBytes("iso8859-1"),"utf-8");
		if(CharUtil.isChinese(word)){
			key = word;
		}
		ReturnMessage<BookVo> rm = bookService.getBooksByName(new Pager(),false, key, getRealPath(), NETBOOKSOURCE.BD);
		return rm;
	}
	
	@RequestMapping(value="tm")
	@ResponseBody
	public ReturnMessage<BookVo> tm(@RequestParam String name){
		return null;
	}
}
