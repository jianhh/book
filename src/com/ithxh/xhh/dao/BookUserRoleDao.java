package com.ithxh.xhh.dao;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookUserRole;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserRoleVo;

public interface BookUserRoleDao extends BaseDao<BookUserRole, BookUserRoleVo>{
	
	public BookRoleVo getBookRoleVoByUserId(String userId);
}
