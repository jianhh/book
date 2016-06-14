package com.ithxh.xhh.service;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.NETBOOKSOURCE;
import com.ithxh.xhh.entity.Book;
import com.ithxh.xhh.vo.formbean.BookTypeVo;
import com.ithxh.xhh.vo.formbean.BookVo;

public interface BookService extends BaseService<Book, BookVo>{

	public ReturnMessage<BookVo> getBooksByName(Pager pager,boolean islocal ,String isbnorname,String realPath,NETBOOKSOURCE source);
	
	public ReturnMessage<Object> collect(String bookid,String userid);
	
	public List<BookTypeVo> getBooktypeList();
	
}
