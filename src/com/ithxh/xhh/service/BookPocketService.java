package com.ithxh.xhh.service;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookPocket;
import com.ithxh.xhh.vo.formbean.BookPocketVo;
import com.ithxh.xhh.vo.formbean.BookVo;

public interface BookPocketService extends BaseService<BookPocket, BookPocketVo>{

	public ReturnMessage<Object> del(BookPocketVo obj);
	
	public List<BookVo> getBookVoByUserid(String userid);
}
