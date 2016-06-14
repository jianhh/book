package com.ithxh.xhh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookValid;
import com.ithxh.xhh.service.BookValidService;
import com.ithxh.xhh.vo.formbean.BookValidVo;

@Service("bookValidService")
@Transactional
public class BookValidServiceImpl extends BaseServiceImpl<BookValid, BookValidVo> implements BookValidService{
	
}
