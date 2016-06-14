package com.ithxh.xhh.dao.impl;

import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.UploadFile;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Repository("uploadFileDao")
public class UploadFileDaoImpl extends BaseDaoImpl<UploadFile, UploadFileVo> implements UploadFileDao{

}
