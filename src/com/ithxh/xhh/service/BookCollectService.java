package com.ithxh.xhh.service;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.BookCollect;
import com.ithxh.xhh.vo.formbean.BookCollectVo;

public interface BookCollectService extends BaseService<BookCollect, BookCollectVo>{

	public ReturnMessage<Object> getPager(Pager pagerTemp,String userid);
	
}
