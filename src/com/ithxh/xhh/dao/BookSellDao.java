package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookSell;
import com.ithxh.xhh.vo.formbean.BookSellVo;

public interface BookSellDao extends BaseDao<BookSell, BookSellVo>{

	public List<BookSellVo> findSellListByUser(String userid,int start,int length);
	
	public int findCountSellListByUser(String userid);
	
	public List<BookSellVo> findSellListByBookid(String bookid,int start,int length);
	
	public int findCountSellByBookid(String bookid);
}
