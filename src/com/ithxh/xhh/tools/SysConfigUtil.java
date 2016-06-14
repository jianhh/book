package com.ithxh.xhh.tools;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ithxh.baseCore.baseUtils.Log4jUtil;
import com.ithxh.xhh.model.SysConfig;

/**
 * 工具类 - 系统配置
 * 
 * @author
 * @version v2.0 KEY:
 */

public class SysConfigUtil {

	public static final String CONFIG_FILE_NAME = "sysConfig.xml";// 系统配置文件名称
	public static final String SYSTEM_CONFIG_CACHE_KEY = "sysConfig";// sysConfig缓存Key
    
	/**
	 * 获取系统配置信息
	 * 
	 * @return SystemConfig对象
	 */
	public static SysConfig getSysConfig() {

		SysConfig sysConfig = null;
		File configFile = null;
		Document document = null;
		try {
			String pb = SysConfigUtil.class.getResource("").toURI().getPath();
			int classesIndex = pb.indexOf("classes");
			String configFilePath = pb.substring(0, classesIndex + 8) + CONFIG_FILE_NAME;

			configFile = new File(configFilePath);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		Node sysBasePathNode = document.selectSingleNode("/base/sysConfig/sysBasePath");
		Node openQiniuUpload = document.selectSingleNode("/base/sysConfig/openQiniuUpload");
		Node qiniuUrl = document.selectSingleNode("/base/sysConfig/qiniuUrl");
		
		sysConfig = new SysConfig();
		sysConfig.setSysBasePath(sysBasePathNode.getText());
		sysConfig.setOpenQiniuUpload(Boolean.parseBoolean(openQiniuUpload.getText()));
		sysConfig.setQiniuUrl(qiniuUrl.getText());
		
		
		return sysConfig;
	}

	/**
	 * 更新系统配置信息
	 * 
	 * @param systemConfig
	 *            SystemConfig对象
	 */
	public static void update(SysConfig sysConfig) {

		File configFile = null;
		Document document = null;
		try {
			String configFilePath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + CONFIG_FILE_NAME;
			configFile = new File(configFilePath);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configFile);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}

		try {
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
			outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
			outputFormat.setIndent(true);// 设置是否缩进
			outputFormat.setIndent("	");// 以TAB方式实现缩进
			outputFormat.setNewlines(true);// 设置是否换行
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(configFile), outputFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
	}

}