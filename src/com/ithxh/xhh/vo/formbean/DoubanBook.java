package com.ithxh.xhh.vo.formbean;

import java.util.ArrayList;
import java.util.HashMap;

public class DoubanBook {
	
	private String isbn13;
	private String title;
	private String subtitle;
	private String price;
	private String image;
	private String publisher;
	private ArrayList<String> author;
	private String summary;
	private String pages;
	private String pubdate;
	private ArrayList<String> tags;
	@SuppressWarnings("rawtypes")
	private HashMap images;
	
	
	@SuppressWarnings("rawtypes")
	public HashMap getImages() {
		return images;
	}
	@SuppressWarnings("rawtypes")
	public void setImages(HashMap images) {
		this.images = images;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public ArrayList<String> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	

	
	/*{"rating":{"max":10,"numRaters":0,"average":"0.0","min":0},
		"subtitle":"Java面向对象程序设计","author":["韩雪"],
		"pubdate":"2012-9","tags":[],"origin_title":"",
		"image":"http:\/\/img3.douban.com\/mpic\/s21572543.jpg",
		"binding":"",
		"translator":[],
		"catalog":"",
		"pages":"342",
		"images":{"small":"http:\/\/img3.douban.com\/spic\/s21572543.jpg",
		"large":"http:\/\/img3.douban.com\/lpic\/s21572543.jpg",
		"medium":"http:\/\/img3.douban.com\/mpic\/s21572543.jpg"},
		"alt":"http:\/\/book.douban.com\/subject\/11631416\/",
		"id":"11631416",
		"publisher":"",
		"isbn10":"7115290415",
		"isbn13":"9787115290410",
		"title":"Java面向对象程序设计",
		"url":"http:\/\/api.douban.com\/v2\/book\/11631416",
		"alt_title":"",
		"author_intro":"",
		"summary":"《Java面向对象程序设计(第2版普通高等学校计算机教育十二五规划教材)》由韩雷主编，本书根据Java语言面向对象的本质特征以及面向对象程序设计课程的基本教学要求，在详细阐述面向对象程序设计基本理论和方法的基础上，详细介绍了Java语言及其面向对象的基本特性、基本技术。全书共分为10章，首先介绍了面向对象程序设计、Java语言的基础知识，而后详细讲述Java语言中面向对象思想的实现以及使用，最后介绍了Java图形用户界面、Applet、数据库等相关知识。\n    《Java面向对象程序设计(第2版普通高等学校计算机教育十二五规划教材)》采用大量的实例进行讲解，力求通过实例使读者更形象地理解面向对象思想，快速掌握Java编程技术。本书难度适中，内容由浅入深，实用性强，覆盖面广，条理清晰。每章附有精心编写的实验和习题，便于读者实践和巩固所学知识。本书可作为普通高等院校Java程序设计课程的教材，也可作为读者的自学用书。",
		"price":"42.00元"}
		
		
		{"rating":{"max":10,"numRaters":0,"average":"0.0","min":0},
		"subtitle":"Java面向对象程序设计","author":["韩雪"],
		"pubdate":"2012-9","tags":[],"origin_title":"",
		"image":"http:\/\/img3.douban.com\/mpic\/s21572543.jpg",
		"binding":"",
		"translator":[],
		"catalog":"",
		"pages":"342",
		"images":{"small":"http:\/\/img3.douban.com\/spic\/s21572543.jpg",
			"large":"http:\/\/img3.douban.com\/lpic\/s21572543.jpg",
			"medium":"http:\/\/img3.douban.com\/mpic\/s21572543.jpg"},
			"alt":"http:\/\/book.douban.com\/subject\/11631416\/",
			"id":"11631416","publisher":"","isbn10":"7115290415",
			"isbn13":"9787115290410","title":"Java面向对象程序设计",
			"url":"http:\/\/api.douban.com\/v2\/book\/11631416",
			"alt_title":""
				,"author_intro":"",
			"summary":"《Java面向对象程序设计(第2版普通高等学校计算机教育十二五规划教材)》由韩雷主编，本书根据Java语言面向对象的本质特征以及面向对象程序设计课程的基本教学要求，在详细阐述面向对象程序设计基本理论和方法的基础上，详细介绍了Java语言及其面向对象的基本特性、基本技术。全书共分为10章，首先介绍了面向对象程序设计、Java语言的基础知识，而后详细讲述Java语言中面向对象思想的实现以及使用，最后介绍了Java图形用户界面、Applet、数据库等相关知识。\n    《Java面向对象程序设计(第2版普通高等学校计算机教育十二五规划教材)》采用大量的实例进行讲解，力求通过实例使读者更形象地理解面向对象思想，快速掌握Java编程技术。本书难度适中，内容由浅入深，实用性强，覆盖面广，条理清晰。每章附有精心编写的实验和习题，便于读者实践和巩固所学知识。本书可作为普通高等院校Java程序设计课程的教材，也可作为读者的自学用书。",
			"price":"42.00元"}
*/
}
