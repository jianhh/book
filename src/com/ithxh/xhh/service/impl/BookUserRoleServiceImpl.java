package com.ithxh.xhh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.dao.BookUserRoleDao;
import com.ithxh.xhh.entity.BookUserRole;
import com.ithxh.xhh.service.BookUserRoleService;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserRoleVo;

@Service("bookUserRoleService")
@Transactional
public class BookUserRoleServiceImpl extends BaseServiceImpl<BookUserRole, BookUserRoleVo> implements BookUserRoleService{
	
	@Autowired
	private BookUserRoleDao bookUserRoleDao;

	public BookRoleVo getBookRoleVoByUserId(String userId){
		return bookUserRoleDao.getBookRoleVoByUserId(userId);
	}
}
