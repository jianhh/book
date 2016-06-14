package com.ithxh.xhh.service;

import java.util.List;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.entity.UploadFile;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

public interface UploadFileService extends BaseService<UploadFile, UploadFileVo>{

	public ReturnMessage<Object> add(List<UploadFileVo> list,String realPath);
}
