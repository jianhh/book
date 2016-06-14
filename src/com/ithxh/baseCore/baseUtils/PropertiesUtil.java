package com.ithxh.baseCore.baseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @category 属性文件工具类
 * @author five
 */
public class PropertiesUtil {

	private static String properiesName = "sysConfig.properties";

	private final static Properties properties = new Properties();
	private static boolean isLoadPropertites = false;

	/**
	 * @category 将属性文件读成属性对象
	 */
	public static void loadProperties() {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(properiesName);
		InputStream in = null;
		try {
			in = url.openStream();
			properties.load(in);
			isLoadPropertites = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log4jUtil.error("", e);
			}
		}
	}

	/**
	 * @category 从属性文件直接读取属性
	 * @param key
	 * @return
	 */
	public static String readProperty(String key) {

		if (!isLoadPropertites)
			loadProperties();

		return properties.getProperty(key);
	}

	/**
	 * @category 向属性文件中写数据
	 * @param key
	 * @param value
	 */
	public void writeProperty(String key, String value) {

		OutputStream os = null;
		if (!isLoadPropertites)
			loadProperties();
		properties.setProperty(key, value);
		try {
			properties.store(os, key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log4jUtil.error("", e);
			}
		}
	}
}
