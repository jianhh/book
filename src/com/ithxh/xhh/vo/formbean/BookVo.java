package com.ithxh.xhh.vo.formbean;

import java.util.ArrayList;
import java.util.List;

import com.ithxh.baseCore.baseUtils.ListUtil;
// default package
import com.ithxh.baseCore.baseUtils.StringUtil;


/**
 * Book entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookVo implements java.io.Serializable {

	// Fields

	private String bookid;
	private String bookname;
	private String bookisbn;
	private Integer booknum;
	private Double bookoldprice;
	private String bookauthor;
	private String bookpic;
	private String bookaddtime;
	private String bookpublish;
	private String bookpublishtime;
	private Integer bookpagesize;
	private String bookintro;
	private String bookremark;
	private String booktypeid;
	private String booktypename;
	private String bookpicpath;
	private String bookorigin;
	private String booktypesonname;
	private String bookpv;
	private String sellid;
	private int discount;
	private List<String> types;
	
	private UploadFileVo netpic;

	// Constructors

	/** default constructor */
	public BookVo() {
	}
	

	public BookVo(String bookname, String bookisbn, Double bookoldprice) {
		super();
		this.bookname = bookname;
		this.bookisbn = bookisbn;
		this.bookoldprice = bookoldprice;
	}



	// Property accessors
	
	@Override
	public String toString() {
		return "BookVo [bookid=" + bookid + ", bookname=" + bookname
				+ ", bookisbn=" + bookisbn + ", booknum=" + booknum
				+ ", bookoldprice=" + bookoldprice + ", bookauthor="
				+ bookauthor + ", bookpic=" + bookpic + ", bookaddtime="
				+ bookaddtime + ", bookpublish=" + bookpublish
				+ ", bookpublishtime=" + bookpublishtime + ", bookpagesize="
				+ bookpagesize + ", bookintro=" + bookintro + ", bookremark="
				+ bookremark + ", booktypeid=" + booktypeid + ", booktypename="
				+ booktypename + ", bookpicpath=" + bookpicpath + ", types="
				+ types + ", netpic=" + netpic + "]";
	}

	
	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public String getSellid() {
		return sellid;
	}


	public void setSellid(String sellid) {
		this.sellid = sellid;
	}


	public String getBookpv() {
		return bookpv;
	}


	public void setBookpv(String bookpv) {
		this.bookpv = bookpv;
	}


	public String getBooktypesonname() {
		return booktypesonname;
	}


	public void setBooktypesonname(String booktypesonname) {
		this.booktypesonname = booktypesonname;
	}


	public String getBookorigin() {
		return bookorigin;
	}


	public void setBookorigin(String bookorigin) {
		this.bookorigin = bookorigin;
	}


	public String getBookid() {
		return this.bookid;
	}

	public UploadFileVo getNetpic() {
		return netpic;
	}


	public void setNetpic(UploadFileVo netpic) {
		this.netpic = netpic;
	}


	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getBookpublishtime() {
		return bookpublishtime;
	}

	public void setBookpublishtime(String bookpublishtime) {
		this.bookpublishtime = bookpublishtime;
	}

	public String getBooktypename() {
		return booktypename;
	}

	public void setBooktypename(String booktypename) {
		this.booktypename = booktypename;
	}

	public String getBookpicpath() {
		return bookpicpath;
	}

	public void setBookpicpath(String bookpicpath) {
		this.bookpicpath = bookpicpath;
	}

	public String getBooktypeid() {
		return booktypeid;
	}

	public void setBooktypeid(String booktypeid) {
		this.booktypeid = booktypeid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookisbn() {
		return this.bookisbn;
	}

	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}

	public Integer getBooknum() {
		return this.booknum;
	}

	public void setBooknum(Integer booknum) {
		this.booknum = booknum;
	}

	public Double getBookoldprice() {
		return this.bookoldprice;
	}

	public void setBookoldprice(Double bookoldprice) {
		this.bookoldprice = bookoldprice;
	}


	public String getBookauthor() {
		return this.bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookpic() {
		return this.bookpic;
	}

	public void setBookpic(String bookpic) {
		this.bookpic = bookpic;
	}

	public String getBookaddtime() {
		return this.bookaddtime;
	}

	public void setBookaddtime(String bookaddtime) {
		this.bookaddtime = bookaddtime;
	}

	public String getBookpublish() {
		return this.bookpublish;
	}

	public void setBookpublish(String bookpublish) {
		this.bookpublish = bookpublish;
	}

	public Integer getBookpagesize() {
		return this.bookpagesize;
	}

	public void setBookpagesize(Integer bookpagesize) {
		this.bookpagesize = bookpagesize;
	}

	public String getBookintro() {
		return this.bookintro;
	}

	public void setBookintro(String bookintro) {
		this.bookintro = bookintro;
	}

	public String getBookremark() {
		return this.bookremark;
	}

	public void setBookremark(String bookremark) {
		this.bookremark = bookremark;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BookVo){
			BookVo b = (BookVo) obj;
			//判断书名，作者，原价，出版社，出版日期，isbn是否相同，不同，则排除
			if(StringUtil.isEmpty(bookname) && StringUtil.isEmpty(b.getBookname())){
				
			}else if(StringUtil.isEmpty(bookname) || StringUtil.isEmpty(b.getBookname())){
				return false;
			}else if(!bookname.equals(b.getBookname())){
				return false;
			}
			
			if(StringUtil.isEmpty(bookauthor) && StringUtil.isEmpty(b.getBookauthor())){
				
			}else if(StringUtil.isEmpty(bookauthor) || StringUtil.isEmpty(b.getBookauthor())){
				return false;
			}else if(!bookauthor.equals(b.getBookauthor())){
				return false;
			}
			
			if(StringUtil.isEmpty(bookpublish) && StringUtil.isEmpty(b.getBookpublish())){
				
			}else if(StringUtil.isEmpty(bookpublish) || StringUtil.isEmpty(b.getBookpublish())){
				return false;
			}else if(!bookpublish.equals(b.getBookpublish())){
				return false;
			}
			
			if(bookoldprice==null && b.getBookoldprice()==null){
				
			}else if(bookoldprice==null || b.getBookoldprice()==null){
				return false;
			}else if(bookoldprice!=b.getBookoldprice()){
				return false;
			}
			
			return true;
			
			
		}else{
			return false;
		}
	}
	
	private List<BookVo> resultList = new ArrayList<BookVo>();
	public List<BookVo> getListDeleteRepeat(List<BookVo> list){
		
		if(ListUtil.isEmpty(list)){
			List<BookVo> reList = resultList;
			resultList = new ArrayList<BookVo>();
			return reList;
		}
		resultList.add(list.get(0));
		list = deleteElement(list,list.get(0));
		if(ListUtil.isEmpty(list)){
			return resultList;
		}
		getListDeleteRepeat(list);
		return resultList;
	}
	
	
	public static List<BookVo> deleteElement(List<BookVo> list,BookVo vo){
		List<BookVo> delList = new ArrayList<BookVo>();
		for (BookVo v:list) {
			if(v.equals(vo)){
				delList.add(v);
			}
		}
		if(delList.size()!=0){
			list.removeAll(delList);
		}
		return list;
	}
	
}