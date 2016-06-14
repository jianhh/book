package com.ithxh.xhh.controller.back;

import java.util.List;

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
import com.ithxh.xhh.service.BookDicService;
import com.ithxh.xhh.service.BookRoleService;
import com.ithxh.xhh.service.BookUserRoleService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.vo.formbean.BookDicVo;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Controller
@RequestMapping("/userBack")
public class UserBackCtrl extends BaseController {

	@Autowired
	BookUserService	bookUserService;
	
	@Autowired
	BookUserRoleService bookUserRoleService;
	
	@Autowired
	BookRoleService bookRoleService;
	
	@Autowired
	BookDicService bookDicService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	
	/**
	 * @Description:  进入用户列表
	 * @author: 何建辉
	 * @date 2016年2月8日 下午10:43:29
	 * @param @return
	 */
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String index() {

		return "operating/userList";
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
		
		ReturnMessage<Object> rm = bookUserService.getPager(pagerTemp);
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
	public String toEdit(@RequestParam(value = "userId", required = false) String userId) {
		
		if(StringUtil.isEmpty(userId)){
			//所有角色
			List<BookRoleVo> allList = bookRoleService.getAll();
			this.set("allList", allList);
			//获取所有学校
			List<BookDicVo> schoolList = bookDicService.getListByType("school");
			this.set("sList", schoolList);
			return "operating/userEditor";
		}
		
		//获取该角色的信息
		BookUserVo userVo = bookUserService.getById(userId);
		if(userVo==null){
			throw new PageNotFoundException();
		}
		//该用户的角色
		BookRoleVo bookRoleVo = bookUserRoleService.getBookRoleVoByUserId(userId);
		userVo.setRoleVo(bookRoleVo);
		this.set("obj", userVo);
		//所有角色
		List<BookRoleVo> allList = bookRoleService.getAll();
		this.set("allList", allList);
		//获取所有学校
		List<BookDicVo> schoolList = bookDicService.getListByType("school");
		this.set("sList", schoolList);
		//获取该学校的所有学院
		List<BookDicVo> list = bookDicService.getListByFatherId(userVo.getSchoolid());
		this.set("cList", list);
		//用户头像
		UploadFileVo pic = uploadFileService.getById(userVo.getUserpic());
		this.set("pic", pic);
		return "operating/userEditor";
	}
	
	
	@RequestMapping(value="doListByFatherId",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<BookDicVo> doCollegeListByFatherId(@RequestParam String fatherId){
		List<BookDicVo> list = bookDicService.getListByFatherId(fatherId);
		ReturnMessage<BookDicVo> rm = new ReturnMessage<BookDicVo>();
		rm.setList(list);
		rm.setResult(true);
		return rm;
	}
	
	
	/**
	 * @Description: 预览界面
	 * @author: 何建辉
	 * @date 2016年2月5日 下午2:00:40
	 * @param @param roleId
	 * @param @return
	 */
	@RequestMapping(value = "/toPreview", method = RequestMethod.GET)
	public String toPreview(@RequestParam(value = "userId", required = false) String userId) {
		//获取该用户的信息
		BookUserVo userVo = bookUserService.getById(userId);
		if(userVo==null){
			throw new PageNotFoundException();
		}
		//该用户的角色
		BookRoleVo bookRoleVo = bookUserRoleService.getBookRoleVoByUserId(userId);
		userVo.setRoleVo(bookRoleVo);
		this.set("obj", userVo);
		//该用户的学校和学院
		BookDicVo school = bookDicService.getById(userVo.getSchoolid());
		BookDicVo college = bookDicService.getById(userVo.getCollegeid());
		this.set("school", school);
		this.set("college", college);
		//用户头像
		UploadFileVo pic = uploadFileService.getById(userVo.getUserpic());
		this.set("pic", pic);
		
		return "operating/userView";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> save(BookUserVo userVo) {
		ReturnMessage<Object> rm = null;
		if(StringUtil.isEmpty(userVo.getUserid())){
			rm = bookUserService.add(userVo);
		}else{
			rm = bookUserService.update(userVo);
		}
		
		return rm;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage delete(@RequestParam(value = "userId", required = true) String[] userId) {
		ReturnMessage<Object> rm = bookUserService.del(userId);
		return rm;
	}
	
	@RequestMapping(value = "/doEnable", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doEnable(@RequestParam String id,@RequestParam String value){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		//不能修改自己
		BookUserVo user = (BookUserVo) getBackUserVo();
		if(id.equals(user.getUserid())){
			rm.setResult(false);
			rm.setMessage("不可以修改自己的状态");
			return rm;
		}
		//不能修改超级管理员
		BookRoleVo role = bookUserRoleService.getBookRoleVoByUserId(id);
		if("1".equals(role.getRoleisadmin())){
			rm.setResult(false);
			rm.setMessage("不可以修改超级管理员的状态");
			return rm;
		}
		
		rm= bookUserService.updateOneField("book_user", "ISENABLE", value, id);
		return rm;
	}
}
