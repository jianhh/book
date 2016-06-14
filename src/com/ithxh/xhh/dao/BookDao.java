package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.Book;
import com.ithxh.xhh.vo.formbean.BookVo;

public interface BookDao extends BaseDao<Book, BookVo>{

	public List<BookVo> findBookVosByIsbnOrName(String isbnorname,int start,int length);
	
	public int findCountByIsbnOrName(String isbnorname);
}
