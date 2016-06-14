package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.entity.BookUser;
import com.ithxh.xhh.vo.formbean.BookUserVo;

@Repository("bookUserDao")
public class BookUserDaoImpl extends BaseDaoImpl<BookUser, BookUserVo> implements BookUserDao{

	@Override
	public BookUserVo getBookUserVoByUserName(String userName) {
		String sql = "select * from book_user where USERNAME=? or EMAIL=?";
		List<BookUserVo> list = super.findList(sql, new String[]{userName,userName});
		if(ListUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	
	/**
	 * @category 根据用户id查询 该用户所拥有的权限列表
	 * @Description 
	 * @Author 何建辉
	 * @date 2015年10月13日  上午10:06:43
	 * @param resUserId
	 * @return
	 */
	public List<BookUserVo> getBookPrivilegeListByUserId(String userId){
		String sql = "SELECT * FROM book_privilege WHERE book_privilege.privilegefatherid is null UNION "
				+ "(SELECT DISTINCT  book_privilege.* FROM book_user_role LEFT JOIN book_role_privilege ON book_user_role.roleid=book_role_privilege.roleid LEFT JOIN book_privilege ON book_role_privilege.privilegeid=book_privilege.privilegeid"
				+ "WHERE (book_user_role.userid=? OR book_privilege.privilegefatherid is null) AND book_user_role.isEnable=1 AND sys_column.isEnable=1)";
		
		return findList(sql, new String[]{userId});
	}

}
