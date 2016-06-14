package com.ithxh.xhh.dao.htmlparser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.HtmlParserConst;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;
/**
 * @description 京东网页解析
 * @author hxh
 * @date 2016年3月31日 上午1:09:17
 */
@SuppressWarnings("serial")
@Repository("jingDongBook")
public class JingDongBook extends BaseParser implements BaseParserBook{
	
	private static Logger logger = Logger.getLogger(JingDongBook.class.getName());
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	private ThreadPoolTaskExecutor  taskExecutor;
	
	private static String bookname = ".jqzoom >img";
	private static String bookisbn = ".p-parameter-list>li";
	private static String bookauthor = "#p-author";
	private static String bookpic = "#spec-n1>img";
	private static String type = ".breadcrumb a[clstag]";
	private static List<String> bookurllist;
	private static String parseurl = ".J-goods-list .gl-item";
	
	public static String getParseurl() {
		return parseurl;
	}

	public static void setParseurl(String parseurl) {
		JingDongBook.parseurl = parseurl;
	}
	
	
	/* (non-Javadoc)
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parse(java.util.List)
	 */
	@Override
	public ReturnMessage<BookVo> parse(List<String> urlList){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		final List<BookVo> bookList = new ArrayList<BookVo>();
		//解析每一个本书
		for(final String url : bookurllist){
			System.out.println("开启时间"+DateUtils.getNowDateTime());
			BookVo b = parseSimpleUrl(url);
			if(b!=null){
				bookList.add(b);
				//只要一本
				rm.setList(bookList);
				rm.setResult(true);
//				return rm;
			}
			System.out.println("结束时间"+DateUtils.getNowDateTime());
		}
		rm.setResult(true);
		rm.setList(bookList);
		return rm;
	}

