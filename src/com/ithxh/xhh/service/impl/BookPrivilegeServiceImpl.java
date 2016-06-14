package com.ithxh.xhh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.dao.BookPrivilegeDao;
import com.ithxh.xhh.entity.BookPrivilege;
import com.ithxh.xhh.service.BookPrivilegeService;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;

@Service("bookPrivilegeService")
@Transactional
public class BookPrivilegeServiceImpl extends BaseServiceImpl<BookPrivilege, BookPrivilegeVo> implements BookPrivilegeService{

	@Autowired
	BookPrivilegeDao bookPrivilegeDao;
	
	@Override
	public List<BookPrivilegeVo> getPrivilegeListByRoleId(String roleId) {
		
		return bookPrivilegeDao.getPrivilegeListByRoleId(roleId);
	}
	
	public List<BookPrivilegeVo> getAllList(){
		return bookPrivilegeDao.getAllList();
	}

}
