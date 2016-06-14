package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookBuy;
import com.ithxh.xhh.vo.formbean.BookBuyVo;

public interface BookBuyService extends BaseService<BookBuy, BookBuyVo>{

	public ReturnMessage<BookBuyVo> addOrder(String[] pocketids,String userid);
	
	public ReturnMessage<BookBuyVo> pay(String[] orderid,String addressid,String[] remark,String userid);
}
