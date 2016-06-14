package com.ithxh.xhh.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.PageNotFoundException;
import com.ithxh.xhh.service.BookPrivilegeService;
import com.ithxh.xhh.service.BookRoleService;
import com.ithxh.xhh.service.impl.BookUserServiceImpl;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;
import com.ithxh.xhh.vo.formbean.BookRoleVo;

/**
 * @category 角色管理 任何机构管理员
 * @author z
 * @date 2015-6-19
 * @time 下午2:19:59
 */
@Controller
@RequestMapping("/roleBack")
public class RoleBackCtrl extends BaseController {


	@Autowired
	BookRoleService bookRoleService;
	
	@Autowired
	BookPrivilegeService bookPrivilegeService;
	
	/**
	 * @Description: 进入到角色管理列表首页
	 * @author: 何建辉
	 * @date 2016年2月5日 下午2:00:25
	 * @param @return
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String index() {

		return "operating/roleList";
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
		
		ReturnMessage<Object> rm = bookRoleService.getPager(pagerTemp);
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
	public String toEdit(@RequestParam(value = "roleId", required = false) String roleId) {
		//获取所有权限
		List<BookPrivilegeVo> allList = bookPrivilegeService.getAllList();
		this.set("columnList", BookUserServiceImpl.getFormatBookPrivilege(allList));
		
		if(StringUtil.isEmpty(roleId)){
			return "operating/roleEditor";
		}
		
		//获取该角色的信息
		BookRoleVo roleVo = bookRoleService.getById(roleId);
		if(roleVo==null){
			throw new PageNotFoundException();
		}
		this.set("obj", roleVo);
		
		//获取该角色的权限
		List<BookPrivilegeVo> list = bookPrivilegeService.getPrivilegeListByRoleId(roleId);
		
		if (!ListUtil.isEmpty(list)) {
			roleVo.setList(list);
		} else {
			throw new PageNotFoundException();
		}
		
		return "operating/roleEditor";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> save(BookRoleVo roleVo, String[] privileges) {
		ReturnMessage<Object> rm = null;
		if(StringUtil.isEmpty(roleVo.getRoleid())){
			rm = bookRoleService.add(roleVo, privileges);
		}else{
			rm = bookRoleService.update(roleVo, privileges);
		}
		
		return rm;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage delete(@RequestParam(value = "roleId", required = true) String[] roleId) {
		ReturnMessage<Object> rm = bookRoleService.del(roleId);
		return rm;
	}
}
