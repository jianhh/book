package com.ithxh.baseCore.baseUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ithxh.xhh.exception.SystemBusyException;

/**
 * @description web工具类 
 * @author hxh
 * @date 2016年1月17日 下午5:45:51
 */
public class WebUtils {
	
	public static final byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    } 
	
	
	public static List<MultipartFile> getList4Request(HttpServletRequest request){
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		MultipartHttpServletRequest mutRequest = (MultipartHttpServletRequest) request;
		
		if (isMultipart) {
			List<MultipartFile> files = mutRequest.getMultiFileMap().get("photo");
			if(files==null){
				files = mutRequest.getMultiFileMap().get("wangEditorFormFile");
			}
			if(files==null){
				files = mutRequest.getMultiFileMap().get("wangEditorH5File");
			}
			if(files==null){
				files = mutRequest.getMultiFileMap().get("wangEditorPasteFile");
			}
			if(files==null){
				files = mutRequest.getMultiFileMap().get("wangEditorDragFile");
			}
			return files;
		}
		return null;
	}

	/**
	 * @Description:获取以 ip 时间戳 和三位随机数组成的字符串
	 * @author: 何建辉
	 * @date 2016年1月17日 下午5:46:02
	 * @param @param ip
	 * @param @return
	 */
	public static String getIPTimeRand(String ip) {
		StringBuffer buf = new StringBuffer();
		if (ip != null) {
			String s[] = ip.split("\\.");// 根据ip以点分割将IP中的数字提取
			for (int i = 0; i < s.length; i++) {
				buf.append(addZero(s[i], 3));// 不够三位数的补零
			}
		}
		buf.append(getTimeStamp());
		Random r = new Random();// 再在结尾加上三个随机数
		for (int i = 0; i < 3; i++) {
			buf.append(r.nextInt(10));
		}
		return buf.toString();
	}
	
	private static String getTimeStamp(){//获取时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return sdf.format(new Date());
	}
		 
	public static String getDate(){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(new Date());
	}
	
	private static String addZero(String str, int len){
       StringBuffer s = new StringBuffer();
       s.append(str);
       while(s.length()<len){
           s.insert(0, 0);
       }
       
       return s.toString();
    }
	
	/**
	 * @Description: 换算成k，保留两位小数
	 * @author: 何建辉
	 * @date 2016年1月23日 下午3:19:41
	 * @param @param value
	 * @param @return
	 */
	public static double tranK(int value){
		BigDecimal b = new BigDecimal(((double)value)/1024);   
		double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		return df;
	}
	
	
	/**
	 * @Description:  转换大小 转出K M G T
	 * @author: 何建辉
	 * @date 2016年2月14日 下午9:14:28
	 * @param @param size
	 * @param @return
	 */
	public static String tranSize(Long size){
		String str = "";
		if(size<0){
			throw new SystemBusyException("转换参数错误");
		}else if(size<1024){
			str = "1K";
		}else if (size<1024*1024) {
			str = size/1024+"K";
		}else if (size<1024*1024*1024) {
			BigDecimal b = new BigDecimal(size/1024/1024);   
			double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			str = df+"M";
		}else if (size<1024*1024*1024*1024) {
			BigDecimal b = new BigDecimal(size/1024/1024/1024);   
			double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			str = df+"G";
		}else{
			BigDecimal b = new BigDecimal(size/1024/1024/1024/1024);   
			double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
			str = df+"T";
		}
		
		return str;
	}
}
