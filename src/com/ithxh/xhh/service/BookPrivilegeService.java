package com.ithxh.xhh.service;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.xhh.entity.BookPrivilege;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;

public interface BookPrivilegeService extends BaseService<BookPrivilege, BookPrivilegeVo>{

	public List<BookPrivilegeVo> getPrivilegeListByRoleId(String roleId);
	
	public List<BookPrivilegeVo> getAllList();
}
