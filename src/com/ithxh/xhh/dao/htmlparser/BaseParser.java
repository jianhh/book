package com.ithxh.xhh.dao.htmlparser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.WebUtils;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.controller.pub.QNUpload;
import com.ithxh.xhh.tools.SysConfigUtil;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@SuppressWarnings({ "serial"})
public class BaseParser implements Serializable{
	
	private static Logger logger = Logger.getLogger(BaseParser.class.getName());
	
	public UploadFileVo parseImg(String picpath){
		logger.info("正在获取 "+picpath+" 的图片---->开始");
		UploadFileVo uploadfile = new UploadFileVo();
		try {
			URL picUrl = new URL(picpath);
			InputStream is = picUrl.openStream();
			//构造文件实体
			uploadfile.setUploadid(IDGenerator.uuidGenerate());
			//获取原文件名
			int index = picpath.lastIndexOf("/");
			String oldname = picpath.substring(index+1);
			uploadfile.setUploadoldfilename(oldname);
			//获取文件后缀
			String ext = picpath.substring(picpath.lastIndexOf(".")+1);
			uploadfile.setUploadfileext(ext);
			//构造保存路径
			String savepath = StaticConst.JD_BOOKPIC_PATH+DateUtils.getDate("yyyy/MM/dd");
			uploadfile.setUploadnewfilename(IDGenerator.uuidGenerate()+"."+ext);
			uploadfile.setUploadfilepath(savepath+"/"+uploadfile.getUploadnewfilename());
			uploadfile.setUploadfiletype("img");
			uploadfile.setUploadtime(DateUtils.getNowDateTime());
			uploadfile.setUploadstatus("0");
			//根据配置文件，决定保存到哪个文件服务器。
			if(SysConfigUtil.getSysConfig().isOpenQiniuUpload()){
				logger.info("正在本地下载图片---->开始上传到七牛云存储");
				byte[] bytes = WebUtils.input2byte(is);
				String qnpath = SysConfigUtil.getSysConfig().getQiniuUrl();
				uploadfile.setUploaddest("qiniuyun");
				String url = new QNUpload(uploadfile.getUploadnewfilename(),bytes).upload();
				uploadfile.setUploadfilepath(qnpath+url);
				logger.info("正在下载图片到云端---->结束");
    		}else{
    			logger.info("正在本地下载图片---->开始下载到"+StaticConst.SYS_FILE_PATH+File.separatorChar+uploadfile.getUploadfilepath());
    			FileUtils.copyInputStreamToFile(is, new File(StaticConst.SYS_FILE_PATH+File.separatorChar+uploadfile.getUploadfilepath()));
    			logger.info("正在本地下载图片---->结束");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		logger.info("正在获取 "+picpath+" 的图片---->结束");
		return uploadfile;
	}
	
	/**
	 * @Description:解析url，返回解析后的内容  
	 * @author: 何建辉
	 * @date 2016年3月31日 上午1:12:54
	 * @param @param picpath
	 * @param @return
	 */
	public static String parseUrl(String picpath){
		logger.info("正在解析url---->开始解析："+picpath);
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(picpath);
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(20000).setConnectionRequestTimeout(50000)  
			        .setSocketTimeout(20000).build();  
	        httpget.setConfig(requestConfig);
	        try {  
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            try {  
	                HttpEntity entity = response.getEntity();  
	                if (entity != null) {  
	                    return EntityUtils.toString(entity, "UTF-8");
	                } 
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        } 
		} catch (Exception e) {
			logger.info("解析失败");
			e.printStackTrace();
			return null;
		}
		logger.info("正在解析url---->结束");
		return null;
	}
}
