package com.ithxh.xhh.service.impl;

import java.net.SocketException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.IpUtil;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.dao.BookDicDao;
import com.ithxh.xhh.dao.BookPrivilegeDao;
import com.ithxh.xhh.dao.BookRoleDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.dao.BookUserRoleDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookUser;
import com.ithxh.xhh.entity.BookUserRole;
import com.ithxh.xhh.exception.AddFailException;
import com.ithxh.xhh.exception.BaseException;
import com.ithxh.xhh.exception.DeleteFailException;
import com.ithxh.xhh.exception.SystemBusyException;
import com.ithxh.xhh.exception.UpdateFailException;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.service.BookValidService;
import com.ithxh.xhh.vo.formbean.BookDicVo;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;
import com.ithxh.xhh.vo.formbean.BookRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserRoleVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookValidVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookUserService")
@Transactional
public class BookUserServiceImpl extends BaseServiceImpl<BookUser, BookUserVo> implements BookUserService{
	
	@Autowired
	private BookUserDao bookUserDao;
	
	@Autowired
	private BookUserRoleDao bookUserRoleDao;
	
	@Autowired
	private BookPrivilegeDao bookPrivilegeDao;
	
	@Autowired
	private UploadFileDao uploadFileDao;
	
	@Autowired
	private BookValidService bookValidService;
	
	@Autowired
	private BookDicDao bookDicDao;
	
	@Autowired
	private BookRoleDao BookRoleDao;
	
