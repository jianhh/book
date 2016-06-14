package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.constant.StaticConst.NETBOOKSOURCE;
import com.ithxh.xhh.dao.BookCollectDao;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookSellDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.dao.htmlparser.BaseParserBook;
import com.ithxh.xhh.entity.Book;
import com.ithxh.xhh.entity.BookCollect;
import com.ithxh.xhh.exception.SystemBusyException;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookTypeService;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.vo.formbean.BookCollectVo;
import com.ithxh.xhh.vo.formbean.BookTypeVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookService")
@Transactional
public class BookServiceImpl extends BaseServiceImpl<Book, BookVo> implements BookService{
	
	private static Logger logger = Logger.getLogger(BookServiceImpl.class.getName());
	
	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	BaseParserBook jingDongBook;
	
	@Autowired
	BaseParserBook baiDuBook;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookTypeService bookTypeService;
	
	@Autowired
	BookCollectDao bookCollectDao;
	
	@Autowired
	BookSellDao bookSellDao;
	
	@Override
	public ReturnMessage<Object> getPager(Pager pagerTemp) {
		
		ReturnMessage<Object> rm = super.getPager(pagerTemp);
		
		List<?> list = rm.getPager().getList();
		
		if(ListUtil.isEmpty(list)){
			return rm;
		}
		try {
			for (int i = 0; i < list.size(); i++) {
				BookVo obj = (BookVo) list.get(i);
				UploadFileVo pic = uploadFileDao.findById(obj.getBookpic());
				obj.setBookpicpath(pic.getUploadfilepath());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rm;
	}
	
	/**
	 * @Description: 另起分页  
	 * @author: 何建辉
	 * @date 2016年3月25日 下午11:50:26
	 * @param @param pagerTemp
	 * @param @param isbnorname
	 * @param @return
	 */
	public ReturnMessage<BookVo> getPager(Pager pagerTemp,String isbnorname) {
		
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		int count = bookDao.findCountByIsbnOrName(isbnorname);
		if(count<1){
			rm.setResult(false);
			return rm;
		}
		
		List<BookVo> list = bookDao.findBookVosByIsbnOrName(isbnorname,pagerTemp.getPageSize()*(pagerTemp.getPageNumber()-1),pagerTemp.getPageSize());
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			return rm;
		}
		
		try {
			for (int i = 0; i < list.size(); i++) {
				BookVo obj = (BookVo) list.get(i);
				UploadFileVo pic = uploadFileDao.findById(obj.getBookpic());
				obj.setBookpicpath(pic.getUploadfilepath());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pagerTemp.setTotalCount(count);
		pagerTemp.setList(list);
		pagerTemp.setPageCount(count*pagerTemp.getPageSize()+1);
		rm.setPager(pagerTemp);
		rm.setResult(true);
		return rm;
	}

	@Override
	public List<BookVo> getByOrderAndLength(String order, String start,
			String end) {
		
		List<BookVo> list = super.getByOrderAndLength(order, start, end);
		
		if(ListUtil.isEmpty(list)){
			return list;
		}
		try {
			for (int i = 0; i < list.size(); i++) {
				BookVo obj = (BookVo) list.get(i);
				UploadFileVo pic = uploadFileDao.findById(obj.getBookpic());
				obj.setBookpicpath(pic.getUploadfilepath());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BookVo getById(String id) {
		
		try {
			BookVo vo = super.getById(id);
			
			if(vo==null){
				return null;
			}
			bookDao.updateTableOneFieldAddOne("book", "bookpv", vo.getBookid());
			UploadFileVo file = uploadFileDao.findById(vo.getBookpic());
			if(file!=null){
				vo.setBookpicpath(file.getUploadfilepath());
			}
			//获取分类
			BookTypeVo typef = bookTypeService.getById(vo.getBooktypeid());
			if(typef==null){
				vo.setBooktypesonname("其他");
			}else{
				vo.setBooktypesonname(typef.getBooktypename());
			}
			
			BookTypeVo stype = bookTypeService.getById(typef.getBooktypefatherid());
			if(stype==null){
				vo.setBooktypename("其他");
			}else{
				vo.setBooktypename(stype.getBooktypename());
			}
			//获取出售总数
			int num = bookSellDao.findCountSellByBookid(id);
			vo.setBooknum(num);
			
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description:  从外网获取书籍信息(京东)
	 * @author: 何建辉
	 * @date 2016年3月23日 下午4:49:28
	 * @param @param isbnorname
	 * @param @param realPath
	 * @param @return
	 */
	private ReturnMessage<BookVo> getBooks4JD(String isbnorname,String realPath){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		System.out.println("开始时间"+DateUtils.getNowDateTime());
		rm = jingDongBook.parse(isbnorname);
		System.out.println("结束时间"+DateUtils.getNowDateTime());
		if(rm.isResult()){
			rm = GetBookVoList4Net(rm.getList(),realPath);
		}
		
		return rm;
	}
	
	/**
	 * @Description:  从外网获取书籍信息(百度)
	 * @author: 何建辉
	 * @date 2016年3月23日 下午4:49:28
	 * @param @param isbnorname
	 * @param @param realPath
	 * @param @return
	 */
	private ReturnMessage<BookVo> getBooks4BD(String name,String realPath){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		System.out.println("开始时间"+DateUtils.getNowDateTime());
		rm = baiDuBook.parse(name);
		System.out.println("结束时间"+DateUtils.getNowDateTime());
		if(rm.isResult()){
			rm = GetBookVoList4Net(rm.getList(),realPath);
		}
		
		return rm;
	}

	
	/**
	 * @Description:  从外网获取书籍信息（京东，百度，豆瓣，天猫···）
	 * @author: 何建辉
	 * @date 2016年3月24日 下午7:32:11
	 * @param @param realPath
	 * @param @param rm
	 */
	private ReturnMessage<BookVo> GetBookVoList4Net(List<BookVo> list,String realPath) {
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		logger.info("排重前："+list.size()+"个");
		if(!ListUtil.isEmpty(list)){
			list = new BookVo().getListDeleteRepeat(list);
		}
		logger.info("排重后："+list.size()+"个");
		
		//
		if(list.size()==0){
			rm.setResult(false);
			rm.setMessage("抱歉，没有获取到您要的数据");
			return rm;
		}
		
		List<UploadFileVo> uploadFileVos = new ArrayList<UploadFileVo>();
		for(BookVo b:list){
			//填充id 时间
			b.setBookaddtime(DateUtils.getNowDateTime());
			b.setBookid(IDGenerator.uuidGenerate());
			UploadFileVo fileVo = b.getNetpic();
			fileVo.setUploadid(IDGenerator.uuidGenerate());
			fileVo.setUploadtime(DateUtils.getNowDateTime());
			b.setBookpic(fileVo.getUploadid());
			uploadFileVos.add(fileVo);
			b.setBookpicpath(fileVo.getUploadfilepath());
			//填充分类,如果没获取到分类则默认设置 “其他”分类
			List<String> types = b.getTypes();
			if(ListUtil.isEmpty(types) || types.size()<1){
				b.setBooktypeid("00");
			}else{
				//根据类型名，获取对象
				BookTypeVo vo = bookTypeService.getByFieldName("booktypename", types.get(0));
				if(vo==null){
					//如果父类型为空，则添加父子 类型
					vo = new BookTypeVo();
					vo.setBooktypefatherid("-1");
					vo.setBooktypeid(IDGenerator.uuidGenerate());
					vo.setBooktypename(types.get(0));
					bookTypeService.add(vo);
					BookTypeVo svo = new BookTypeVo();
					svo.setBooktypefatherid(vo.getBooktypeid());
					svo.setBooktypeid(IDGenerator.uuidGenerate());
					svo.setBooktypename(types.get(1));
					bookTypeService.add(svo);
					b.setBooktypeid(svo.getBooktypeid());
				}else{
					
					BookTypeVo vo1 = bookTypeService.getByFieldName("booktypename", types.get(1));
					if(vo1==null){
						vo1 = new BookTypeVo();
						vo1.setBooktypeid(IDGenerator.uuidGenerate());
						vo1.setBooktypefatherid(vo.getBooktypeid());
						vo1.setBooktypename(types.get(1));
						bookTypeService.add(vo1);
					}
					b.setBooktypeid(vo1.getBooktypeid());
				}
				
			}
			
		}
		ReturnMessage<Object> rmm = uploadFileService.add(uploadFileVos);
		if (!rmm.isResult()) {
			logger.warn("------ 获取信息成功，但是上传图片失败 ------");
			rm.setResult(false);
			rm.setMessage("获取信息失败");
		}else{
			//保存书籍信息 ，由于即使抛错，还是需要保存下来，内容宝贵嘛，这里就不抛异常出发回滚了
			ReturnMessage<Object> message = add(list);
			if(!message.isResult()){
				logger.warn("------ 获取信息成功，但是保存书籍信息失败 共:"+list.size()+"本，成功："+message.getState()+"本，失败多少本自己算------");
				rm.setResult(false);
				rm.setMessage("获取信息失败");
			}else{
				//保存分类
				
				logger.warn("------ 获取信息成功，保存书籍信息成功 成功："+list.size()+"本------");
				rm.setResult(true);
				Pager pager = new Pager();
				pager.setList(list);
				pager.setTotalCount(list.size());
				rm.setPager(pager);
			}
		}
		return rm;
	}

	
	/**
	 * @Description:  根据isbn或者bookname搜索
	 * @author: 何建辉
	 * @date 2016年3月24日 下午7:46:44
	 * @param @param islocal 是否搜索本地，false不搜索，true搜索 如果搜索，没有则搜索外网，如果不搜索，直接搜索外网
	 * @param @param isbnorname isbn码或者书名bookname
	 * @param @param realPath 服务器路径
	 * @param @param source 搜索那个网站 京东 百度 天猫 豆瓣
	 * @param @return
	 */
	public ReturnMessage<BookVo> getBooksByName(Pager pager,boolean islocal ,String isbnorname,String realPath,NETBOOKSOURCE source){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		//查询本地数据库
		List<BookVo> list = null;
		if(islocal){
			rm = getPager(pager,isbnorname);
			if(rm.isResult()){
				return rm;
			}
		}
		if(ListUtil.isEmpty(list)){
			//本地没有，外网获取
			if(source.equals(NETBOOKSOURCE.BD)){
				rm = getBooks4BD(isbnorname, realPath);
			}else if(source.equals(NETBOOKSOURCE.TM)){
				
			}else if(source.equals(NETBOOKSOURCE.DB)){
				
			}else{
				//默认选京东
				rm = getBooks4JD(isbnorname, realPath);
			}
		}
		
		return rm;
		
	}
	
	
	/**
	 * @Description: 收藏或取消收藏  
	 * @author: 何建辉
	 * @date 2016年3月27日 上午2:57:29
	 * @param @param id
	 * @param @return
	 */
	public ReturnMessage<Object> collect(String bookid,String userid){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		try {
			//判断是否存在该书本
			BookVo vo = bookDao.findById(bookid);
			if(vo==null){
				rm.setResult(false);
				return rm;
			}
			//获取该用户对该书的收藏记录
			List<BookCollectVo> list = bookCollectDao.findByFields(new String[]{"bookid","userid"}, new String[]{bookid,userid}, AndOrOr.AND);
			//如果没有，则添加收藏
			if(ListUtil.isEmpty(list)){
				BookCollect collect = new BookCollect();
				collect.setAddtime(DateUtils.getFormateNowDate("yyyy年MM月dd日"));
				collect.setBookid(bookid);
				collect.setUserid(userid);
				collect.setCollectid(IDGenerator.uuidGenerate());
				int i = bookCollectDao.insert(collect);
				if(i<1){
					rm.setResult(false);
				}else{
					rm.setResult(true);
					rm.setState("true");
				}
			}else{
				//如果有，则删除收藏
				BookCollectVo collectVo = list.get(0);
				int i = bookCollectDao.delete(collectVo.getCollectid());
				if(i<1){
					rm.setResult(false);
				}else{
					rm.setResult(true);
					rm.setState("false");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemBusyException(e);
		}
		return rm;
	}
	
	
	public List<BookTypeVo> getBooktypeList(){
		List<BookTypeVo> all = bookTypeService.getAll();
		List<BookTypeVo> list = getFormatBooktype(all);
		return list;
	}
	
	
	/**
	 * @Description: 组装栏目表,将子列表放到父类里面 ，返回给页面  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:59:06
	 * @param @param list
	 * @param @return
	 */
	public static List<BookTypeVo> getFormatBooktype(List<BookTypeVo> list) {

		if (list == null || list.size() <= 0)   return null;
		List<BookTypeVo> listResult = new ArrayList<BookTypeVo>(); // 存放结果

		// 将等于 0 的 最高层 取出来。
		for (BookTypeVo bp : list) {
			if ("-1".equals(bp.getBooktypefatherid())) {
				listResult.add(bp);
			}
		}

		// 将 不等于-1 放进等于-1 的里面去
		List<BookTypeVo> listResultSon = null;
		List<BookTypeVo> removeList = new ArrayList<BookTypeVo>();
		for (BookTypeVo scv : listResult) // 循环等于零的
		{

			listResultSon = new ArrayList<BookTypeVo>(); // 存放结果
			for (BookTypeVo bp : list) // 循环不等于0 的
			{
				if (scv.getBooktypeid().equals(bp.getBooktypefatherid())) { // 如果父ID等于 空的那个对象的ID
					listResultSon.add(bp);
				}
			}
			if(!ListUtil.isEmpty(listResultSon)){
				scv.setList(listResultSon);
			}else {
				removeList.add(scv);
			}
		}
		listResult.removeAll(removeList);
		return listResult;
	}
}
