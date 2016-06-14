package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.dao.BookAddressDao;
import com.ithxh.xhh.dao.BookBuyDao;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookSellDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookSell;
import com.ithxh.xhh.exception.BaseException;
import com.ithxh.xhh.service.BookAddressService;
import com.ithxh.xhh.service.BookSellService;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.vo.formbean.BookAddressVo;
import com.ithxh.xhh.vo.formbean.BookBuyVo;
import com.ithxh.xhh.vo.formbean.BookSellVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookVo;

@Component("bookSellService")
@Transactional
public class BookSellServiceImpl extends BaseServiceImpl<BookSell, BookSellVo> implements BookSellService{

	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookUserDao bookUserDao;
	
	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	BookSellDao bookSellDao;
	
	@Autowired
	BookBuyDao bookBuyDao;
	
	@Autowired
	BookAddressDao bookAddressDao;
	
	@Autowired
	BookAddressService bookAddressService;
	
	@Autowired
	BookUserService bookUserService;
	
	@Transactional
	public ReturnMessage<Object> doSell(BookSellVo vo){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		//查看关联的书籍是否存在
		try {
			BookVo bookVo = bookDao.findById(vo.getBookid());
			if(bookVo==null){
				rm.setResult(false);
				rm.setMessage("抱歉，提交信息错误，请刷新页面重新提交。");
				return rm;
			}
			//出售
			//如果之前出售过，则+1
			List<BookSellVo> list = bookSellDao.findByFields(new String[]{"bookid","discount","userid","sellstatus"}, new String[]{vo.getBookid(),vo.getDiscount()+"",vo.getUserid(),"0"}, AndOrOr.AND);
			if(!ListUtil.isEmpty(list)){
				//+num
				int i = bookSellDao.updateTableOneFieldAddValue("book_sell", "num", list.get(0).getSellid(), Integer.valueOf(vo.getNum()));
				if(i<1){
					rm.setResult(false);
					rm.setMessage("出售失败，请稍后再试");
					return rm;
				}else{
					//刷新库存
					int j = bookDao.updateTableOneFieldAddValue("book", "booknum", vo.getBookid(), Integer.valueOf(vo.getNum()));
					if(j<1){
						throw new BaseException("出售失败，请稍后再试");
					}else{
						rm.setResult(true);
						return rm;
					}
				}
			}
			
			vo.setSelltime(DateUtils.getNowDateTime());
			vo.setSellid(IDGenerator.uuidGenerate());
			
			vo.setPrice(bookVo.getBookoldprice()*vo.getDiscount()/10);
			BookSell sell = new BookSell();
			BeanUtils.copyProperties(sell, vo);
			
			int i = bookSellDao.insert(sell);
			if(i<1){
				rm.setResult(false);
				rm.setMessage("出售失败，请稍后再试");
				return rm;
			}
			//刷新库存
			int j = bookDao.updateTableOneFieldAddValue("book", "booknum", vo.getBookid(), Integer.valueOf(vo.getNum()));
			if(j<1){
				throw new BaseException("出售失败，请稍后再试");
			}else{
				rm.setResult(true);
				return rm;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException("出售失败，请稍后再试");
		}
	}
	

	/**
	 * @Description: 获取我购买的书籍
	 * @author: 何建辉
	 * @date 2016年4月2日 上午2:08:06
	 * @param @param pagerTemp
	 * @param @param status
	 * @param @param userid
	 * @param @return
	 */
	public ReturnMessage<Object> getBuyListPager(Pager pagerTemp,String userid) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		int start = (pagerTemp.getPageNumber()-1)*pagerTemp.getPageSize();
		List<BookBuyVo> list = bookBuyDao.findBuyListByUserId(userid, start, pagerTemp.getPageSize());
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			return rm;
		}
		try {
			//获取到内容
			for(BookBuyVo vo : list){
				//获取出售消息
				BookSellVo sellVo = bookSellDao.findById(vo.getSellid());
				vo.setBookSellVo(sellVo);
				//获取书籍信息
				BookVo bookVo = bookService.getById(sellVo.getBookid());
				vo.setBookVo(bookVo);
				//获取用户信息(卖家)
				BookUserVo bookUserVo = bookUserService.getById(sellVo.getUserid());
				vo.setBookUserVo(bookUserVo);
			}
			
			List<ArrayList<BookBuyVo>> resultList = new ArrayList<ArrayList<BookBuyVo>>();
			List<String> dateList = new ArrayList<String>();
			//按天书组装lsit
			for(BookBuyVo vo : list){
				
				int index = dateList.indexOf(vo.getBuytime());
				if(index==-1){
					ArrayList<BookBuyVo> li = new ArrayList<BookBuyVo>();
					li.add(vo);
					resultList.add(li);
					dateList.add(vo.getBuytime());
				}else{
					resultList.get(index).add(vo);
				}
			}
			//获取总条数
			int totalNum = bookBuyDao.findCountSuccessSellListByUserId(userid);
			rm.setResult(true);
			pagerTemp.setTotalCount(totalNum);
			pagerTemp.setList(resultList);
			rm.setPager(pagerTemp);
		} catch (SQLException e) {
			e.printStackTrace();
			rm.setResult(false);
		}
		return rm;
	}
	
	
	/**
	 * @Description: 获取正在出售的书籍分页
	 * @author: 何建辉
	 * @date 2016年4月2日 上午2:08:06
	 * @param @param pagerTemp
	 * @param @param status
	 * @param @param userid
	 * @param @return
	 */
	public ReturnMessage<Object> getSellPager(Pager pagerTemp,String userid) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		int start = (pagerTemp.getPageNumber()-1)*pagerTemp.getPageSize();
		List<BookSellVo> list = bookSellDao.findSellListByUser(userid, start, pagerTemp.getPageSize());
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			return rm;
		}
		//获取到内容
		for(BookSellVo vo : list){
			//获取书籍信息
			BookVo bookVo = bookService.getById(vo.getBookid());
			vo.setBookVo(bookVo);
		}

		List<ArrayList<BookSellVo>> resultList = new ArrayList<ArrayList<BookSellVo>>();
		List<String> dateList = new ArrayList<String>();
		//按天数组装lsit
		for(BookSellVo vo : list){
			
			int index = dateList.indexOf(vo.getSelltime().substring(0, 10));
			if(index==-1){
				ArrayList<BookSellVo> li = new ArrayList<BookSellVo>();
				li.add(vo);
				resultList.add(li);
				dateList.add(vo.getSelltime().substring(0, 10));
			}else{
				resultList.get(index).add(vo);
			}
		}
		rm.setResult(true);
		pagerTemp.setList(resultList);
		rm.setPager(pagerTemp);
		return rm;
	}
	
	
	
	public ReturnMessage<Object> getSellerList(Pager pager,String bookid){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		int start = (pager.getPageNumber()-1)*pager.getPageSize();
		List<BookSellVo> list = bookSellDao.findSellListByBookid(bookid, start, pager.getPageSize());
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			return rm;
		}
		//获取到内容
		for(BookSellVo vo : list){
			//获取用户信息
			BookUserVo userVo = bookUserService.getById(vo.getUserid());
			vo.setUserVo(userVo);
		}
		pager.setList(list);
		rm.setPager(pager);
		rm.setResult(true);
		
		return rm;
	}
	
	/**
	 * @Description: 根据书id获取该书出售库存  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午7:59:50
	 * @param @param bookid
	 * @param @return
	 */
	public int getCountSellByBookid(String bookid){
		int i = bookSellDao.findCountSellByBookid(bookid);
		return i;
	}
	
	/**
	 * @Description: 获取我出售成功的书籍，也就是别人购买成功的书籍
	 * @author: 何建辉
	 * @date 2016年4月2日 上午2:08:06
	 * @param @param pagerTemp
	 * @param @param status
	 * @param @param userid
	 * @param @return
	 */
	public ReturnMessage<Object> getSuccessSellPager(Pager pagerTemp,String userid) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		int start = (pagerTemp.getPageNumber()-1)*pagerTemp.getPageSize();
		List<BookBuyVo> list = bookBuyDao.findSuccessSellListByUserId(userid, start, pagerTemp.getPageSize());
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			return rm;
		}
		try {
			//获取到内容
			for(BookBuyVo vo : list){
				//获取出售消息
				BookSellVo sellVo = bookSellDao.findById(vo.getSellid());
				vo.setBookSellVo(sellVo);
				//获取书籍信息
				BookVo bookVo = bookService.getById(sellVo.getBookid());
				vo.setBookVo(bookVo);
				//获取用户信息(购买者)
				BookUserVo bookUserVo = bookUserService.getById(vo.getUserid());
				vo.setBookUserVo(bookUserVo);
				//获取收货地址信息
				BookAddressVo bookAddressVo = bookAddressService.getById(vo.getBookaddressid());
				vo.setBookAddressVo(bookAddressVo);
			}
			
			List<ArrayList<BookBuyVo>> resultList = new ArrayList<ArrayList<BookBuyVo>>();
			List<String> dateList = new ArrayList<String>();
			//按天书组装lsit
			for(BookBuyVo vo : list){
				
				int index = dateList.indexOf(vo.getBuytime());
				if(index==-1){
					ArrayList<BookBuyVo> li = new ArrayList<BookBuyVo>();
					li.add(vo);
					resultList.add(li);
					dateList.add(vo.getBuytime());
				}else{
					resultList.get(index).add(vo);
				}
			}
			//获取总条数
			int totalNum = bookBuyDao.findCountSuccessSellListByUserId(userid);
			rm.setResult(true);
			pagerTemp.setTotalCount(totalNum);
			pagerTemp.setList(resultList);
			rm.setPager(pagerTemp);
		} catch (SQLException e) {
			e.printStackTrace();
			rm.setResult(false);
		}
		return rm;
	}

}
