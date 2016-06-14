package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookUser;
import com.ithxh.xhh.vo.formbean.BookUserVo;

public interface BookUserService extends BaseService<BookUser, BookUserVo>{

	public ReturnMessage<BookUserVo> login(String userName, String password);
	
	public ReturnMessage<Object> doReg(BookUserVo userVo);
	
	public ReturnMessage<Object> validate(String code);
	
	public ReturnMessage<Object> completeReg(BookUserVo vo);
	
}
