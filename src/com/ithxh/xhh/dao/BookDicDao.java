package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookDic;
import com.ithxh.xhh.vo.formbean.BookDicVo;

public interface BookDicDao extends BaseDao<BookDic, BookDicVo>{

	public List<BookDicVo> getListByType(String type);
}
