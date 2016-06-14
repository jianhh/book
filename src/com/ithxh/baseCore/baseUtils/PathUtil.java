package com.ithxh.baseCore.baseUtils;

import javax.servlet.http.HttpServletRequest;

//import org.jeecgframework.web.system.manager.ClientManager;
//import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
//import org.jeecgframework.web.system.pojo.base.TSUser;

/**
 * 项目参数工具类
 * 
 */
public class PathUtil {

	// private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	/**
	 * @category 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {

		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}
	
	/**
	 * @category 获得webapps 项目路径
	 * @author 梁永明
	 * @param request
	 * @date 2014-7-8 09:02
	 * @return path
	 */
	public static String getWebappsPath(HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath("");
		return path;
	}

	/**
	 * @category 获取系统路径
	 * @return
	 */
	public static String getSysPath() {

		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * @category 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {

		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	/**
	 * @category 获取class文件路径
	 * @return
	 */
	public static String getClassPath() {

		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	/**
	 * @category 获取系统文件路径
	 * @return
	 */
	public static String getSystempPath() {

		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * @category 获取路径分隔符
	 * @return
	 */
	public static String getSeparator() {

		return System.getProperty("file.separator");
	}

	// /**
	// * 获取随机码的长度
	// *
	// * @return 随机码的长度
	// */
	// public static String getRandCodeLength() {
	// return bundle.getString("randCodeLength");
	// }
	//
	// /**
	// * 获取随机码的类型
	// *
	// * @return 随机码的类型
	// */
	// public static String getRandCodeType() {
	// return bundle.getString("randCodeType");
	// }

	public static void main(String[] args) {

		System.out.println(getPorjectPath());
		System.out.println(getSystempPath());
		System.out.println(getSysPath());
		System.out.println(getClassPath());

	}
}
