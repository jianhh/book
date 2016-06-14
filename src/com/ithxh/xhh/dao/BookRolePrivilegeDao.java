package com.ithxh.xhh.dao;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookRolePrivilege;
import com.ithxh.xhh.vo.formbean.BookRolePrivilegeVo;

public interface BookRolePrivilegeDao extends BaseDao<BookRolePrivilege, BookRolePrivilegeVo>{
	

	public BookRolePrivilegeVo findBookRolePrivilegeVoById(String roleid,String privilegeid);
}
