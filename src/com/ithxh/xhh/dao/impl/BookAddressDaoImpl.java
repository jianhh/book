package com.ithxh.xhh.dao.impl;

import org.springframework.stereotype.Component;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookAddressDao;
import com.ithxh.xhh.entity.BookAddress;
import com.ithxh.xhh.vo.formbean.BookAddressVo;

@Component("bookAddressDao")
public class BookAddressDaoImpl extends BaseDaoImpl<BookAddress, BookAddressVo> implements BookAddressDao{

}
