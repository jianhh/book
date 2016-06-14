package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.PageNotFoundException;
import com.ithxh.xhh.service.BookBuyService;
import com.ithxh.xhh.vo.formbean.BookBuyVo;

@Controller
@RequestMapping("/buyBack")
public class BookBuyBackCtrl extends BaseController {

	@Autowired
	BookBuyService bookBuyService;
	
	/**
	 * @Description:  进入文章列表
	 * @author: 何建辉
	 * @date 2016年2月8日 下午10:43:29
	 * @param @return
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String index() {

		return "operating/bookBuyList";
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
		
		ReturnMessage<Object> rm = bookBuyService.getPager(pagerTemp);
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
	public String toEdit(@RequestParam(value = "id", required = false) String id) {
		
		if(StringUtil.isEmpty(id)){
			
			return "operating/bookBuyEditor";
		}
		
		//获取该文章的信息
		BookBuyVo obj = bookBuyService.getById(id);
		if(obj==null){
			throw new PageNotFoundException();
		}
		this.set("obj", obj);
		return "operating/bookBuyEditor";
	}

}
