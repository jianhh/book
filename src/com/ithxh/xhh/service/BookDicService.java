package com.ithxh.xhh.service;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.xhh.entity.BookDic;
import com.ithxh.xhh.vo.formbean.BookDicVo;

public interface BookDicService extends BaseService<BookDic, BookDicVo>{

	public List<BookDicVo> getListByType(String type);
	
	public List<BookDicVo> getListByFatherId(String fatherId);
}