	@Override
	public BookUserVo getById(String id) {
		
		try {
			BookUserVo userVo = super.getById(id);
			//获取用户头像
			if(!StringUtil.isEmpty(userVo.getUserpic())){
				UploadFileVo pic = uploadFileDao.findById(userVo.getUserpic());
				userVo.setUserpicpath(pic.getUploadfilepath());
			}
			
			//获取用户学校学院
			if(!StringUtil.isEmpty(userVo.getSchoolid())){
				BookDicVo school = bookDicDao.findById(userVo.getSchoolid());
				userVo.setSchoolname(school.getDicname());
			}
			if(!StringUtil.isEmpty(userVo.getCollegeid())){
				BookDicVo college = bookDicDao.findById(userVo.getCollegeid());
				userVo.setCollegename(college.getDicname());
			}
			return userVo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public ReturnMessage<BookUserVo> login(String userName, String password) {
		
		ReturnMessage<BookUserVo> rm = new ReturnMessage<BookUserVo>();    // 要返回的对象
		BookUserVo bookUserVo = new BookUserVo(); // 要返回的对象中的对象
		
		BookUserVo uservo = bookUserDao.getBookUserVoByUserName(userName);
		
		if(uservo==null){
			rm.setResult(false);
			rm.setMessage("该用户不存在");
			return rm;
		}
		
		//判断是否已经邮箱激活
		if(uservo.getIsactive().equals("0")){
			rm.setResult(false);
			rm.setMessage("该账号尚未激活");
			return rm;
		}
		
		//判断是否被启用
		if(uservo.getIsenable().equals("no")){
			rm.setResult(false);
			rm.setMessage("该账号已经被禁用,请联系管理员");
			return rm;
		}
		
		if(!/*DigestUtils.md5(password)*/password.equals(uservo.getPassword())){
			rm.setResult(false);
			rm.setMessage("密码不正确");
			return rm;
		}
		
		//修改信息
		try {
			String ip = IpUtil.getRealIp();
			if(ip==null){
				ip = "127.0.0.1";
			}
			bookUserDao.updateTableOneField(new String[]{"LASTLOGINTIME","LOGINTIMES","USERLASTLOGINIP"}, new Object[]{DateUtils.getNowDateTime(),uservo.getLogintimes()+1,ip}, uservo.getUserid());
		} catch (SocketException | SQLException e) {
			e.printStackTrace();
		}
		
		BeanUtils.copyProperties(uservo,bookUserVo);
		
		//获取头像
		try {
			UploadFileVo pic = uploadFileDao.findById(bookUserVo.getUserpic());
			bookUserVo.setUserpicpath(pic.getUploadfilepath());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 拼接栏目
		List<BookPrivilegeVo> listColumn = null;
		//获取该用户角色
		try {
			BookUserRoleVo roleVo = bookUserRoleDao.findByFieldName("USERID", bookUserVo.getUserid());
			if(roleVo!=null){
				BookRoleVo role = BookRoleDao.findById(roleVo.getRoleid());
				if("1".equals(role.getRoleisadmin())){
					//超级管理员
					listColumn = bookPrivilegeDao.getAllList();
				}else{
					//非超级管理员
					listColumn = bookPrivilegeDao.getPrivilegeListByRoleId(role.getRoleid());
				}
				bookUserVo.setpList(listColumn);
			}
		} catch (SQLException e1) {
			throw new SystemBusyException(e1);
		}
		
		if(!ListUtil.isEmpty(listColumn)){
			//拼接权限树
			bookUserVo.setListPrivilegeVo(getFormatBookPrivilege(listColumn));
		}
		
		rm.setO(bookUserVo);
		rm.setMessage("登录成功！");
		rm.setResult(true);  // 表示登录成功
		return rm;
	}
	
	
	/**
	 * @Description: 组装栏目表,将子列表放到父类里面 ，返回给页面  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:59:06
	 * @param @param list
	 * @param @return
	 */
	public static List<BookPrivilegeVo> getFormatBookPrivilege(List<BookPrivilegeVo> list) {

		if (list == null || list.size() <= 0)   return null;
		List<BookPrivilegeVo> listResult = new ArrayList<BookPrivilegeVo>(); // 存放结果

		// 将等于 0 的 最高层 取出来。
		for (BookPrivilegeVo bp : list) {
			if (StringUtil.isEmpty(bp.getPrivilegefatherid())) {
				listResult.add(bp);
			}
		}

		// 将 不等于0 放进等于0 的里面去
		List<BookPrivilegeVo> listResultSon = null;
		List<BookPrivilegeVo> removeList = new ArrayList<BookPrivilegeVo>();
		for (BookPrivilegeVo scv : listResult) // 循环等于零的
		{

			listResultSon = new ArrayList<BookPrivilegeVo>(); // 存放结果
			for (BookPrivilegeVo bp : list) // 循环不等于0 的
			{
				if (scv.getPrivilegeid().equals(bp.getPrivilegefatherid())) { // 如果父ID等于 空的那个对象的ID
					listResultSon.add(bp);
				}
			}
			if(!ListUtil.isEmpty(listResultSon)){
				scv.setList(listResultSon);
			}else {
				removeList.add(scv);
			}
		}
		listResult.removeAll(removeList);
		return listResult;
	}


	@Override
	@Transactional
	public ReturnMessage<Object> add(BookUserVo obj) {
		//判断图片是否存在
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo vo1 = null;
		try {
			//判断用户名是否重复
			BookUserVo vo = super.findByFieldName("username", obj.getUsername());
			if(vo!=null){
				rm.setResult(false);
				rm.setMessage("该用户名已经存在，请重新填写");
				return rm;
			}
			vo1 = super.findByFieldName("email", obj.getEmail());
			if(vo1!=null){
				rm.setResult(false);
				rm.setMessage("该邮箱已经被绑定，请重新填写");
				return rm;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		//添加用户
		obj.setUserid(IDGenerator.uuidGenerate());
		obj.setIsenable("yes");
		obj.setIsqq("no");
		obj.setLogintimes(0L);
		obj.setLastlogintime(DateUtils.getNowDateTime());
		obj.setRegistertime(DateUtils.getNowDateTime());
		BookUser user = new BookUser();
		BeanUtils.copyProperties(obj, user);
		int i = 0;
		try {
			i = bookUserDao.insert(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return ReturnMessage.get(ReturnMsg.ADD_FAIL);
		}
		if(i==1){
			//添加角色信息
			if(obj.getRoleVo()!=null){
				BookUserRole userRole = new BookUserRole();
				userRole.setRoleid(obj.getRoleVo().getRoleid());
				userRole.setUserid(obj.getUserid());
				try {
					int insert = bookUserRoleDao.insert(userRole);
					if(insert<1){
						throw new AddFailException();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new AddFailException(e);
				}
			}else {
				throw new AddFailException("参数信息错误，请重新刷新填写");
			}
		}
		//修改图片状态
		try {
			uploadFileDao.updateTableOneField("book_upload", "uploadstatus", "1", user.getUserpic());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ReturnMessage.get(ReturnMsg.ADD_SUCCESS);
	}
	

	@Override
	@Transactional
	public ReturnMessage<Object> update(BookUserVo obj) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo vo1 = null;
		try {
			BookUserVo userVo = bookUserDao.findById(obj.getUserid());
			
			//判断用户名是否重复
			if(!userVo.getUsername().equals(obj.getUsername())){
				BookUserVo vo = super.findByFieldName("username", obj.getUsername());
				if(vo!=null){
					rm.setResult(false);
					rm.setMessage("该用户名已经存在，请重新填写");
					return rm;
				}
			}
			if(!userVo.getEmail().equals(obj.getEmail())){
				vo1 = super.findByFieldName("email", obj.getEmail());
				if(vo1!=null){
					rm.setResult(false);
					rm.setMessage("该邮箱已经被绑定，请重新填写");
					return rm;
				}
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		int i = 0;
		BookUser user = new BookUser();
		try {
			//添加用户
			obj.setIsenable("yes");
			BeanUtils.copyProperties(obj, user);
			
			i = bookUserDao.updateTable(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
		}
		if(i==1 && !"1".equals(obj.getUserid())){
			//添加角色信息
			if(obj.getRoleVo()!=null){
				BookUserRole userRole = new BookUserRole();
				userRole.setRoleid(obj.getRoleVo().getRoleid());
				userRole.setUserid(obj.getUserid());
				
				try {
					//删除旧的角色
					bookUserRoleDao.deleteByOneField("USERID", obj.getUserid());
					//添加新的角色
					int insert = bookUserRoleDao.insert(userRole);
					if(insert<1){
						throw new UpdateFailException();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new UpdateFailException(e);
				}
			}else {
				//删除角色
				try {
					bookUserRoleDao.deleteByOneField("USERID", obj.getUserid());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//修改图片状态
		try {
			uploadFileDao.updateTableOneField("book_upload", "uploadstatus", "1", user.getUserpic());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS);
	}
	
	
	@Transactional
	public ReturnMessage<Object> doReg(BookUserVo userVo){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		//判断用户名和邮箱是否重复
		try {
			BookUserVo vo = super.findByFieldName("username", userVo.getUsername());
			if(vo!=null){
				rm.setResult(false);
				rm.setMessage("该用户名已经存在，请重新填写");
				return rm;
			}
			BookUserVo vo1 = super.findByFieldName("email", userVo.getEmail());
			
			if(vo1!=null){
				rm.setResult(false);
				rm.setMessage("该邮箱已经被绑定，请重新填写");
				return rm;
			}
			
			//注册成功
			userVo.setUserid(IDGenerator.uuidGenerate());
			userVo.setIsactive("0");
			userVo.setIsenable("no");
			userVo.setIsqq("no");
			userVo.setUserpic("defaultuser");
			userVo.setLogintimes(0L);
			
			rm = super.add(userVo);
			rm.setO(userVo);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BaseException("注册失败");
		}
		
		return rm;
	}


	@Override
	@Transactional
	public ReturnMessage<Object> del(String[] ids) {
		//先删除用户
		ReturnMessage<Object> rm = super.del(ids);
		if(rm.isResult()){
			//再删除用户角色表信息
			for(String id:ids){
				try {
					int del = bookUserRoleDao.deleteByOneField("USERID", id);
					if(del<1){
						throw new DeleteFailException();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DeleteFailException();
				}
			}
		}else{
			throw new DeleteFailException();
		}
		return rm;
	}
	
	
	public ReturnMessage<Object> validate(String code){
		BookValidVo vo = bookValidService.getByFieldName("validcode", code);
		if(vo==null || "1".equals(vo.getStatus())){
			ReturnMessage<Object> rm = ReturnMessage.get(ReturnMsg.BASE_FALSE,"对不起，该链接已经失效");
			rm.setState("false");
			return rm;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp start = DateUtils.getTimestamp(sdf.parse(vo.getStarttime()));
			Timestamp end = DateUtils.getTimestamp(sdf.parse(vo.getEndtime()));
			Timestamp now = DateUtils.getTimestamp();
			
			if(start.before(now) && end.after(now)){
				//在规定时间内激活，需要验证原来账户是否激活过了
				ReturnMessage<Object> rm = new ReturnMessage<Object>();
				BookUserVo userVo = bookUserDao.findById(vo.getUserid());
				if(userVo==null){
					rm.setState("false");
					rm.setMessage("对不起，该链接已经失效了");
				}else{
					if(userVo.getIsactive().equals("1")){
						rm.setState("false");
						rm.setMessage("抱歉，该链接已经失效且账号已经激活了");
					}else{
						//需要修改激活成功的状态,同时修改该验证码的状态 删除
						bookValidService.updateOneField("book_validate", "status", "0", vo.getId());
						bookValidService.del(vo.getId());
						bookUserDao.updateTableOneField("book_user", "isenable", "yes", userVo.getUserid());
						bookUserDao.updateTableOneField("book_user", "isactive", "1", userVo.getUserid());
						rm.setState("true");
						rm.setMessage("恭喜您，邮箱激活成功");
					}
				}
				return rm;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ReturnMessage<Object> rm = ReturnMessage.get(ReturnMsg.BASE_FALSE,"对不起，该链接已经失效");
		rm.setState("false");
		return rm;
	}
	
	
	public ReturnMessage<Object> completeReg(BookUserVo vo){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		try {
			int i = bookUserDao.updateTableOneField(new String[]{"PHONENUMBER","SHORTPHONENUMBER","SCHOOLID","COLLEGEID","REGISTERTIME"}, new String[]{vo.getPhonenumber(),vo.getShortphonenumber()
					,vo.getSchoolid(),vo.getCollegeid(),DateUtils.getNowDateTime()}, vo.getUserid());
			if(i<1){
				rm.setResult(false);
				rm.setMessage("系统繁忙，请稍后再试");
			}else{
				rm.setResult(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rm;
	}
	
}
