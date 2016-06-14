package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookSell;
import com.ithxh.xhh.vo.formbean.BookSellVo;

public interface BookSellService extends BaseService<BookSell, BookSellVo>{

	public ReturnMessage<Object> doSell(BookSellVo vo);
	
	public ReturnMessage<Object> getSuccessSellPager(Pager pagerTemp,String userid);
	
	public ReturnMessage<Object> getBuyListPager(Pager pagerTemp,String userid);
	
	public ReturnMessage<Object> getSellPager(Pager pagerTemp,String userid);
	
	public ReturnMessage<Object> getSellerList(Pager pager,String bookid);
	
	public int getCountSellByBookid(String bookid);
}
