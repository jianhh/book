package com.ithxh.xhh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookSendEmail;
import com.ithxh.xhh.service.BookSendEmailService;
import com.ithxh.xhh.vo.formbean.BookSendEmailVo;

@Service("bookSendEmailService")
@Transactional
public class BookSendEmailServiceImpl extends BaseServiceImpl<BookSendEmail, BookSendEmailVo> implements BookSendEmailService{
	
}
