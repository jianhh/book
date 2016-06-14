package com.ithxh.xhh.dao.htmlparser;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.annotation.BussAnnotation;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.HtmlParserConst;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;
/**
 * @description 百度书籍解析 
 * @author hxh
 * @date 2016年3月31日 上午1:17:27
 */
@SuppressWarnings("serial")
@Repository("baiDuBook")
public class BaiDuBook extends BaseParser implements BaseParserBook{
	
	private static Logger logger = Logger.getLogger(BaiDuBook.class.getName());
	
	@Autowired
	private ThreadPoolTaskExecutor  taskExecutor;
	
	@Autowired
	BookDao bookDao;
	
	private static String bookname = ".content-block .book-title";
	private static String bookauthor = ".book-information-tip";
	private static String bookpic = ".doc-info-img";
	private static String type = ".logSend";
	private static String bookintro = ".scaling-content";
	private static List<String> bookurllist;
	private static String parseurl = ".J-goods-list .gl-item";
	
	public static String getParseurl() {
		return parseurl;
	}

	public static void setParseurl(String parseurl) {
		BaiDuBook.parseurl = parseurl;
	}
	
	
	/* (non-Javadoc)
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parse(java.util.List)
	 */
	@Override
	@BussAnnotation(moduleName="解析百度地址 parse(List<String> urlList)",option="解析多个百度详细书籍地址，返回BookVo对象")
	public ReturnMessage<BookVo> parse(List<String> urlList){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		final List<BookVo> bookList = new ArrayList<BookVo>();
		//解析每一个本书
		for(final String url : bookurllist){
			
			BookVo b = parseSimpleUrl(url);
			if(b!=null){
				bookList.add(b);
				/*//只要一本
				rm.setResult(true);
				rm.setList(bookList);
				return rm;*/
			}
			
		}
		logger.info("解析结果："+bookList.size()+"个");
		rm.setResult(true);
		rm.setList(bookList);
		if(ListUtil.isEmpty(bookList)){
			rm.setResult(false);
		}
		return rm;
	}

	@BussAnnotation(moduleName="解析单个百度地址 parseSimpleUrl(String url)",option="解析一个百度详细书籍地址，返回BookVo对象")
	private BookVo parseSimpleUrl(String url) {
		logger.info("正在解析单个url"+url+",开始时间"+DateUtils.getNowDateTime());
		
		try {
			BookVo bookVo = new BookVo();
			bookVo.setBookorigin(url);
			Document doc = Jsoup.connect(url).get();
			//书名
			Element nameeElement = doc.select(bookname).first();
			bookVo.setBookname(nameeElement.ownText());
			//简介
			Element intro = doc.select(bookintro).first();
			bookVo.setBookintro(intro.text().trim());
			
			//分类
			String type1 = "";
			try {
				Element t1 = doc.select(type).get(1);
				type1 = t1.text();
			} catch (Exception e) {
				type1 = "其他";
			}
			String type2 = "";
			try {
				Element t2 = doc.select(type).get(1);
				type2 = t2.text();
			} catch (Exception e) {
				type2 = "其他";
			}
			List<String> types = new ArrayList<String>();
			if(StringUtil.isEmpty(type1)){
				types.add("其他");
				type2 = "其他";
			}else{
				types.add(type1);
			}
			types.add(type2);
			
			bookVo.setTypes(types);
			
			//作者 
			Elements es = doc.select(bookauthor);
			Iterator<Element> it = es.iterator();
			while(it.hasNext()){
				Element next = it.next();
				String name = next.text().replace(" ", "");
				if(name.indexOf("作者")!=-1){
					bookVo.setBookauthor(next.nextElementSibling().text().trim());
				}else if(name.indexOf("出版社")!=-1){
					bookVo.setBookpublish(next.parent().ownText().trim());
				}else if(name.indexOf("出版时间")!=-1){
					bookVo.setBookpublishtime(next.parent().ownText().trim());
				}else if(name.indexOf("定价")!=-1){
					String p = next.parent().ownText().trim();
					p = p.substring(0,p.length()-2);
					if(StringUtil.isDouble(p)){
						bookVo.setBookoldprice(Double.parseDouble(p));
					}
				}
			}
			//如果书名 作者  出版社其中之一没有则返回null
			if(StringUtil.isEmpty(bookVo.getBookname()) || StringUtil.isEmpty(bookVo.getBookauthor()) || StringUtil.isEmpty(bookVo.getBookpublish())){
				return null;
			}
			
			//图片
			Element picElement = doc.select(bookpic).first();
			String picpath = picElement.attr("src");
			if(picpath.indexOf("http:")==-1){
				picpath = "http:"+picpath;
			}
			
			//远程获取图片
			UploadFileVo uploadfile = parseImg(picpath);
			
			bookVo.setNetpic(uploadfile);
			logger.info("解析单个url结束,结束时间"+DateUtils.getNowDateTime());
			return bookVo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("----- 百度 获取详细书籍信息失败  ------");
			return null;
		}
	}
	


	/* (non-Javadoc)
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parse(java.lang.String)
	 */
	@Override
	@BussAnnotation(moduleName="解析单个isbn或者bookname parse(String isbnOrName)",option="")
	public ReturnMessage<BookVo> parse(String isbnOrName){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		ReturnMessage<String> rmm = parseSearchPage(isbnOrName);
		if(!rmm.isResult()){
			rm.setResult(false);
			rm.setMessage("没有获取到该书籍");
			return rm;
		}
		rm = parse(bookurllist);
		
		return rm;
	}
	
	
	/* (non-Javadoc)
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parseSearchPage(java.lang.String)
	 */
	@Override
	public ReturnMessage<String> parseSearchPage(String name){
		logger.info("正在解析查询页面，获取详细页面url");
		ReturnMessage<String> rm = new ReturnMessage<String>();
		String content = parseUrl(HtmlParserConst.BAIDU+name);
		JSONObject json = JSONObject.fromObject(content);
		json = json.getJSONObject("data");
		JSONArray arr = json.getJSONArray("book_list");
		bookurllist = new ArrayList<String>();
		for (int i = 0; i < arr.size(); i++) {
			String u = HtmlParserConst.BAIDU_PRE+(String) arr.getJSONObject(i).get("doc_id");
			//判断该地址是否解析过
			if(u.indexOf("http:")==-1){
				u = "http:"+u;
			}
			BookVo vo = null;
			try {
				vo = bookDao.findByFieldName("BOOKORIGIN", u);
			} catch (SQLException e) {
				e.printStackTrace();
				logger.warn("查询数据库发生异常");
			}
			if(vo==null){
				bookurllist.add(u);
			}else{
				logger.info("该url已经解析过了-->"+u);
			}
		}
		rm.setResult(true);
		logger.info("解析查询页面结束，获取到详细页面url:"+bookurllist.size()+"个");
		return rm;
	}
	
	
	public static ReturnMessage<String> parseSearchPage1(String name){
		ReturnMessage<String> rm = new ReturnMessage<String>();
		String content = parseUrl(HtmlParserConst.BAIDU+name);
		JSONObject json = JSONObject.fromObject(content);
		json = json.getJSONObject("data");
		JSONArray arr = json.getJSONArray("book_list");
		bookurllist = new ArrayList<String>();
		for (int i = 0; i < arr.size(); i++) {
			String u = (String) arr.getJSONObject(i).get("doc_id");
			
			bookurllist.add(HtmlParserConst.BAIDU_PRE+u);
			System.out.println(u);
		}
		rm.setResult(true);
		
		return rm;
	}
	
	public static void main(String[] args) throws URISyntaxException {
		
		parseSearchPage1("看见");
	}
	
}
