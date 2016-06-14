package com.ithxh.xhh.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.controller.pub.QNUpload;
import com.ithxh.xhh.entity.UploadFile;
import com.ithxh.xhh.exception.UpdateFailException;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.tools.SysConfigUtil;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("uploadFileService")
@Transactional
public class UploadFileServiceImpl extends BaseServiceImpl<UploadFile, UploadFileVo> implements UploadFileService{

	/**
	 * 本地上传
	 */
	@Transactional
	public ReturnMessage<Object> add(List<UploadFileVo> list,String realPath) {
		//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
        try {
        	String qnpath = SysConfigUtil.getSysConfig().getQiniuUrl();
        	//如果开启七牛云存储，则使用云存储
    		if(SysConfigUtil.getSysConfig().isOpenQiniuUpload()){
    			for(UploadFileVo file : list){
    				file.setUploaddest("qiniuyun");
    				String url = new QNUpload(file.getUploadnewfilename(),file.getBytes()).upload();
    				file.setUploadfilepath(qnpath+url);
    				if(file.getInputStream()!=null){
            			file.getInputStream().close();
            		}
    			}
    		}
        	rm = super.add(list);
        	if(!rm.isResult()){
        		throw new UpdateFailException("批处理上传文件失败");
        	}
        	if(!SysConfigUtil.getSysConfig().isOpenQiniuUpload()){
    			for(UploadFileVo file : list){
            		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath+File.separatorChar+file.getUploadfilepath()));
            		if(file.getInputStream()!=null){
            			file.getInputStream().close();
            		}
            	}
    		}
        	
		} catch (Exception e) {
			e.printStackTrace();
			throw new UpdateFailException("批处理上传文件失败");
		} 
        rm.setResult(true);
        rm.setMessage("上传成功");
		return rm;
	}

	
}