	private BookVo parseSimpleUrl(String url) {
		try {
			BookVo bookVo = new BookVo();
			if(url.indexOf("http")==-1){
				url = "http:"+url;
			}
			System.out.println(url);
			//保存解析路径来源
			bookVo.setBookorigin(url);
			Document doc = Jsoup.connect(url).get();
			Element element = doc.select("script").get(0);
			//解析该页面，获取该书籍真正信息地址
			String str = element.html();
			int index = str.trim().indexOf("desc: ");
			if(index==-1){
				return null;
			}
			str = str.substring(index+7);
			index = str.indexOf(",");
			str = str.substring(0,index-1);
			String content = parseUrl("http:"+str);
			JSONObject json = null;
			//如果没有解析到书籍基本信息路径，则需返回null，为了保证解析完成，不需要抛出异常
			try {
				json = JSONObject.fromObject(content.substring(9, content.length()-1));
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
			Document d = Jsoup.parse((String)json.get("content"));
			//解析书籍原价，如果没有获取到该节点，需要返回null，不需要继续解析下去
			Elements elements = d.getElementsContainingOwnText("原价");
			if(elements.isEmpty() || elements.first()==null){
				return null;
			}
			//三种原价解析，任何一种都有可能，所以即使解析错误，都不可以抛出异常，而是使用另外一种方式解析。
			String p = "";
			try {
				p = elements.first().parent().nextElementSibling().text();
				if(StringUtil.isDouble(p)){
					bookVo.setBookoldprice(Double.parseDouble(p));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(StringUtil.isEmpty(p) || p.indexOf("售价")!=-1){
					String ownText = elements.first().parent().ownText();
					if(!StringUtil.isEmpty(ownText)){
						p = ownText.substring(1,ownText.length()-1);
						
						if(StringUtil.isDouble(p)){
							bookVo.setBookoldprice(Double.parseDouble(p));
						}
						
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(bookVo.getBookoldprice()==null){
					String ownText = elements.first().text().substring(3);
					p = ownText.substring(0,ownText.length()-1);
					if(StringUtil.isDouble(p)){
						bookVo.setBookoldprice(Double.parseDouble(p));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//如果三种解析方式都不能得到原价，则返回null，进行下一轮解析。
			if(bookVo.getBookoldprice()==null){
				return null;
			}
			
			//原价
			bookVo.setBookoldprice(Double.valueOf(p.trim()));
			
			//简介，简介可以为空
			Elements es = d.getElementsByTag("h3");
			if(!es.isEmpty() && es.size()>=2){
				String text = es.get(2).text();
				if(text.indexOf("内容")!=-1){
					bookVo.setBookintro(es.get(2).nextElementSibling().text().trim());
				}
			}
			//解析书名
			Element nameeElement = doc.select(bookname).get(0);
			bookVo.setBookname(nameeElement.attr("alt").replace(" ", ""));
			//解析出版社，出版时间，isbn，页码
			Elements baseElements = doc.select(bookisbn);
			Iterator<Element> bit = baseElements.iterator();
			while (bit.hasNext()) {
				Element next = bit.next();
				String value = next.text();
				if(value.indexOf("：")==-1){
					continue;
				}
				String[] values = value.split("：");
				if(value.indexOf("出版社")!=-1){
					bookVo.setBookpublish(values[1].trim());
				}else if(value.indexOf("ISBN")!=-1){
					bookVo.setBookisbn(values[1].trim());
				}else if(value.indexOf("出版时间")!=-1){
					bookVo.setBookpublishtime(values[1].trim());
				}else if(value.indexOf("页")!=-1){
					bookVo.setBookpagesize(Integer.valueOf(values[1].trim()));
				}
			}
			
			//作者
			Element authorElement = doc.select(bookauthor).first();
			String author = authorElement.text();
			bookVo.setBookauthor(author.trim());
			//到此为止，重要信息已经解析完毕，需要是否获取到必填信息，如果有一项未能获取到，则解析失败，返回null
			if(StringUtil.isEmpty(bookVo.getBookauthor()) || StringUtil.isEmpty(bookVo.getBookisbn()) || StringUtil.isEmpty(bookVo.getBookpublish())){
				return null;
			}
			
			//类型
			Element firsttype = doc.select(type).get(1);
			Element secondtype = doc.select(type).get(2);
			List<String> list = new ArrayList<String>();
			
			list.add(firsttype.text().trim());
			list.add(secondtype.text().trim());
			bookVo.setTypes(list);
			
			//解析图片地址
			Element picElement = doc.select(bookpic).first();
			String picpath = picElement.attr("src");
			if(picpath.indexOf("http:")==-1){
				picpath = "http:"+picpath;
			}
			//远程获取图片
			UploadFileVo uploadfile = parseImg(picpath);
			
			bookVo.setNetpic(uploadfile);
			return bookVo;
		} catch (IOException e) {
			logger.warn("----- 京东 获取详细书籍信息失败  ------");
			
			return null;
		}
	}
	


	/* (non-Javadoc)
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parse(java.lang.String)
	 */
	@Override
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
	
	
	/* 根据关键字或者isbn码进行解析查询页面，获取多本书籍详情页面地址
	 * @see com.ithxh.xhh.dao.htmlparser.BaseParserBook#parseSearchPage(java.lang.String)
	 */
	@Override
	public ReturnMessage<String> parseSearchPage(String isbnOrName){
		ReturnMessage<String> rm = new ReturnMessage<String>();
		try {
			
			Document doc = Jsoup.connect(HtmlParserConst.JINGDONG+isbnOrName).get();
			Elements elements = doc.select(parseurl);
			if(elements.isEmpty()){
				logger.warn("----- 京东 没有获取到信息 ------");
				rm.setResult(false);
				return rm;
			}else{
				//把查询页面的每一本书籍链接存到list中
				Iterator<Element> it = elements.iterator();
				bookurllist = new ArrayList<String>();
				while(it.hasNext()){
					Element e = it.next();
					e = e.select(".p-name > a").get(0);
					//判断该地址是否解析过
					String u = e.attr("href");
					if(u.indexOf("http:")==-1){
						u = "http:"+u;
					}
					BookVo vo = bookDao.findByFieldName("BOOKORIGIN", u);
					if(vo==null){
						bookurllist.add(u);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("----- 京东 获取查询信息失败  ------");
			rm.setResult(false);
			return rm;
		}
		rm.setResult(true);
		return rm;
	}
	
	public static void main(String[] args) throws URISyntaxException {
		/*ReturnMessage<BookVo> rm = parse("9787111255833");
		
		for(BookVo vo : rm.getList()){
			System.out.println(vo);
		}*/
//			String path = new JindDongBook().getClass().getClassLoader().getResource("").toURI().getPath();
	}
	
}
