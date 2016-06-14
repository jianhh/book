package com.ithxh.xhh.dao.impl;

import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookRoleDao;
import com.ithxh.xhh.entity.BookRole;
import com.ithxh.xhh.vo.formbean.BookRoleVo;

@Repository("bookRoleDao")
public class BookRoleDaoImpl extends BaseDaoImpl<BookRole, BookRoleVo> implements BookRoleDao{


}
