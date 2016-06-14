package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.xhh.dao.BookUserRoleDao;
import com.ithxh.xhh.entity.BookUserRole;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserRoleVo;

@Repository("bookUserRoleDao")
public class BookUserRoleDaoImpl extends BaseDaoImpl<BookUserRole, BookUserRoleVo> implements BookUserRoleDao{
	
	public BookRoleVo getBookRoleVoByUserId(String userId){
		String sql = "select * from book_user_role b left join book_role r on b.ROLEID=r.ROLEID where b.USERID=?";
		List<BookRoleVo> list = jt.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(BookRoleVo.class),userId);
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
}
