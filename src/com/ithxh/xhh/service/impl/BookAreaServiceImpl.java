package com.ithxh.xhh.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookArea;
import com.ithxh.xhh.service.BookAreaService;
import com.ithxh.xhh.vo.formbean.BookAreaVo;

@Component("bookAreaService")
@Transactional
public class BookAreaServiceImpl extends BaseServiceImpl<BookArea, BookAreaVo> implements BookAreaService {

	
}
