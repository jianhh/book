package com.ithxh.xhh.dao.htmlparser;

import java.util.List;

import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.vo.formbean.BookVo;

/**
 * @description 解析书籍接口 
 * @author hxh
 * @date 2016年3月31日 上午1:15:19
 */
public interface BaseParserBook {
	
	/**
	 * @Description:  解析指定集合的书籍页面信息
	 * @author: 何建辉
	 * @date 2016年3月23日 下午2:57:17
	 * @param @param urlList
	 * @param @return
	 */
	public abstract ReturnMessage<BookVo> parse(List<String> urlList);

	/**
	 * @Description: 解析每一本书籍的页面地址转成bookvo  
	 * @author: 何建辉
	 * @date 2016年3月23日 上午1:10:21
	 * @param @param isbnOrName
	 * @param @return
	 */
	public abstract ReturnMessage<BookVo> parse(String isbnOrName);

	/**
	 * @Description:  解析查询页面（包含多本书籍），获取所有书籍的详细页面地址保存到bookurllist集合中
	 * @author: 何建辉
	 * @date 2016年3月23日 上午1:04:06
	 * @param @param isbnOrName
	 * @param @return
	 */
	public abstract ReturnMessage<String> parseSearchPage(String isbnOrName);

}