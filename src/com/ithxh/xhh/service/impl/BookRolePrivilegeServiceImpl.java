package com.ithxh.xhh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookRolePrivilege;
import com.ithxh.xhh.service.BookRolePrivilegeService;
import com.ithxh.xhh.vo.formbean.BookRolePrivilegeVo;

@Service("bookRolePrivilege")
@Transactional
public class BookRolePrivilegeServiceImpl extends BaseServiceImpl<BookRolePrivilege, BookRolePrivilegeVo> implements BookRolePrivilegeService{

}
