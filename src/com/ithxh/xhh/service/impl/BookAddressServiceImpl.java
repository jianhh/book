package com.ithxh.xhh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.entity.BookAddress;
import com.ithxh.xhh.service.BookAddressService;
import com.ithxh.xhh.service.BookAreaService;
import com.ithxh.xhh.vo.formbean.BookAddressVo;
import com.ithxh.xhh.vo.formbean.BookAreaVo;

@Component("bookAddressService")
@Transactional
public class BookAddressServiceImpl extends BaseServiceImpl<BookAddress, BookAddressVo> implements BookAddressService{

	@Autowired
	BookAreaService bookAreaService;
	
	@Override
	public BookAddressVo getById(String id) {
		
		BookAddressVo vo = null;
		try {
			vo = super.getById(id);
			BookAreaVo province = bookAreaService.getById(vo.getProvince());
			vo.setPname(province.getArea());
			
			BookAreaVo city = bookAreaService.getById(vo.getCity());
			vo.setCname(city.getArea());
			
			BookAreaVo county = bookAreaService.getById(vo.getCounty());
			vo.setConame(county.getArea());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
}
