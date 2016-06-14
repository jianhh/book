package com.ithxh.baseCore.plugins.smartupload;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.IpUtil;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.WebUtils;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.FileType;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.entity.UploadFile;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

/**
 * @description smartupload上传下载model 
 * @author hxh
 * @date 2016年1月17日 下午4:07:28
 */
public class JspSmartUpload {
		
	private String name; //默认default
	private String maxFileSize; //
	private String totalMaxFileSize;
	private String allowedFilesList;
	private String deniedFilesList;
	private String permanentDir;
	private String templateDir;
	private String picDir;
	private String videoDir;
	private String resourceDir;
	private String otherDir;
	
	//-------------------------------------------------------------------------------------
	/**
	 * @Description:  验证文件
	 * @author: 何建辉
	 * @date 2016年2月14日 下午9:27:06
	 * @param @param files
	 * @param @return
	 */
	public ReturnMessage<UploadFile> Validate(List<MultipartFile> files){
		if(ListUtil.isEmpty(files)){
			return ReturnMessage.get(ReturnMsg.BASE_FALSE,"文件未上传");
		}
		Long allFileSize = 0L;
		//验证文件类型，大小，总大小
		for(MultipartFile file : files){
			String exp = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			//判断禁止上传类型
			if(deniedFilesList.indexOf(",")<0){
				if(exp.toLowerCase().equals(deniedFilesList.toLowerCase())){
					return ReturnMessage.get(ReturnMsg.BASE_FALSE,"不允许上传该类型文件");
				}
			}else {
				String[] type = deniedFilesList.split(",");
				for (int i = 0; i < type.length; i++) {
					if(type[i].toLowerCase().equals(exp.toLowerCase())){
						return ReturnMessage.get(ReturnMsg.BASE_FALSE,"不允许上传该类型文件");
					}
				}
			}
			//判断允许上传类型
			if(allowedFilesList.indexOf(",")<0){
				if(!exp.toLowerCase().equals(allowedFilesList.toLowerCase())){
					return ReturnMessage.get(ReturnMsg.BASE_FALSE,"文件类型不支持");
				}
			}else {
				boolean b = false;
				String[] type = allowedFilesList.split(",");
				for (int i = 0; i < type.length; i++) {
					if(type[i].toLowerCase().equals(exp.toLowerCase())){
						b = true;
						break;
					}
				}
				if(!b){
					return ReturnMessage.get(ReturnMsg.BASE_FALSE,"文件类型不支持");
				}
				
			}
			
			//判断单个大小
			long size = file.getSize();
			if(getMaxFileSize()<size){
				return ReturnMessage.get(ReturnMsg.BASE_FALSE,"文件限制大小为"+WebUtils.tranSize(getMaxFileSize()));
			}
			allFileSize = allFileSize+size;
		}
		if(allFileSize>getTotalMaxFileSize()){
			return ReturnMessage.get(ReturnMsg.BASE_FALSE,"总文件限制大小为"+WebUtils.tranSize(getTotalMaxFileSize()));
		}
		
		return ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS);
	}
	
	/**
	 * @Description:  文件上传
	 * @author: 何建辉
	 * @date 2016年2月14日 下午9:27:20
	 * @param @param config
	 * @param @param request
	 * @param @param response
	 * @param @return
	 */
	public ReturnMessage<UploadFileVo> initUploadFile(HttpServletRequest request,String realPath){
		ReturnMessage<UploadFileVo> rm = new ReturnMessage<UploadFileVo>();
		List<UploadFileVo> fileList = new ArrayList<UploadFileVo>();
		try {
			//判断文件业务类型
			String savePath = this.permanentDir;
			if(name.equals(FileType.IMG.getCode())){
				savePath = savePath+this.picDir+"/"+DateUtils.getDate("yyyy/MM/dd");
			}else if (name.equals(FileType.VIDEO.getCode())) {
				savePath = savePath+this.videoDir+"/"+DateUtils.getDate("yyyy/MM/dd");
			}else if (name.equals(FileType.DOCUMENT.getCode())) {
				savePath = savePath+this.resourceDir+"/"+DateUtils.getDate("yyyy/MM/dd");
			}else {
				savePath = savePath+this.otherDir+"/"+DateUtils.getDate("yyyy/MM/dd");
			}
			List<MultipartFile> list = WebUtils.getList4Request(request);
			for (MultipartFile file : list) {  
				String fileName = file.getOriginalFilename();
				UploadFileVo uploadFile = new UploadFileVo();
				uploadFile.setUploadid(IDGenerator.uuidGenerate());
				uploadFile.setUploadfileext(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));
				String newname = WebUtils.getIPTimeRand(IpUtil.getRemotIpAddr(request))+"."+uploadFile.getUploadfileext();
				//可以根据内在类型判断放到哪个目录下
				
				uploadFile.setUploadfilepath(savePath+"/"+newname);
				uploadFile.setUploadfilesize(file.getSize());
				uploadFile.setUploadfiletype("");
				uploadFile.setUploadip(IpUtil.getRemotIpAddr(request));
				uploadFile.setUploadmimetype(file.getContentType());
				uploadFile.setUploadnewfilename(newname);
				uploadFile.setUploadoldfilename(fileName);
				uploadFile.setUploadtime(DateUtils.getNowDateTime());
				uploadFile.setUploadtransize(WebUtils.tranSize(file.getSize()));
				uploadFile.setInputStream(file.getInputStream());
				uploadFile.setUploadstatus("0");
				uploadFile.setBytes(file.getBytes());
				//保存
				java.io.File f = new java.io.File(savePath);
				if(!f.exists()){
					f.mkdirs();
				}
				fileList.add(uploadFile);
			}
			rm.setResult(true);
			rm.setList(fileList);
		} catch (Exception e) {
			e.printStackTrace();
			rm.setMessage("上传失败");
			rm.setResult(false);
		}
		return rm;
	}
	
	
	
	//-------------------------------------------------------------------------------------
	
	
	public JspSmartUpload() {
		super();
	}
	
	@Override
	public String toString() {
		return "SmartUpload [name=" + name + ", maxFileSize=" + maxFileSize
				+ ", totalMaxFileSize=" + totalMaxFileSize
				+ ", allowedFilesList=" + allowedFilesList
				+ ", deniedFilesList=" + deniedFilesList + ", permanentDir="
				+ permanentDir + ", templateDir=" + templateDir + ", picDir="
				+ picDir + ", videoDir=" + videoDir + ", resourceDir="
				+ resourceDir + ", otherDir=" + otherDir + "]";
	}
	
	public String getName() {
		return name;
	}

	public Long getMaxFileSize() {
		if(maxFileSize.indexOf("*")==-1 && Long.valueOf(maxFileSize)>0){
			return Long.valueOf(maxFileSize);
		}else if (maxFileSize.indexOf("*")!=-1) {
			String[] num = maxFileSize.split("\\*");
			Long number = 1L;
			for(String s:num){
				number = number * Long.valueOf(s);
			}
			return number;
		}else{
			throw new RuntimeException("smartupload.xml配置错误，参数为maxFileSize");
		}
	}
	
	public Long getTotalMaxFileSize() {
		
		if(totalMaxFileSize.indexOf("*")==-1 && Long.valueOf(totalMaxFileSize)>0){
			return Long.valueOf(totalMaxFileSize);
		}else if (totalMaxFileSize.indexOf("*")!=-1) {
			String[] num = totalMaxFileSize.split("\\*");
			Long number = 1L;
			for(String s:num){
				number = number * Long.valueOf(s);
			}
			return number;
		}else{
			throw new RuntimeException("smartupload.xml配置错误，参数为totalMaxFileSize");
		}
	}
	
	public String getAllowedFilesList() {
		
		return allowedFilesList;
	}
	
	public String getDeniedFilesList() {
		
		return deniedFilesList;
	}
	
	public String getPermanentDir() {
		return permanentDir;
	}
	public String getTemplateDir() {
		return templateDir;
	}
	public String getPicDir() {
		return picDir;
	}
	public String getVideoDir() {
		return videoDir;
	}
	public String getResourceDir() {
		return resourceDir;
	}
	public String getOtherDir() {
		return otherDir;
	}
}
