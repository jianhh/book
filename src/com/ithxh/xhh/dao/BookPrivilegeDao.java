package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookPrivilege;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;

public interface BookPrivilegeDao extends BaseDao<BookPrivilege, BookPrivilegeVo>{

	public List<BookPrivilegeVo> getAllList();
	
	public List<BookPrivilegeVo> getPrivilegeListByRoleId(String roleId);

}
