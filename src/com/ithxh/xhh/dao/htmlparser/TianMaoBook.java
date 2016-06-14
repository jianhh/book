package com.ithxh.xhh.dao.htmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.HtmlParserConst;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@SuppressWarnings("serial")
public class TianMaoBook extends BaseParser{

private static Logger logger = Logger.getLogger(TianMaoBook.class.getName());

	private static String bookname = ".breadcrumb>span";
	private static String bookisbn = ".p-parameter-list>li";
	private static String bookoldprice = "#page_maprice";
	private static String bookauthor = "#p-author";
	private static String bookpic = "#spec-n1>img";
	private static String bookintro = ".book-detail-content";
	private static String type = ".breadcrumb>span>a['clstag']";
	private static List<String> bookurllist;
	private static String parseurl = "#J_goodsList>li>.p-name>a";
	
	/**
	 * @Description: 解析每一本书籍的页面地址转成bookvo  
	 * @author: 何建辉
	 * @date 2016年3月23日 上午1:10:21
	 * @param @param isbnOrName
	 * @param @return
	 */
	public ReturnMessage<BookVo> parse(String isbnOrName){
		ReturnMessage<BookVo> rm = new ReturnMessage<BookVo>();
		boolean b = parseSearchPage(isbnOrName);
		if(!b){
			rm.setResult(false);
			rm.setMessage("没有获取到该书籍");
			return rm;
		}
		List<BookVo> bookList = new ArrayList<BookVo>();
		//解析每一个本书
		for(String url : bookurllist){
			try {
				BookVo bookVo = new BookVo();
				Document doc = Jsoup.connect(url).get();
				Element nameeElement = doc.select(bookname).get(1);
				bookVo.setBookname(nameeElement.text());
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
				
				//原价
				Element priceeElement = doc.select(bookoldprice).first();
				String price = priceeElement.text().trim();
				bookVo.setBookoldprice(Double.valueOf(price.substring(1)));
				//作者
				Element authorElement = doc.select(bookauthor).first();
				String author = authorElement.text();
				bookVo.setBookauthor(author);
				//简介
				Element introElement = doc.select(bookintro).first();
				bookVo.setBookintro(introElement.text());
				//类型
				Element firsttype = doc.select(type).first();
				Element secondtype = doc.select(type).get(1);
				List<String> list = new ArrayList<String>();
				list.add(firsttype.text().trim());
				list.add(secondtype.text().trim());
				bookVo.setTypes(list);
				
				//图片
				Element picElement = doc.select(bookpic).first();
				String picpath = picElement.attr("src");
				if(picpath.indexOf("http:")==-1){
					picpath = "http:"+picpath;
				}
				//远程获取图片
				UploadFileVo uploadfile = parseImg(picpath);
				bookVo.setNetpic(uploadfile);
				bookList.add(bookVo);
			} catch (IOException e) {
				logger.warn("----- 京东 获取详细书籍信息失败  ------");
			}
		}
		
		
		return rm;
	}
	
	
	/**
	 * @Description:  解析查询页面（包含多本书籍），获取所有书籍的详细页面地址保存到bookurllist集合中
	 * @author: 何建辉
	 * @date 2016年3月23日 上午1:04:06
	 * @param @param isbnOrName
	 * @param @return
	 */
	public static boolean parseSearchPage(String isbnOrName){
		
		try {
			
			Document doc = Jsoup.connect(HtmlParserConst.JINGDONG+isbnOrName).get();
			Elements elements = doc.select(parseurl);
			if(elements.isEmpty()){
				logger.warn("----- 京东 没有获取到信息 ------");
				return false;
			}else{
				//把查询页面的每一本书籍链接存到list中
				Iterator<Element> it = elements.iterator();
				bookurllist = new ArrayList<String>();
				while(it.hasNext()){
					Element e = it.next();
					bookurllist.add(e.attr("href"));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("----- 京东 获取查询信息失败  ------");
			return false;
		}
		
		return true;
	}
	
	
}
