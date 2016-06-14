package com.ithxh.xhh.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.service.BookDicService;
import com.ithxh.xhh.vo.formbean.BookDicVo;

/**
 * @category 角色管理 任何机构管理员
 * @author z
 * @date 2015-6-19
 * @time 下午2:19:59
 */
@Controller
@RequestMapping("/schoolBack")
public class SchoolBackCtrl extends BaseController {
	
	@Autowired
	BookDicService bookDicService;

	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toList() {
		
		List<BookDicVo> list = this.bookDicService.getAll();
		this.set("dicList",list);
		
		
		return "operating/schoolList";
	}

	@RequestMapping(value = "/doList")
	@ResponseBody
	public ReturnMessage<Object> doList(Pager pager) {

		// 2)对这些下面的关键字执行准确查询
		ReturnMessage<Object> rm = bookDicService.getPager(pager);
		return rm;
	}
	
	
	@RequestMapping(value = "/doEditor", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> edit(BookDicVo vo) {

		ReturnMessage<Object> rm = this.bookDicService.update(vo);

		return rm;
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> add(BookDicVo vo) {
		vo.setDicid(IDGenerator.uuidGenerate());
		if(vo.getDicremark().equals("1")){
			vo.setDictype("school");
		}else {
			vo.setDictype("college");
		}
		ReturnMessage<Object> rm = bookDicService.add(vo);
		if(rm.isResult()){
			rm.setO(vo);
		}
		return rm;
	}
	
	@RequestMapping(value="doDelete",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doDelete(@RequestParam String id){
		ReturnMessage<Object> rm = bookDicService.del(id);
		return rm;
	}
}
