package com.ithxh.xhh.dao;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookUser;
import com.ithxh.xhh.vo.formbean.BookUserVo;

public interface BookUserDao extends BaseDao<BookUser, BookUserVo>{

	public BookUserVo getBookUserVoByUserName(String userName);
	

}
