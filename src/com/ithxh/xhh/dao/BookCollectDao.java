package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.xhh.entity.BookCollect;
import com.ithxh.xhh.vo.formbean.BookCollectVo;
import com.ithxh.xhh.vo.formbean.BookVo;

public interface BookCollectDao extends BaseDao<BookCollect, BookCollectVo>{

	public List<BookVo> findBookVoListPager(Pager pager,String userid);
	
	public int findCount4Pager(String userid);
}
