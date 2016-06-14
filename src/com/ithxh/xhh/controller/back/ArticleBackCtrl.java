package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.PageNotFoundException;
import com.ithxh.xhh.service.BookArticleService;
import com.ithxh.xhh.vo.formbean.BookArticleVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;

@Controller
@RequestMapping("/articleBack")
public class ArticleBackCtrl extends BaseController {

	@Autowired
	BookArticleService bookArticleService;
	
	/**
	 * @Description:  进入文章列表
	 * @author: 何建辉
	 * @date 2016年2月8日 下午10:43:29
	 * @param @return
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String index() {

		return "operating/articleList";
	}

	/**
	 * @Description: 查询分页
	 * @author: 何建辉
	 * @date 2016年2月5日 下午2:00:06
	 * @param @param pagerTemp
	 * @param @return
	 */
	@RequestMapping(value = "/doList")
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pagerTemp) {
		
		ReturnMessage<Object> rm = bookArticleService.getPager(pagerTemp);
		return rm;
	}

	/**
	 * @Description: 编辑器界面
	 * @author: 何建辉
	 * @date 2016年2月5日 下午2:00:40
	 * @param @param roleId
	 * @param @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(@RequestParam(value = "articleid", required = false) String articleid) {
		
		if(StringUtil.isEmpty(articleid)){
			
			return "operating/articleEditor";
		}
		
		//获取该文章的信息
		BookArticleVo articleVo = bookArticleService.getById(articleid);
		if(articleVo==null){
			throw new PageNotFoundException();
		}
		this.set("obj", articleVo);
		return "operating/articleEditor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> save(BookArticleVo articleVo) {
		ReturnMessage<Object> rm = null;
		if(StringUtil.isEmpty(articleVo.getArticleid())){
			articleVo.setArticleid(IDGenerator.uuidGenerate());
			articleVo.setArticlepv(0);
			articleVo.setArticletime(DateUtils.getNowDateTime());
			BookUserVo backUserVo = (BookUserVo) getBackUserVo();
			articleVo.setArticleuserid(backUserVo.getUserid());
			rm = bookArticleService.add(articleVo);
		}else{
			rm = bookArticleService.update(articleVo);
		}
		
		return rm;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/doDelete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage delete(@RequestParam(value = "articleid", required = true) String[] articleid) {
		ReturnMessage<Object> rm = bookArticleService.del(articleid);
		return rm;
	}
}
