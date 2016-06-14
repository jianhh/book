package com.ithxh.xhh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.dao.BookPrivilegeDao;
import com.ithxh.xhh.dao.BookRoleDao;
import com.ithxh.xhh.dao.BookRolePrivilegeDao;
import com.ithxh.xhh.entity.BookRole;
import com.ithxh.xhh.entity.BookRolePrivilege;
import com.ithxh.xhh.exception.AddFailException;
import com.ithxh.xhh.exception.UpdateFailException;
import com.ithxh.xhh.service.BookRoleService;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;
import com.ithxh.xhh.vo.formbean.BookRolePrivilegeVo;
import com.ithxh.xhh.vo.formbean.BookRoleVo;

@Service("bookRoleService")
public class BookRoleServiceImpl extends BaseServiceImpl<BookRole, BookRoleVo> implements BookRoleService{

	@Autowired
	BookRoleDao bookRoleDao;
	
	@Autowired
	BookRolePrivilegeDao bookRolePrivilegeDao;
	
	@Autowired
	BookPrivilegeDao bookPrivilegeDao;
	
	@Transactional
	public ReturnMessage<Object> add(BookRoleVo bookRoleVo,String[] privileges) {
		BookRole role = new BookRole();
		try {
			BeanUtils.copyProperties(role, bookRoleVo);
			role.setRoleid(IDGenerator.uuidGenerate());
			role.setRoleisadmin("0");
			//判断是否重复角色名
			BookRoleVo vo = bookRoleDao.findByFieldName("ROLENAME", role.getRolename());
			if(vo!=null){
				return ReturnMessage.get(ReturnMsg.BASE_FALSE,"角色名称不能重复");
			}
			int insert = super.insert(role);
			if(insert<1){
				return ReturnMessage.get(ReturnMsg.ADD_FAIL);
			}
			List<String> parentList = new ArrayList<String>();
			for (int j = 0; j < privileges.length; j++) {
				//判断是否添加了父权限
				BookPrivilegeVo privilegeVo = bookPrivilegeDao.findById(privileges[j]);
				
				BookRolePrivilegeVo bookRolePrivilegeVo = bookRolePrivilegeDao.findBookRolePrivilegeVoById(bookRoleVo.getRoleid(), privilegeVo.getPrivilegefatherid());
				if(bookRolePrivilegeVo==null){
					boolean b = false;
					for (String p : parentList) {
						if(p.equals(privilegeVo.getPrivilegefatherid())){
							b = true;
							break;
						}
					}
					if(!b){
						parentList.add(privilegeVo.getPrivilegefatherid());
						//添加父权限
						BookRolePrivilege brp = new BookRolePrivilege();
						brp.setRoleid(role.getRoleid());
						brp.setPrivilegeid(privilegeVo.getPrivilegefatherid());
						int in = bookRolePrivilegeDao.insert(brp);
						if(in<1){
							throw new AddFailException();
						}
					}
				}
				BookRolePrivilege obj = new BookRolePrivilege();
				obj.setPrivilegeid(privileges[j]);
				obj.setRoleid(role.getRoleid());
				int i = bookRolePrivilegeDao.insert(obj);
				if(i<1){
					throw new AddFailException();
				}
			}
			return ReturnMessage.get(ReturnMsg.ADD_SUCCESS);
		} catch (Exception e) {
			throw new AddFailException(e);
		}
	}



	public ReturnMessage<Object> update(BookRoleVo bookRoleVo,String[] privileges){
		BookRole role = new BookRole();
		try {
			BeanUtils.copyProperties(role, bookRoleVo);
			role.setRoleisadmin("0");
			int i = bookRoleDao.updateTable(role);
			if(i<1){
				return ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
			}
			//更新
			bookRolePrivilegeDao.deleteByOneField("ROLEID", bookRoleVo.getRoleid());
			for (int j = 0; j < privileges.length; j++) {
				BookRolePrivilege obj = new BookRolePrivilege();
				obj.setPrivilegeid(privileges[j]);
				obj.setRoleid(bookRoleVo.getRoleid());
				int insert = bookRolePrivilegeDao.insert(obj);
				if(insert<1){
					throw new UpdateFailException();
				}
			}
			return ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UpdateFailException(e);
		}
	}
}
