package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookRole;
import com.ithxh.xhh.vo.formbean.BookRoleVo;

public interface BookRoleService extends BaseService<BookRole, BookRoleVo>{
	
	public ReturnMessage<Object> add(BookRoleVo bookRoleVo,String[] privileges);
	
	public ReturnMessage<Object> update(BookRoleVo bookRoleVo,String[] privileges);
}
