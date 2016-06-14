package com.ithxh.xhh.dao;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseDao;
import com.ithxh.xhh.entity.BookBuy;
import com.ithxh.xhh.vo.formbean.BookBuyVo;

public interface BookBuyDao extends BaseDao<BookBuy, BookBuyVo>{

	/**
	 * @Description: 获取成功出售的列表信息  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午11:08:11
	 * @param @param userid
	 * @param @param start
	 * @param @param length
	 * @param @return
	 */
	public List<BookBuyVo> findSuccessSellListByUserId(String userid,int start,int length);
	
	public int findCountSuccessSellListByUserId(String userid);
	
	/**
	 * @Description: 获取用户购买书籍列表信息  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午11:08:41
	 * @param @param userid
	 * @param @param start
	 * @param @param length
	 * @param @return
	 */
	public List<BookBuyVo> findBuyListByUserId(String userid,int start,int length);
}
