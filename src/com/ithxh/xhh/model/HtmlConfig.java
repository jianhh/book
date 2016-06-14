package com.ithxh.xhh.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @category 生成静态页面的时候需要用到的 替换模板
 * @author five
 *
 */
public class HtmlConfig {

	public static final String REPLACE_FILE = "{file_name}";// 替换模板文件名
	public static final String REPLACE_SN = "{sn}";// 随机UUID字符串替换
	public static final String REPLACE_DATE_YY = "{date_yyyy}";// 当前日期字符串替换(年)
	public static final String REPLACE_DATE_MM = "{date_MM}";// 当前日期字符串替换(月)
	public static final String REPLACE_DATE_DD = "{date_dd}";// 当前日期字符串替换(日)
	public static final String REPLACE_DATE_HH = "{date_HH}";// 当前日期字符串替换(时)

	public static final String LGOIN = "login";// 登录页面
	//public static final String RESTAURANT_DETAIL = "restaurantDetail";// 餐厅详情界面

	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径
	private String htmlFilePath;// 生成HTML静态文件存放路径

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getTemplateFilePath() {

		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {

		this.templateFilePath = templateFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {

		this.htmlFilePath = htmlFilePath;
	}

	// 获取模板文件存放路径
	public String getTemplateFilePath(String fileName) {

		templateFilePath = templateFilePath.replace(REPLACE_FILE, fileName);
		return templateFilePath;
	}

	// 获取生成HTML静态文件存放路径
	public String getHtmlFileReplacePath(String sn) {

		htmlFilePath = htmlFilePath.replace(REPLACE_SN, sn);
		SimpleDateFormat yyDateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat ddDateFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hhDateFormat = new SimpleDateFormat("HH");
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_YY, yyDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_MM, mmDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_DD, ddDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_HH, hhDateFormat.format(new Date()));
		return htmlFilePath;
	}

	public String getHtmlFilePath() {

		return htmlFilePath;
	}
}
