package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.xhh.entity.BookUserRole;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserRoleVo;

public interface BookUserRoleService extends BaseService<BookUserRole, BookUserRoleVo>{

	public BookRoleVo getBookRoleVoByUserId(String userId);
}
