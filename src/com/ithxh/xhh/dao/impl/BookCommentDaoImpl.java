package com.ithxh.xhh.dao.impl;

import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookCommentDao;
import com.ithxh.xhh.entity.BookComment;
import com.ithxh.xhh.vo.formbean.BookCommentVo;

@Repository("bookCommentDao")
public class BookCommentDaoImpl extends BaseDaoImpl<BookComment, BookCommentVo> implements BookCommentDao{
	
}
