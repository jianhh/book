package com.ithxh.xhh.dao.impl;

import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookArticleDao;
import com.ithxh.xhh.entity.BookArticle;
import com.ithxh.xhh.vo.formbean.BookArticleVo;

@Repository("bookArticleDao")
public class BookArticleDaoImpl extends BaseDaoImpl<BookArticle, BookArticleVo> implements BookArticleDao{
	
}
