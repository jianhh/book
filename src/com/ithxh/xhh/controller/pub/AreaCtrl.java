package com.ithxh.xhh.controller.pub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.service.BookAddressService;
import com.ithxh.xhh.service.BookAreaService;
import com.ithxh.xhh.vo.formbean.BookAddressVo;
import com.ithxh.xhh.vo.formbean.BookAreaVo;

@Controller
@RequestMapping("area")
public class AreaCtrl extends BaseController{
	
	@Autowired
	BookAreaService bookAreaService;
	
	@Autowired
	BookAddressService bookAddressService;

	
	@RequestMapping("toAddress")
	public String toAddress(@RequestParam(required=false)String id){
		if(!StringUtil.isEmpty(id)){
			//获取该地址
			BookAddressVo obj = bookAddressService.getById(id);
			this.set("obj", obj);
			List<BookAreaVo> list = bookAreaService.getListByFieldName("parent_id", "1",true);
			this.set("list", list);
			
			List<BookAreaVo> clist = bookAreaService.getListByFieldName("parent_id", obj.getProvince(),true);
			this.set("clist", clist);
			
			List<BookAreaVo> colist = bookAreaService.getListByFieldName("parent_id", obj.getCity(),true);
			this.set("colist", colist);
		}else{
			List<BookAreaVo> list = bookAreaService.getListByFieldName("parent_id", "1",true);
			this.set("list", list);
			
			List<BookAreaVo> clist = bookAreaService.getListByFieldName("parent_id", list.get(0).getCode()+"",true);
			this.set("clist", clist);
			
			List<BookAreaVo> colist = bookAreaService.getListByFieldName("parent_id", clist.get(0).getCode()+"",true);
			this.set("colist", colist);
		}
		
		
		return "front/order-address";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="getList",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getListByFatherid(@RequestParam(defaultValue="1") String fatherid){
		ReturnMessage rm = grm(ReturnMsg.BASE_TRUE);
		List<BookAreaVo> list = bookAreaService.getListByFieldName("parent_id", fatherid,true);
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
		}else{
			rm.setList(list);
			rm.setResult(true);
		}
		return rm;
	}
	
}
