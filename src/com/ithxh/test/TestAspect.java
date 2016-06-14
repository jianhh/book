package com.ithxh.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ithxh.xhh.dao.htmlparser.BaiDuBook;
import com.ithxh.xhh.dao.htmlparser.BaseParserBook;

public class TestAspect {

	@Test
	public void testBefore(){
		System.out.println("meia");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		BaiDuBook b = (BaiDuBook) ac.getBean("baiDuBook");
		b.parseSearchPage("看见");
		
	}
}
