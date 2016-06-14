package com.ithxh.xhh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookType;
import com.ithxh.xhh.service.BookTypeService;
import com.ithxh.xhh.vo.formbean.BookTypeVo;

@Service("bookTypeService")
@Transactional
public class BookTypeServiceImpl extends BaseServiceImpl<BookType, BookTypeVo> implements BookTypeService{

}
