package com.ithxh.baseCore.baseUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.sql.Date;

/**
 * @category 类型转换 数据判断 等 工具类
 * @author five
 * 
 */
public class ConvertUtils {

	/**
	 * @category 判断对象是否为空
	 */
	public static boolean isEmpty(Object object) {

		if (object == null) {
			return (true);
		}
		if (object.equals("")) {
			return (true);
		}
		if (object.equals("null")) {
			return (true);
		}
		return (false);
	}

	/**
	 * @category 将ISO 8859 1 编码的字符串 换成 UTF8 的字符串
	 * @param strIn
	 * @param sourceCode
	 * @param targetCode
	 * @return
	 */
	public static String IsoToUTF(String strIn) {

		strIn = code2code(strIn, "ISO-8859-1", "UTF-8");
		return strIn;

	}

	/**
	 * @category 对字符串换编码
	 * @param strIn
	 *            字符串
	 * @param sourceCode
	 *            源编码类型
	 * @param targetCode
	 *            目标编码类型
	 * @return
	 */
	private static String code2code(String strIn, String sourceCode, String targetCode) {

		String strOut = null;
		if (strIn == null || (strIn.trim()).equals(""))
			return strIn;
		try {
			byte[] b = strIn.getBytes(sourceCode);
			for (int i = 0; i < b.length; i++) {
				System.out.print(b[i] + "  ");
			}
			strOut = new String(b, targetCode);
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
			return null;
		}
		return strOut;
	}

	/**
	 * @category 将 字符串转换成int类型 如果空或异常 就 返回 defval
	 * @param s
	 * @param defval
	 * @return
	 */
	public static int getInt(String s, int defval) {

		if (s == null || s == "") {
			return (defval);
		}
		try {
			return (Integer.parseInt(s));
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return (defval);
		}
	}

	/**
	 * @category 将string 数组转换成 int数组 异常默认 defval
	 * @param s
	 * @param defval
	 * @return
	 */
	public static Integer[] getInts(String[] s, int defval) {

		Integer[] integer = new Integer[s.length];
		for (int i = 0; i < s.length; i++) {
			integer[i] = getInt(s, defval);
		}
		return integer;
	}

	/**
	 * @category 将String类型转换成Double类型 异常就返回 defval
	 * @param s
	 * @param defval
	 * @return
	 */
	public static double getDouble(String s, double defval) {

		if (s == null || s == "") {
			return (defval);
		}
		try {
			return (Double.parseDouble(s));
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return (defval);
		}
	}

	/**
	 * @category 将string 转换成 short 如果异常就返回 defval
	 * @param s
	 * @return
	 */
	public static Short getShort(String s, Short defval) {

		if (StringUtil.isEmpty(s)) {
			return null;
		}
		try {
			return Short.parseShort(s);
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return (defval);
		}

	}

	/**
	 * @category 将Object 转换成 int 如果异常就返回 defval
	 * @param s
	 * @return
	 */

	public static int getInt(Object object, int defval) {

		if (isEmpty(object)) {
			return (defval);
		}
		try {
			return (Integer.parseInt(object.toString()));
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return (defval);
		}
	}

	/**
	 * @category 将BigDecimal 转换成 int 如果异常就返回 defval
	 * @param s
	 * @return
	 */
	public static int getInt(BigDecimal s, int defval) {

		if (s == null) {
			return (defval);
		}
		return s.intValue();
	}

	/**
	 * @category object 转换成String
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {

		if (isEmpty(object)) {
			return "";
		}
		return (object.toString().trim());
	}

	/**
	 * @category int转换成string
	 * @param i
	 * @return
	 */
	public static String getString(int i) {

		return (String.valueOf(i));
	}

	/**
	 * @category float转string
	 * @param i
	 * @return
	 */
	public static String getString(float i) {

		return (String.valueOf(i));
	}

	/**
	 * @category 去掉string的空格
	 * @param s
	 * @param defval
	 * @return
	 */
	public static String getString(String s, String defval) {

		if (isEmpty(s)) {
			return (defval);
		}
		return (s.trim());
	}

	/**
	 * @category object转成string并且去掉空格
	 * @param s
	 * @param defval
	 * @return
	 */
	public static String getString(Object s, String defval) {

		if (isEmpty(s)) {
			return (defval);
		}
		return (s.toString().trim());
	}

	/**
	 * @category string转换成long
	 * @param str
	 * @return
	 */
	public static long stringToLong(String str) {

		Long test = new Long(0);
		try {
			test = Long.valueOf(str);
		} catch (Exception e) {
			Log4jUtil.error("", e);
		}
		return test.longValue();
	}

	/**
	 * @category 判断一个类是否为基本数据类型。
	 * @param clazz
	 *            要判断的类。
	 * @return true 表示为基本数据类型。
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static boolean isBaseDataType(Class clazz) throws Exception {

		return (clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class)
				|| clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive());
	}

	/**
	 * @category 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {

		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest.trim();

	}

	/**
	 * @category 判断字符串是否在字符串数组内
	 * @param substring
	 * @param source
	 * @return
	 */
	public static boolean isIn(String substring, String[] source) {

		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}

}
