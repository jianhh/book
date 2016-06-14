package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookPrivilegeDao;
import com.ithxh.xhh.entity.BookPrivilege;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;

@Repository("bookPrivilegeDao")
public class BookPrivilegeDaoImpl extends BaseDaoImpl<BookPrivilege, BookPrivilegeVo> implements BookPrivilegeDao{

	@Override
	public List<BookPrivilegeVo> getAllList() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select * from ").append(sb.getTableName()).append(" where PRIVILEGEISENABLE='1'");
		
		return jt.query(sBuilder.toString(), ParameterizedBeanPropertyRowMapper.newInstance(BookPrivilegeVo.class));
	}

	
	public List<BookPrivilegeVo> getPrivilegeListByRoleId(String roleId) {
		String sql = "select * from book_role left join book_role_privilege r on book_role.ROLEID=r.ROLEID left join book_privilege p on r.PRIVILEGEID=p.PRIVILEGEID where r.ROLEID=? and book_role.ROLEISENABLE='1'";
		List<BookPrivilegeVo> list = super.findList(sql, new String[]{roleId});
		return list;
	}

}
