package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.xhh.dao.BookRolePrivilegeDao;
import com.ithxh.xhh.entity.BookRolePrivilege;
import com.ithxh.xhh.vo.formbean.BookRolePrivilegeVo;

@Repository("bookRolePrivilegeDao")
public class BookRolePrivilegeDaoImpl extends BaseDaoImpl<BookRolePrivilege, BookRolePrivilegeVo> implements BookRolePrivilegeDao{

	public BookRolePrivilegeVo findBookRolePrivilegeVoById(String roleid,String privilegeid){
		String sql = "select * from "+sb.getTableName()+" where ROLEID = ? and PRIVILEGEID = ?";
		List<BookRolePrivilegeVo> list = jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BookRolePrivilegeVo.class), roleid,privilegeid);
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
}
