package com.ithxh.baseCore.plugins.smartupload;

import java.io.InputStream;
import java.util.List;

import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @description 上传下载工具类 
 * @author hxh
 * @date 2016年1月17日 下午4:28:14
 */
public class JspSmartUploadUtil {
	
	private static List<JspSmartUpload> uploadList;
	
	private JspSmartUploadUtil(){}
	
	/**
	 * @Description: 根据指定key获取smartupload.xml中指定配置信息，并且返回实体  
	 * @author: 何建辉
	 * @date 2016年1月17日 下午4:27:36
	 * @param @param key
	 * @param @return
	 */
	@SuppressWarnings("unchecked")
	public static JspSmartUpload getSmartUpload(String key,InputStream is){
		
		if(ListUtil.isEmpty(uploadList)){
			XStream xStream = new XStream(new StaxDriver());
			xStream.setMode(XStream.NO_REFERENCES);
			xStream.alias("smartUpload", JspSmartUpload.class);
			xStream.aliasAttribute(JspSmartUpload.class, "name", "name");
			uploadList = (List<JspSmartUpload>) xStream.fromXML(is,"smartUpload");
		}
		
		if(ListUtil.isEmpty(uploadList)){
			throw new RuntimeException("smartupload.xml配置错误");
		}
		if(StringUtil.isEmpty(key)){
			return uploadList.get(0);
		}
		for(JspSmartUpload su:uploadList){
			if(su.getName().toLowerCase().equals(key.toLowerCase())){
				return su;
			}
		}
		throw new RuntimeException("smartupload.xml配置错误");
	}

}
