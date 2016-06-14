package com.ithxh.baseCore.baseUtils;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 字符串处理及转换工具类
 * 
 * @author five
 */
public class StringUtil {

	private static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
	private static Pattern numericStringPattern = Pattern.compile("^[0-9\\-\\-]+$");
	private static Pattern floatNumericPattern = Pattern.compile("^[0-9\\-\\.]+$");
	private static Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
	public static final String splitStrPattern = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";
	private static Log logger = LogFactory.getLog(StringUtil.class);

	/**
	 * @category 判断是否数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumeric(String src) {

		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = numericPattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * @category 判断是否数字字符串表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumericString(String src) {

		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = numericStringPattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}
	
	/**
	 * @category 去掉字符串最后一位
	 * 
	 * @param src 源字符串
	 * @return str
	 */
	public static String subStringEndCut(String str) {

		if (str != null && str.length() > 0) {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}

	/**
	 * @category 判断是否纯字母组合
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否纯字母组合的标志
	 */
	public static boolean isABC(String src) {

		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = abcPattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * @category 判断是否浮点数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isFloatNumeric(String src) {

		boolean return_value = false;
		if (src != null && src.length() > 0) {
			Matcher m = floatNumericPattern.matcher(src);
			if (m.find()) {
				return_value = true;
			}
		}
		return return_value;
	}

	/**
	 * @category 把string list用给定的符号symbol连接成一个字符串
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String joinString(List array, String symbol) {

		String result = "";
		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				String temp = array.get(i).toString();
				if (temp != null && temp.trim().length() > 0)
					result += (temp + symbol);
			}
			if (result.length() > 1)
				result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * @category 将字符串截取指定长度 后面用...表示
	 * @param subject
	 * @param size
	 * @return
	 */
	public static String subStringNotEncode(String subject, int size) {

		if (subject != null && subject.length() > size) {
			subject = subject.substring(0, size) + "...";
		}
		return subject;
	}

	/**
	 * @category 截取字符串　超出的字符用symbol代替 　　
	 * 
	 * @param len
	 *            　字符串长度　长度计量单位为一个GBK汉字　　两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len, String symbol) {

		int iLen = len * 2;
		int counterOfDoubleByte = 0;
		String strRet = "";
		try {
			if (str != null) {
				byte[] b = str.getBytes("GBK");
				if (b.length <= iLen) {
					return str;
				}
				for (int i = 0; i < iLen; i++) {
					if (b[i] < 0) {
						counterOfDoubleByte++;
					}
				}
				if (counterOfDoubleByte % 2 == 0) {
					strRet = new String(b, 0, iLen, "GBK") + symbol;
					return strRet;
				} else {
					strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
					return strRet;
				}
			} else {
				return "";
			}
		} catch (Exception ex) {
			Log4jUtil.error("", ex);
			return str.substring(0, len);
		} finally {
			strRet = null;
		}
	}

	/**
	 * @category 截取字符串　超出的字符用...代替 　　
	 * 
	 * @param len
	 *            　字符串长度　长度计量单位为一个GBK汉字　　两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return12
	 */
	public static String getLimitLengthString(String str, int len) {

		return getLimitLengthString(str, len, "...");
	}

	/**
	 * 
	 * @category 截取字符 不转码 也不用 ...
	 * 
	 * @param subject
	 * @param size
	 * @return
	 */
	public static String subStrNotEncode(String subject, int size) {

		if (subject.length() > size) {
			subject = subject.substring(0, size);
		}
		return subject;
	}

	/**
	 * @category 把string 数组用给定的符号symbol连接成一个字符串
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	public static String joinString(String[] array, String symbol) {

		String result = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				String temp = array[i];
				if (temp != null && temp.trim().length() > 0)
					result += (temp + symbol);
			}
			if (result.length() > 1)
				result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * @category 取得字符串的实际长度 汉字占用2 字符占用1
	 * 
	 * @param SrcStr
	 *            源字符串
	 * @return 字符串的实际长度
	 */
	public static int getStringLen(String SrcStr) {

		int return_value = 0;
		if (SrcStr != null) {
			char[] theChars = SrcStr.toCharArray();
			for (int i = 0; i < theChars.length; i++) {
				return_value += (theChars[i] <= 255) ? 1 : 2;
			}
		}
		return return_value;
	}

	/**
	 * @category 检查数据串中是否包含非法字符集
	 * @param str
	 * @return [true]|[false] 包含|不包含
	 */
	public static boolean check(String str) {

		String sIllegal = "'\"";
		int len = sIllegal.length();
		if (null == str)
			return false;
		for (int i = 0; i < len; i++) {
			if (str.indexOf(sIllegal.charAt(i)) != -1)
				return true;
		}

		return false;
	}

	/***************************************************************************
	 * @category 隐藏邮件地址前缀
	 * 
	 * @param email
	 *            - EMail邮箱地址 例如: linwenguo@koubei.com 等等...
	 * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
	 * @version 1.0 (2006.11.27) Wilson Lin
	 **************************************************************************/
	public static String getHideEmailPrefix(String email) {

		if (null != email) {
			int index = email.lastIndexOf('@');
			if (index > 0) {
				email = repeat("*", index).concat(email.substring(index));
			}
		}
		return email;
	}

	/***************************************************************************
	 * @category 通过源字符串重复生成N次组成新的字符串。
	 * 
	 * @param src
	 *            - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
	 * @param num
	 *            - 重复生成次数
	 * @return 返回已生成的重复字符串
	 * @version 1.0 (2006.10.10) Wilson Lin
	 **************************************************************************/
	public static String repeat(String src, int num) {

		StringBuffer s = new StringBuffer();
		for (int i = 0; i < num; i++)
			s.append(src);
		return s.toString();
	}

	/**
	 * @category 根据指定的字符把源字符串分割成一个数组
	 * 
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByCustomerPattern(String pattern, String src) {

		if (src == null)
			return null;
		List<String> list = new ArrayList<String>();
		String[] result = src.split(pattern);
		for (int i = 0; i < result.length; i++) {
			list.add(result[i]);
		}
		return list;
	}

	/**
	 * @category 根据指定的字符把源字符串分割成一个数组 逗号 登号 句号
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByPattern(String src) {

		String pattern = "，|,|、|。";
		return parseString2ListByCustomerPattern(pattern, src);
	}

	/**
	 * @category 格式化一个float
	 * 
	 * @param format
	 *            要格式化成的格式 such as #.00, #.#
	 */

	public static String formatFloat(float f, String format) {

		DecimalFormat df = new DecimalFormat(format);
		return df.format(f);
	}

	/**
	 * @category 判断是否是空字符串 null和"" 都返回 true
	 * 
	 * @author Robin Chang
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {

		if (s != null && !s.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * @category 自定义的分隔字符串函数 例如: 1,2,3 =>[1,2,3] 3个元素 ,2,3=>[,2,3] 3个元素 ,2,3,=>[,2,3,] 4个元素 ,,,=>[,,,] 4个元素
	 * 
	 *           5.22算法修改，为提高速度不用正则表达式 两个间隔符,,返回""元素
	 * 
	 * @param split
	 *            分割字符 默认,
	 * @param src
	 *            输入字符串
	 * @return 分隔后的list
	 * @author Robin
	 */
	public static List<String> splitToList(String split, String src) {

		// 默认,
		String sp = ",";
		if (split != null && split.length() == 1) {
			sp = split;
		}
		List<String> r = new ArrayList<String>();
		int lastIndex = -1;
		int index = src.indexOf(sp);
		if (-1 == index && src != null) {
			r.add(src);
			return r;
		}
		while (index >= 0) {
			if (index > lastIndex) {
				r.add(src.substring(lastIndex + 1, index));
			} else {
				r.add("");
			}

			lastIndex = index;
			index = src.indexOf(sp, index + 1);
			if (index == -1) {
				r.add(src.substring(lastIndex + 1, src.length()));
			}
		}
		return r;
	}

	/**
	 * @category 把 名 值 参数表转换成字符串 (a=1,b=2 =>a=1&b=2)
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String linkedHashMapToString(LinkedHashMap<String, String> map) {

		if (map != null && map.size() > 0) {
			String result = "";
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				String value = map.get(name);
				result += (result.equals("")) ? "" : "&";
				result += String.format("%s=%s", name, value);
			}
			return result;
		}
		return null;
	}

	/**
	 * @category 解析字符串返回 名称=值的参数表 (a=1&b=2 => a=1,b=2)
	 * @see test.koubei.util.StringUtilTest#testParseStr()
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedHashMap<String, String> toLinkedHashMap(String str) {

		if (str != null && !str.equals("") && str.indexOf("=") > 0) {
			LinkedHashMap result = new LinkedHashMap();

			String name = null;
			String value = null;
			int i = 0;
			while (i < str.length()) {
				char c = str.charAt(i);
				switch (c) {
				case 61: // =
					value = "";
					break;
				case 38: // &
					if (name != null && value != null && !name.equals("")) {
						result.put(name, value);
					}
					name = null;
					value = null;
					break;
				default:
					if (value != null) {
						value = (value != null) ? (value + c) : "" + c;
					} else {
						name = (name != null) ? (name + c) : "" + c;
					}
				}
				i++;

			}

			if (name != null && value != null && !name.equals("")) {
				result.put(name, value);
			}

			return result;

		}
		return null;
	}

	/**
	 * @category 根据输入的多个解释和下标返回一个值
	 * 
	 * @param captions
	 *            例如:"无,爱干净,一般,比较乱"
	 * @param index
	 *            1
	 * 
	 * @return 爱干净
	 */
	public static String getCaption(String captions, int index) {

		if (index > 0 && captions != null && !captions.equals("")) {
			String[] ss = captions.split(",");
			if (ss != null && ss.length > 0 && index < ss.length) {
				return ss[index];
			}
		}
		return null;
	}

	/**
	 * @category 数字转字符串,如果num<=0 则输出"";
	 * 
	 * @param num
	 * @return
	 */
	public static String objectToString(Object num) {

		if (num == null) {
			return null;
		} else if (num instanceof Integer && (Integer) num > 0) {
			return Integer.toString((Integer) num);
		} else if (num instanceof Long && (Long) num > 0) {
			return Long.toString((Long) num);
		} else if (num instanceof Float && (Float) num > 0) {
			return Float.toString((Float) num);
		} else if (num instanceof Double && (Double) num > 0) {
			return Double.toString((Double) num);
		} else {
			return "";
		}
	}

	/**
	 * @category 货币转字符串
	 * 
	 * @param money
	 * @param style
	 *            样式 [default]要格式化成的格式 such as #.00, #.#
	 * @return
	 */

	public static String moneyToString(Object money, String style) {

		if (money != null && style != null && (money instanceof Double || money instanceof Float)) {
			Double num = (Double) money;

			if (style.equalsIgnoreCase("default")) {
				// 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
				if (num == 0) {
					// 不输出0
					return "";
				} else if ((num * 10 % 10) == 0) {
					// 没有小数
					return Integer.toString(num.intValue());
				} else {
					// 有小数
					return num.toString();
				}

			} else {
				DecimalFormat df = new DecimalFormat(style);
				return df.format(num);
			}
		}
		return null;
	}

	/**
	 * @category 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
	 * 
	 * @param sou
	 * @param find
	 * @return
	 */
	public static boolean findPos(String sou, String... finds) {

		if (sou != null && finds != null && finds.length > 0) {
			for (int i = 0; i < finds.length; i++) {
				if (sou.indexOf(finds[i]) > -1)
					return true;
			}
		}
		return false;
	}

	/**
	 * @category 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
	 * 
	 * @param sou
	 * @param find
	 * @return
	 */
	public static boolean strPos(String sou, List<String> finds) {

		if (sou != null && finds != null && finds.size() > 0) {
			for (String s : finds) {
				if (sou.indexOf(s) > -1)
					return true;
			}
		}
		return false;
	}

	/**
	 * @category 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
	 * 
	 * @param sou
	 * @param find
	 * @return
	 */
	public static boolean strPos(String sou, String finds) {

		List<String> t = splitToList(",", finds);
		return strPos(sou, t);
	}

	/**
	 * @category 判断两个字符串是否相等 如果都为null则判断为相等, 一个为null另一个not null则判断不相等 否则如果s1=s2则相等
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equals(String s1, String s2) {

		if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
			return true;
		} else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
			return s1.equals(s2);
		}
		return false;
	}

	/**
	 * @category string 转Int
	 * @param s
	 * @return
	 */
	public static int toInt(String s) {

		if (s != null && !"".equals(s.trim())) {
			try {
				return Integer.parseInt(s);
			} catch (Exception e) {
				Log4jUtil.error("", e);
				return 0;
			}
		}
		return 0;
	}

	/**
	 * @category string转Double
	 * @param s
	 * @return
	 */
	public static double toDouble(String s) {

		if (s != null && !"".equals(s.trim())) {
			return Double.parseDouble(s);
		}
		return 0;
	}

	/**
	 * @category 把xml 转为object
	 * 
	 * @param xml
	 * @return
	 */

	@SuppressWarnings("resource")
	public static Object xmlToObject(String xml) {

		try {
			ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes("UTF8"));
			XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(in));
			return xmlDecoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		return null;
	}

	/**
	 * @category string转换成 long
	 * @param s
	 * @return
	 */
	public static long toLong(String s) {

		try {
			if (s != null && !"".equals(s.trim()))
				return Long.parseLong(s);
		} catch (Exception e) {
			Log4jUtil.error("", e);
		}
		return 0L;
	}
	
	/**
	 * @category object转换成 short
	 * @param s
	 * @return
	 */
	public static short toShort(Object obj) {
		String str = String.valueOf(obj);
		try {
			if (str != null && !"".equals(str.trim()))
				return Short.parseShort(str);
		} catch (Exception e) {
			Log4jUtil.error("", e);
		}
		return 0;
	}

	/**
	 * @category 随即生成指定位数的含验证码字符串
	 * @author Peltason
	 * 
	 * @date 2007-5-9
	 * @param bit
	 *            指定生成验证码位数
	 * @return String
	 */
	public static String getRandom(int bit) {

		if (bit == 0)
			bit = 6; // 默认6位
		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "";
		str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
		return RandomStringUtils.random(bit, str);// 返回6位的字符串
	}

	/**
	 * @category Wap页面的非法字符检查
	 * @author hugh115
	 * @date 2007-06-29
	 * @param str
	 * @return
	 */
	public static String replaceWapStr(String str) {

		if (str != null) {
			str = str.replaceAll("<span class=\"keyword\">", "");
			str = str.replaceAll("</span>", "");
			str = str.replaceAll("<strong class=\"keyword\">", "");
			str = str.replaceAll("<strong>", "");
			str = str.replaceAll("</strong>", "");
			str = str.replace('$', '＄');
			str = str.replaceAll("&amp;", "＆");
			str = str.replace('&', '＆');
			str = str.replace('<', '＜');
			str = str.replace('>', '＞');
		}
		return str;
	}

	/**
	 * @category 字符串转float 如果异常返回0.00
	 * @param s
	 *            输入的字符串
	 * @return 转换后的float
	 */
	public static Float toFloat(String s) {

		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return new Float(0);
		}
	}

	/**
	 * @category 页面中去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @author shazao
	 * @date 2007-08-17
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {

		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}

	/**
	 * @category 全角生成半角
	 * @author bailong
	 * @date 2007-08-29
	 * @param str
	 * @return
	 */
	public static String Q2B(String QJstr) {

		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				Log4jUtil.error("", e);
				if (logger.isErrorEnabled()) {
					logger.error(e);
				}
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					Log4jUtil.error("", e);
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			} else {
				outStr = outStr + Tstr;
			}
		}
		return outStr;
	}

	/**
	 * 
	 * @category 修改字符串的编码
	 * 
	 * @param s
	 *            源字符串
	 * @param fencode
	 *            源编码格式
	 * @param bencode
	 *            目标编码格式
	 * @return 目标编码
	 */
	public static String changCoding(String s, String fencode, String bencode) {

		String str;
		try {
			if (StringUtil.isNotEmpty(s)) {
				str = new String(s.getBytes(fencode), bencode);
			} else {
				str = "";
			}
			return str;
		} catch (UnsupportedEncodingException e) {
			Log4jUtil.error("", e);
			return s;
		}
	}

	/**
	 * @category 清除字符串的 HTML 标签
	 * @param str
	 * @return
	 ************************************************************************* 
	 */
	public static String removeHTMLLableExe(String str) {

		str = stringReplace(str, ">\\s*<", "><");
		str = stringReplace(str, "&nbsp;", " ");// 替换空格
		str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
		str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
		str = stringReplace(str, "\\s\\s\\s*", " ");// 将多个空白变成一个空格
		str = stringReplace(str, "^\\s*", "");// 去掉头的空白
		str = stringReplace(str, "\\s*$", "");// 去掉尾的空白
		str = stringReplace(str, " +", " ");
		return str;
	}

	/**
	 * @category 除去html标签
	 * 
	 * @param str
	 *            源字符串
	 * @return 目标字符串
	 */
	public static String removeHTMLLable(String str) {

		str = stringReplace(str, "\\s", "");// 去掉页面上看不到的字符
		str = stringReplace(str, "<br ?/?>", "\n");// 去<br><br />
		str = stringReplace(str, "<([^<>]+)>", "");// 去掉<>内的字符
		str = stringReplace(str, "&nbsp;", " ");// 替换空格
		str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");// 去<br><br />
		return str;
	}

	/**
	 * @category 去掉HTML标签之外的字符串
	 * 
	 * @param str
	 *            源字符串
	 * @return 目标字符串
	 */
	public static String removeOutHTMLLable(String str) {

		str = stringReplace(str, ">([^<>]+)<", "><");
		str = stringReplace(str, "^([^<>]+)<", "<");
		str = stringReplace(str, ">([^<>]+)$", ">");
		return str;
	}

	/**
	 * 
	 * @category 字符串替换
	 * 
	 * @param str
	 *            源字符串
	 * @param sr
	 *            正则表达式样式
	 * @param sd
	 *            替换文本
	 * @return 结果串
	 */
	public static String stringReplace(String str, String sr, String sd) {

		String regEx = sr;
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		str = m.replaceAll(sd);
		return str;
	}

	/**
	 * 
	 * @category 将html的省略写法替换成非省略写法
	 * @param str
	 *            html字符串
	 * @param pt
	 *            标签如table
	 * @return 结果串
	 */
	public static String fomateToFullForm(String str, String pt) {

		String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		String[] sa = null;
		String sf = "";
		String sf2 = "";
		String sf3 = "";
		for (; m.find();) {
			sa = p.split(str);
			if (sa == null) {
				break;
			}
			sf = str.substring(sa[0].length(), str.indexOf("/>", sa[0].length()));
			sf2 = sf + "></" + pt + ">";
			sf3 = str.substring(sa[0].length() + sf.length() + 2);
			str = sa[0] + sf2 + sf3;
			sa = null;
		}
		return str;
	}

	/**
	 * 
	 * @category 得到字符串的子串位置序列
	 * 
	 * @param str
	 *            字符串
	 * @param sub
	 *            子串
	 * @param b
	 *            true子串前端,false子串后端
	 * @return 字符串的子串位置序列
	 */
	public static int[] getSubStringPos(String str, String sub, boolean b) {

		// int[] i = new int[(new Integer((str.length()-stringReplace( str , sub
		// , "" ).length())/sub.length())).intValue()] ;
		String[] sp = null;
		int l = sub.length();
		sp = splitString(str, sub);
		if (sp == null) {
			return null;
		}
		int[] ip = new int[sp.length - 1];
		for (int i = 0; i < sp.length - 1; i++) {
			ip[i] = sp[i].length() + l;
			if (i != 0) {
				ip[i] += ip[i - 1];
			}
		}
		if (b) {
			for (int j = 0; j < ip.length; j++) {
				ip[j] = ip[j] - l;
			}
		}
		return ip;
	}

	/**
	 * 
	 * @category 根据正则表达式分割字符串
	 * 
	 * @param str
	 *            源字符串
	 * @param ms
	 *            正则表达式
	 * @return 目标字符串组
	 */
	public static String[] splitString(String str, String ms) {

		String regEx = ms;
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		String[] sp = p.split(str);
		return sp;
	}

	/**
	 * @category 根据正则表达式提取字符串,相同的字符串只返回一个
	 * 
	 * @param str源字符串
	 * @param pattern
	 *            正则表达式
	 * @return 目标字符串数据组
	 ************************************************************************* 
	 */

	// ★传入一个字符串，把符合pattern格式的字符串放入字符串数组
	// java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包
	public static String[] getStringArrayByPattern(String str, String pattern) {

		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);
		// 范型
		Set<String> result = new HashSet<String>();// 目的是：相同的字符串只返回一个。。。 不重复元素
		// boolean find() 尝试在目标字符串里查找下一个匹配子串。
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) { // int groupCount()
																// 返回当前查找所获得的匹配组的数量。
				// org.jeecgframework.core.util.Log4jUtil.info(matcher.group(i));
				result.add(matcher.group(i));

			}
		}
		String[] resultStr = null;
		if (result.size() > 0) {
			resultStr = new String[result.size()];
			return result.toArray(resultStr);// 将Set result转化为String[] resultStr
		}
		return resultStr;

	}

	/**
	 * @category 得到第一个b,e之间的字符串,并返回e后的子串
	 * 
	 * @param s
	 *            源字符串
	 * @param b
	 *            标志开始
	 * @param e
	 *            标志结束
	 * @return b,e之间的字符串
	 */

	/*
	 * String aaa="abcdefghijklmn"; String[] bbb=StringProcessor.midString(aaa, "b","l"); org.jeecgframework.core.util.Log4jUtil.info("bbb[0]:"+bbb[0]);//cdefghijk
	 * org.jeecgframework.core.util.Log4jUtil.info("bbb[1]:"+bbb[1]);//lmn ★这个方法是得到第二个参数和第三个参数之间的字符串,赋给元素0;然后把元素0代表的字符串之后的,赋给元素1
	 */

	/*
	 * String aaa="abcdefgllhijklmn5465"; String[] bbb=StringProcessor.midString(aaa, "b","l"); //ab cdefg llhijklmn5465 // 元素0 元素1
	 */
	public static String[] midString(String s, String b, String e) {

		int i = s.indexOf(b) + b.length();
		int j = s.indexOf(e, i);
		String[] sa = new String[2];
		if (i < b.length() || j < i + 1 || i > j) {
			sa[1] = s;
			sa[0] = null;
			return sa;
		} else {
			sa[0] = s.substring(i, j);
			sa[1] = s.substring(j);
			return sa;
		}
	}

	/**
	 * @category 带有前一次替代序列的正则表达式替代
	 * 
	 * @param s
	 * @param pf
	 * @param pb
	 * @param start
	 * @return
	 */
	public static String stringReplace(String s, String pf, String pb, int start) {

		Pattern pattern_hand = Pattern.compile(pf);
		Matcher matcher_hand = pattern_hand.matcher(s);
		int gc = matcher_hand.groupCount();
		int pos = start;
		String sf1 = "";
		String sf2 = "";
		String sf3 = "";
		int if1 = 0;
		String strr = "";
		while (matcher_hand.find(pos)) {
			sf1 = matcher_hand.group();
			if1 = s.indexOf(sf1, pos);
			if (if1 >= pos) {
				strr += s.substring(pos, if1);
				pos = if1 + sf1.length();
				sf2 = pb;
				for (int i = 1; i <= gc; i++) {
					sf3 = "\\" + i;
					sf2 = replaceAll(sf2, sf3, matcher_hand.group(i));
				}
				strr += sf2;
			} else {
				return s;
			}
		}
		strr = s.substring(0, start) + strr;
		return strr;
	}

	/**
	 * @category 存文本替换
	 * 
	 * @param s
	 *            源字符串
	 * @param sf
	 *            子字符串
	 * @param sb
	 *            替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceAll(String s, String sf, String sb) {

		int i = 0, j = 0;
		int l = sf.length();
		boolean b = true;
		boolean o = true;
		String str = "";
		do {
			j = i;
			i = s.indexOf(sf, j);
			if (i > j) {
				str += s.substring(j, i);
				str += sb;
				i += l;
				o = false;
			} else {
				str += s.substring(j);
				b = false;
			}
		} while (b);
		if (o) {
			str = s;
		}
		return str;
	}

	/**
	 * @category 判断是否与给定字符串样式匹配
	 * 
	 * @param str
	 *            字符串
	 * @param pattern
	 *            正则表达式样式
	 * @return 是否匹配是true,否false
	 */
	public static boolean isMatch(String str, String pattern) {

		Pattern pattern_hand = Pattern.compile(pattern);
		Matcher matcher_hand = pattern_hand.matcher(str);
		boolean b = matcher_hand.matches();
		return b;
	}

	/**
	 * @category 截取字符串
	 * 
	 * @param s
	 *            源字符串
	 * @param jmp
	 *            跳过jmp
	 * @param sb
	 *            取在sb
	 * @param se
	 *            于se
	 * @return 之间的字符串
	 */
	public static String subStringExe(String s, String jmp, String sb, String se) {

		if (isEmpty(s)) {
			return "";
		}
		int i = s.indexOf(jmp);
		if (i >= 0 && i < s.length()) {
			s = s.substring(i + 1);
		}
		i = s.indexOf(sb);
		if (i >= 0 && i < s.length()) {
			s = s.substring(i + 1);
		}
		if (se == "") {
			return s;
		} else {
			i = s.indexOf(se);
			if (i >= 0 && i < s.length()) {
				s = s.substring(i + 1);
			}
			return s;
		}
	}

	/**
	 * *************************************************************************
	 * 
	 * @category 对通过URL传输的内容进行编码
	 * 
	 * @param 源字符串
	 * @return 经过编码的内容
	 ************************************************************************* 
	 */
	public static String URLEncode(String src) {

		String return_value = "";
		try {
			if (src != null) {
				return_value = URLEncoder.encode(src, "GBK");

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return_value = src;
			Log4jUtil.error("", e);
		}

		return return_value;
	}

	/**
	 * @category 泛型方法(通用)，把list转换成以“,”相隔的字符串 调用时注意类型初始化（申明类型） 如：List<Integer> intList = new ArrayList<Integer>(); 调用方法：StringUtil.listTtoString(intList); 效率：list中4条信息，1000000次调用时间为850ms左右
	 * 
	 * @author fengliang
	 * @serialData 2008-01-09
	 * @param <T>
	 *            泛型
	 * @param list
	 *            list列表
	 * @return 以“,”相隔的字符串
	 */
	public static <T> String listTtoString(List<T> list) {

		if (list == null || list.size() < 1)
			return "";
		Iterator<T> i = list.iterator();
		if (!i.hasNext())
			return "";
		StringBuilder sb = new StringBuilder();
		for (;;) {
			T e = i.next();
			sb.append(e);
			if (!i.hasNext())
				return sb.toString();
			sb.append(",");
		}
	}

	/**
	 * @category 把整形数组转换成以“,”相隔的字符串
	 * 
	 * @author fengliang
	 * @serialData 2008-01-08
	 * @param a
	 *            数组a
	 * @return 以“,”相隔的字符串
	 */
	public static String intArraytoString(int[] a) {

		if (a == null)
			return "";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "";
		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(a[i]);
			if (i == iMax)
				return b.toString();
			b.append(",");
		}
	}

	/**
	 * @category 判断文字内容重复
	 * 
	 * @author 沙枣
	 * @Date 2008-04-17
	 */
	public static boolean isContentRepeat(String content) {

		int similarNum = 0;
		int forNum = 0;
		int subNum = 0;
		int thousandNum = 0;
		String startStr = "";
		String nextStr = "";
		boolean result = false;
		float endNum = (float) 0.0;
		if (content != null && content.length() > 0) {
			if (content.length() % 1000 > 0)
				thousandNum = (int) Math.floor(content.length() / 1000) + 1;
			else
				thousandNum = (int) Math.floor(content.length() / 1000);
			if (thousandNum < 3)
				subNum = 100 * thousandNum;
			else if (thousandNum < 6)
				subNum = 200 * thousandNum;
			else if (thousandNum < 9)
				subNum = 300 * thousandNum;
			else
				subNum = 3000;
			for (int j = 1; j < subNum; j++) {
				if (content.length() % j > 0)
					forNum = (int) Math.floor(content.length() / j) + 1;
				else
					forNum = (int) Math.floor(content.length() / j);
				if (result || j >= content.length())
					break;
				else {
					for (int m = 0; m < forNum; m++) {
						if (m * j > content.length() || (m + 1) * j > content.length() || (m + 2) * j > content.length())
							break;
						startStr = content.substring(m * j, (m + 1) * j);
						nextStr = content.substring((m + 1) * j, (m + 2) * j);
						if (startStr.equals(nextStr)) {
							similarNum = similarNum + 1;
							endNum = (float) similarNum / forNum;
							if (endNum > 0.4) {
								result = true;
								break;
							}
						} else
							similarNum = 0;
					}
				}
			}
		}
		return result;
	}

	/**
	 * @category 判断是否是空字符串 null和"" null返回result,否则返回字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String isEmpty(String s, String result) {

		if (s != null && !s.equals("")) {
			return s;
		}
		return result;
	}

	/**
	 * @category 判断对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {

		boolean flag = true;
		if (str != null && !str.equals("")) {
			if (str.toString().length() > 0) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * @category 全角字符变半角字符
	 * 
	 * @author shazao
	 * @date 2008-04-03
	 * @param str
	 * @return
	 */
	public static String full2Half(String str) {

		if (str == null || "".equals(str))
			return "";
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c >= 65281 && c < 65373)
				sb.append((char) (c - 65248));
			else
				sb.append(str.charAt(i));
		}

		return sb.toString();

	}

	/**
	 * @category 全角括号转为半角
	 * 
	 * @author shazao
	 * @date 2007-11-29
	 * @param str
	 * @return
	 */
	public static String replaceBracketStr(String str) {

		if (str != null && str.length() > 0) {
			str = str.replaceAll("（", "(");
			str = str.replaceAll("）", ")");
		}
		return str;
	}

	/**
	 * @category 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
	 * 
	 * @param query
	 *            源参数字符串
	 * @param split1
	 *            键值对之间的分隔符（例：&）
	 * @param split2
	 *            key与value之间的分隔符（例：=）
	 * @param dupLink
	 *            重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
	 * @return map
	 * @author sky
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {

		if (!isEmpty(query) && query.indexOf(split2) > 0) {
			Map<String, String> result = new HashMap();

			String name = null;
			String value = null;
			String tempValue = "";
			int len = query.length();
			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);
				if (c == split2) {
					value = "";
				} else if (c == split1) {
					if (!isEmpty(name) && value != null) {
						if (dupLink != null) {
							tempValue = result.get(name);
							if (tempValue != null) {
								value += dupLink + tempValue;
							}
						}
						result.put(name, value);
					}
					name = null;
					value = null;
				} else if (value != null) {
					value += c;
				} else {
					name = (name != null) ? (name + c) : "" + c;
				}
			}

			if (!isEmpty(name) && value != null) {
				if (dupLink != null) {
					tempValue = result.get(name);
					if (tempValue != null) {
						value += dupLink + tempValue;
					}
				}
				result.put(name, value);
			}

			return result;
		}
		return null;
	}

	/**
	 * @category 将list 用传入的分隔符组装为String
	 * 
	 * @param list
	 * @param slipStr
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String listToStringSlipStr(List list, String slipStr) {

		StringBuffer returnStr = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				returnStr.append(list.get(i)).append(slipStr);
			}
		}
		if (returnStr.toString().length() > 0)
			return returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr));
		else
			return "";
	}
	
	/**
	 * @category 将String[] 用传入的分隔符组装为String
	 * 
	 * @param list
	 * @param slipStr
	 * @return String
	 */
	public static String toStringSlipStr(String[] args, String slipStr) {

		StringBuffer returnStr = new StringBuffer();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				returnStr.append(args[i]).append(slipStr);
			}
		}
		if (returnStr.toString().length() > 0)
			return returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr));
		else
			return "";
	}

	/**
	 * @category 获取从start开始用*替换len个长度后的字符串
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param start
	 *            开始位置
	 * @param len
	 *            长度
	 * @return 替换后的字符串
	 */
	public static String getMaskStr(String str, int start, int len) {

		if (StringUtil.isEmpty(str)) {
			return str;
		}
		if (str.length() < start) {
			return str;
		}

		// 获取*之前的字符串
		String ret = str.substring(0, start);

		// 获取最多能打的*个数
		int strLen = str.length();
		if (strLen < start + len) {
			len = strLen - start;
		}

		// 替换成*
		for (int i = 0; i < len; i++) {
			ret += "*";
		}

		// 加上*之后的字符串
		if (strLen > start + len) {
			ret += str.substring(start + len);
		}

		return ret;
	}

	/**
	 * @category 根据传入的分割符号 把传入的字符串分割为List字符串
	 * 
	 * @param slipStr
	 *            分隔的字符串
	 * @param src
	 *            字符串
	 * @return 列表
	 */
	public static List<String> stringToStringListBySlipStr(String slipStr, String src) {

		if (src == null)
			return null;
		List<String> list = new ArrayList<String>();
		String[] result = src.split(slipStr);
		for (int i = 0; i < result.length; i++) {
			list.add(result[i]);
		}
		return list;
	}

	/**
	 * @category 截取字符串
	 * 
	 * @param str
	 *            原始字符串
	 * @param len
	 *            要截取的长度
	 * @param tail
	 *            结束加上的后缀
	 * @return 截取后的字符串
	 */
	public static String getHtmlSubString(String str, int len, String tail) {

		if (str == null || str.length() <= len) {
			return str;
		}
		int length = str.length();
		char c = ' ';
		String tag = null;
		String name = null;
		int size = 0;
		String result = "";
		boolean isTag = false;
		List<String> tags = new ArrayList<String>();
		int i = 0;
		for (int end = 0, spanEnd = 0; i < length && len > 0; i++) {
			c = str.charAt(i);
			if (c == '<') {
				end = str.indexOf('>', i);
			}

			if (end > 0) {
				// 截取标签
				tag = str.substring(i, end + 1);
				int n = tag.length();
				if (tag.endsWith("/>")) {
					isTag = true;
				} else if (tag.startsWith("</")) { // 结束符
					name = tag.substring(2, end - i);
					size = tags.size() - 1;
					// 堆栈取出html开始标签
					if (size >= 0 && name.equals(tags.get(size))) {
						isTag = true;
						tags.remove(size);
					}
				} else { // 开始符
					spanEnd = tag.indexOf(' ', 0);
					spanEnd = spanEnd > 0 ? spanEnd : n;
					name = tag.substring(1, spanEnd);
					if (name.trim().length() > 0) {
						// 如果有结束符则为html标签
						spanEnd = str.indexOf("</" + name + ">", end);
						if (spanEnd > 0) {
							isTag = true;
							tags.add(name);
						}
					}
				}
				// 非html标签字符
				if (!isTag) {
					if (n >= len) {
						result += tag.substring(0, len);
						break;
					} else {
						len -= n;
					}
				}

				result += tag;
				isTag = false;
				i = end;
				end = 0;
			} else { // 非html标签字符
				len--;
				result += c;
			}
		}
		// 添加未结束的html标签
		for (String endTag : tags) {
			result += "</" + endTag + ">";
		}
		if (i < length) {
			result += tail;
		}
		return result;
	}

	// public static String getProperty(String property) {
	// if (property.contains("_")) {
	// return property.replaceAll("_", "\\.");
	// }
	// return property;
	// }

	/**
	 * @category 解析前台encodeURIComponent编码后的参数
	 * 
	 * @param encodeURIComponent
	 *            (encodeURIComponent(no))
	 * @return
	 */
	public static String getEncodePra(String property) {

		String trem = "";
		if (isNotEmpty(property)) {
			try {
				trem = URLDecoder.decode(property, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				Log4jUtil.error("", e);
			}
		}
		return trem;
	}

	/**
	 * @category 判断某个字符串是否存在于数组中
	 * 
	 * @param stringArray
	 *            原数组
	 * @param source
	 *            查找的字符串
	 * @return 是否找到
	 */
	public static boolean contains(String[] stringArray, String source) {

		// 转换为list
		List<String> tempList = Arrays.asList(stringArray);

		// 利用list的包含方法,进行判断
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @category 首字母大写
	 * @param realName
	 * @return
	 */
	  public static String firstUpperCase(String s)
	    {
	        if(Character.isUpperCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	
	
	/**
	 * @category 首字母小写
	 * @param realName
	 * @return
	 */
	  
	  public static String firstLowerCase(String s)
	    {
	        if(Character.isLowerCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	

	/**
	 * @category 判断这个类是不是java自带的类
	 * @param clazz
	 * @return
	 */
	public static boolean isJavaClass(Class<?> clazz) {

		boolean isBaseClass = false;
		if (clazz.isArray()) {
			isBaseClass = false;
		} else if (clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang") || clazz.getPackage().getName().equals("java.math") || clazz.getPackage().getName().equals("java.util")) {
			isBaseClass = true;
		}
		return isBaseClass;
	}

	/**
	 * @category 把对象转换成String
	 * @param obj
	 *            对象
	 * @return String
	 * @modify
	 */
	public final static String toString(Object obj) {

		return obj == null ? "" : obj.toString();
	}

	/**
	 * @category 调试方法
	 * @param obj
	 */
	public final static void debug(Object obj) {

		System.out.println("[DEBUG]:" + toString(obj));
	}

	/**
	 * @category 调试方法
	 * @param obj
	 */
	public final static void debug(Object[] obj) {

		for (Object tmp : obj)
			debug(tmp);
	}

	/**
	 * @category 过滤将要写入到XML文件中的字符串，即过滤掉<![CDATA[和]]>标签
	 * @param obj
	 * @return
	 */
	public static String toXMLFilter(Object obj) {

		if (trim(obj).equals(""))
			return " ";
		return trim(obj).replaceAll("<!\\[CDATA\\[", "&lt;!CDATA").replaceAll("\\]\\]>", "]] >");
	}

	/**
	 * @category 返回一个对象的字符串，多数是处理字符串的
	 * @param obj
	 * @return
	 */
	public static String trim(Object obj) {

		return obj == null ? "" : String.valueOf(obj).trim();
	}

	/**
	 * @category 对一字符串数组进行去空格操作
	 * @param aStr
	 * @return
	 */
	public final static String[] trim(String[] aStr) {

		if (aStr == null)
			return null;
		for (int i = 0; i < aStr.length; i++) {
			aStr[i] = trim(aStr[i]);
		}
		return aStr;
	}

	/**
	 * @category 过滤设置到SQL语句中的字符串
	 * @param aStr
	 * @return
	 */
	public final static String toDBFilter(String aStr) {

		return trim(aStr).replaceAll("\\\'", "''");
	}

	/**
	 * @category 数字字符串的整型转换
	 * 
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果
	 */
	public final static int parseInt(String str, int defaultVal) {

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			Log4jUtil.error("", e);
			return defaultVal;
		}
	}

	/**
	 * @category 数字字符串的整型转换
	 * 
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return Integer 转换后的结果
	 */
	public final static Integer parseInteger(String str, int defaultVal) {

		try {
			if(null==str||str.trim().equals(""))
			{
				return new Integer(defaultVal);
			}else{
				return Integer.valueOf(str);
			}
				
		} catch (NumberFormatException ex) {
			Log4jUtil.error("", ex);
			return new Integer(defaultVal);
		}
	}

	/**
	 * @category 字符串转换成double
	 * @author
	 * @date 2013-3-3 下午06:11:28
	 * @param str
	 * @param defauleVal
	 * @return
	 */
	public final static double parseDouble(String str, double defauleVal) {
		try {
			return Double.valueOf(str);
		} catch (NumberFormatException ex) {
			Log4jUtil.error("", ex);
			return defauleVal;
		}
	}

	/**
	 * @category 字符串转换成double
	 * @author
	 * @date 2013-3-3 下午06:11:28
	 * @param str
	 * @param defauleVal
	 * @return
	 */
	public final static Double parseDoubleObj(String str, double defauleVal) {

		try {
			return Double.valueOf(str);
		} catch (NumberFormatException ex) {
			Log4jUtil.error("", ex);
			return new Double(defauleVal);
		}
	}

	/**
	 * @category 数字字符串的长整型转换
	 * @param str
	 * @return 转换后的结果
	 * @modify
	 */
	public final static Long parseLong(String str) {

		// 初始化默认值为0
		return parseLongDefaultVal(str, 0);
	}

	/**
	 * @category 数字字符串的长整型转换
	 * @param Object
	 * @return 转换后的结果
	 * @modify
	 */
	public final static Long parseLong(Object o) {

		// 初始化默认值为0
		if (o == null) {
			return (long) 0;
		}
		return parseLongDefaultVal(o.toString(), 0);
	}

	/**
	 * @category 数字字符串的长整型转换
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果
	 * @modify
	 */
	public final static Long parseLongDefaultVal(String str, long defaultVal) {

		try {
			return Long.valueOf(str);
		} catch (NumberFormatException ex) {
			Log4jUtil.error("", ex);
			return new Long(defaultVal);
		}
	}

	/**
	 * @category 数字字符串数组转化为长整型数组
	 * @param str
	 * @return
	 */
	public final static Long[] parseLong(String[] str) {

		if (str == null || str.length < 1)
			return new Long[0];
		Long[] result = new Long[str.length];
		for (int i = 0; i < str.length; i++) {
			result[i] = parseLong(str[i]);
		}
		return result;
	}

	/**
	 * @category 数字字符串的整型转换
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果
	 * @modify
	 */
	public final static Integer[] parseInteger(String[] str, int defaultVal) {

		if (str == null || str.length < 1)
			return new Integer[0];
		Integer[] result = new Integer[str.length];
		for (int i = 0; i < str.length; i++) {
			result[i] = parseInteger(str[i], defaultVal);
		}
		return result;
	}

	/**
	 * @category 数字字符串的整型转换
	 * @param str
	 *            数字字符串
	 * @param defaultVal
	 *            默认值
	 * @return 转换后的结果
	 * @modify
	 */
	public final static int[] parseInt(String[] str, int defaultVal) {

		if (str == null || str.length < 1)
			return new int[0];
		int[] result = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			result[i] = parseInt(str[i], defaultVal);
		}
		return result;
	}

	/**
	 * @category 将指定的值从源数组中进行排除，并返回一个新数组
	 * @param src
	 *            原数组
	 * @param target
	 *            指定数组
	 * @return 排除后的新数组
	 * @modify
	 */
	public final static int[] exclude(int[] src, int[] target) {

		if (target == null || target.length < 1)
			return src;
		StringBuilder tmp = new StringBuilder();
		for (int tt : src) {
			if (!include(target, tt))
				tmp.append(tt + ",");
		}
		if (tmp.length() > 1 && tmp.charAt(tmp.length() - 1) == ',') {
			tmp.deleteCharAt(tmp.length() - 1);
		}
		if (tmp.toString().trim().length() < 1)
			return new int[0];
		String[] array = tmp.toString().split(",");
		return parseInt(array, 0);
	}

	/**
	 * @category 将指定的target数组从src源数组中进行排除.
	 * @param src
	 *            原数组
	 * @param target
	 *            指定数组
	 * @return 排除后的新数组
	 * @modify
	 */
	public final static String[] exclude(String[] src, String[] target) {

		if (target == null || target.length < 1)
			return src;
		StringBuilder tmp = new StringBuilder();
		for (String str : src) {
			if (!include(target, str))
				tmp.append(str + ",");
		}
		if (tmp.length() > 1 && tmp.charAt(tmp.length() - 1) == ',') {
			tmp.deleteCharAt(tmp.length() - 1);
		}
		if (tmp.toString().trim().length() < 1)
			return new String[0];
		return tmp.toString().split(",");
	}

	/**
	 * @category 将指定的数组字符串使用指定的符号进行连接
	 * @param src
	 * @param spliter
	 * @return
	 */
	public final static String join(Object[] src, String spliter) {

		if (src == null || src.length < 1)
			return "";
		StringBuffer tmp = new StringBuffer();
		// String mySpliter = trim(spliter).intern() == "" ? "," : spliter;
		String mySpliter = trim(spliter);
		for (int i = 0; i < src.length; i++) {
			tmp.append(src[i]);
			if (i < src.length - 1)
				tmp.append(mySpliter);
		}
		return tmp.toString();
	}

	/**
	 * @category 将指定的数组字符串使用指定的符号进行连接.
	 * @param src
	 * @param spliter
	 * @return
	 */
	public final static String join(int[] src, String spliter) {

		if (src == null || src.length < 1)
			return "";
		StringBuffer tmp = new StringBuffer();
		String mySpliter = trim(spliter).intern() == "" ? "," : spliter;
		for (int i = 0; i < src.length; i++) {
			tmp.append(src[i] + mySpliter);
		}
		return tmp.deleteCharAt(tmp.length() - 1).toString();
	}

	/**
	 * @category 将指定的字符串数组使用指定的字符串进行保围，比如一字符串数组如下： ["hello", "world"],使用的包围字符串为"'",那么返回的结果就应该是： ["'hello'","'world'"].
	 * @param src
	 * @param str
	 * @return
	 * @modify
	 */
	public final static String[] arround(String[] src, String str) {

		if (src == null || src.length < 1)
			return src;
		String[] result = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			result[i] = str + src[i] + str;
		}
		return result;
	}

	/**
	 * @category 判断指定的字符串是否是空指针或空串
	 * @param src
	 * @return
	 */
	public final static boolean isNullAndBlank(String src) {

		return trim(src).intern() == "";
	}

	/**
	 * @category 将指定的字符串转换成符合JavaBean规范的方法名称！ 此方法将只转换第一个字母为大写字母，比如有一字符串是 helloWorld,那么转换后就是：setHelloWorld.另外如果给出 的字符串为空（null或""），那么将直接返回空字符串！
	 * @param name
	 * @return
	 * @modify
	 */
	public final static String toMethodName(String name) {

		String tmp = trim(name).intern();
		if (tmp == "")
			return "";
		if (tmp.length() < 2) {
			return "set" + name.toUpperCase();
		} else {
			return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		}
	}

	/**
	 * @category 利用反射将指定对象的属性打印成字符串形式！
	 * @param obj
	 *            对象
	 * @return String
	 * @modify
	 */
	public final static String reflectObj(Object obj) {

		if (obj == null)
			return "";
		return ToStringBuilder.reflectionToString(obj);
	}

	/**
	 * @category 将map中的键和值进行对应并返回成字符串.
	 */

	public final static String mapToString(Map<?, ?> map) {

		if (map == null)
			return null;
		StringBuilder buf = new StringBuilder("[");
		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			buf.append(String.valueOf(key) + ":" + String.valueOf(map.get(key)) + ",");
		}
		if (buf.charAt(buf.length() - 1) == ',') {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.append(']').toString();
	}

	/**
	 * @category 检查给定的数组长度是否一致，若全部一致则返回true，否则返回false
	 * @param array
	 * @return
	 */
	public final static boolean sameLength(String[]... array) {

		if (array.length <= 1)
			return true;
		for (int i = 0; i < array.length; i++) {
			String[] str1 = array[i];
			for (int j = i + 1; j < array.length; j++) {
				String[] str2 = array[j];
				if (str1 == null && str2 == null)
					continue;
				if (str1 == null && str2 != null)
					return false;
				if (str1 != null && str2 == null)
					return false;
				if (str1.length != str2.length)
					return false;
			}
		}
		return true;
	}

	/**
	 * @category 检查指定的数组中是否包含了指定的数字.
	 * @param source
	 * @param test
	 * @return
	 */
	public final static boolean include(int[] source, int test) {

		if (source == null || source.length < 1)
			return false;
		for (int tmp : source) {
			if (tmp == test)
				return true;
		}
		return false;
	}

	/**
	 * @category 检查指定的字符串数组中是否包含了指定的字符串.
	 * @param source
	 * @param test
	 * @return
	 */
	public final static boolean include(String[] source, String test) {

		if (source == null || source.length < 1)
			return false;
		for (String tmp : source) {
			if (tmp == null && test == null)
				return true;
			if (tmp != null && tmp.equals(test))
				return true;
		}
		return false;
	}

	/**
	 * @category 检查指定的字符串数组中是否包含了指定的字符串，不区分大小写.
	 * @param source
	 * @param test
	 * @return
	 */
	public final static boolean includeIgnoreCase(String[] source, String test) {

		if (source == null || source.length < 1)
			return false;
		for (String tmp : source) {
			if (tmp == null && test == null)
				return true;
			if (tmp != null && tmp.equalsIgnoreCase(test))
				return true;
		}
		return false;
	}

	/**
	 * @category 检查指定的字符串数组source1是否包含另外一个字符串数组source2，不区分大小写.
	 * @param source
	 * @param test
	 * @return
	 */
	public final static boolean includeIgnoreCaseStrings(String[] source1, String[] source2) {

		if (source1 == null || source1.length < 1)
			return false;
		if (source2 == null || source2.length < 1)
			return false;

		for (String tmp2 : source2) {
			boolean isFind1 = false; // 记录2中的某一个是否存在1中
			for (String tm1 : source1) {
				if (tm1.equals(tmp2)) {
					isFind1 = true;
					break;
				}
			}
			if (!isFind1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @category 将指定字符串的首字母变成大写.
	 * @param str
	 * @return
	 */
	public final static String capitalize(String str) {

		if (str == null || str.length() < 1)
			return str;
		if (str.length() == 1)
			return str.toUpperCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * @category 将指定字符串的首字母变成小写.
	 * @param str
	 * @return
	 */
	public final static String unCapitalize(String str) {

		if (str == null || str.length() < 1)
			return str;
		if (str.length() == 1)
			return str.toLowerCase();
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * @category 获取当前日期形成的路径字符串.
	 * @return
	 */
	public final static String getDailyDirectory() {

		return DateUtils.getFormateNowDate("yyyy/MM/dd/");
	}

	/**
	 * @category 反转字符串.
	 * @param str
	 * @return
	 */
	public final static String reverse(String str) {

		if (trim(str).equals(""))
			return str;
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * @category 格式化字符串为四的倍数 不够就在前面添 0 同时将 后面 小数点去掉
	 * @param str
	 * @return
	 */
	public final static String formatString(String str) {

		// int r = str.indexOf(".");
		if (str.indexOf(".") > 0) {
			str = str.substring(0, str.length() - 3);
		}

		Long trueValue = Long.parseLong(str) + 1;
		String trueValueStr = trueValue.toString();
		// 对trueValueStr 前面补0；
		int r2 = 4 - (trueValueStr.length() % 4);
		if (r2 < 4 && r2 > 0) {
			for (int i = 0; i < r2; i++) {
				trueValueStr = "0" + trueValueStr;
			}
		}
		return trueValueStr;
	}

	/**
	 * @category 将 OBJ 转换 成 String 防止空串的时候报错
	 * @param obj
	 * @return
	 */
	public final static String ObjecttoString(Object obj) {

		if (obj == null || obj.equals("")) {
			return " ";
		} else {
			return obj.toString();
		}
	}

	/**
	 * @category string转换成Integer
	 * @param str
	 * @return
	 */
	public final static Integer StringToInteger(String str) {

		if (str.equals("null") || str.equals("") || str == null) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}

	/**
	 * 编码转换
	 * 
	 * @category date 2013-3-2 下午09:21:25
	 * @author five
	 * @param oldString
	 * @param sourseCode
	 * @param newCode
	 * @return
	 */
	public final static String stringTranscoding(String oldString, String sourseCode, String newCode) {

		String resultString = "";
		try {
			resultString = new String(oldString.getBytes(sourseCode), newCode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		return resultString;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public final static String judgeNull(Object obj) {

		if (obj instanceof Integer) {
			if (obj == null || obj.equals(null)) {
				return "0";
			} else {
				return obj.toString();
			}
		} else {
			if (obj == null || obj.equals(null)) {
				return " ";
			} else {
				return obj.toString();
			}
		}

	}

	// 取 sysDicSelfCode 后面的3位，小于999时候，转换成int,并且+1，再转换成string 补 0
	public static String stringAddOne(String arg1) {

		// 不符合要求的数据，直接返回0。
		if (arg1.length() != 3 || arg1.equals("999"))
			return "0";

		// 符合要求的数据，+1
		Integer strInt = Integer.parseInt(arg1) + 1;

		// 符合要求的数据 转换成字符串,并且补0
		arg1 = strInt.toString();
		int ZeroNum = 3 - arg1.length();
		for (int i = 0; i < ZeroNum; i++) {
			arg1 = "0" + arg1;
		}
		return arg1;
	}

	
	/**
	 * @category  数字字符串后面加补 0  以达到指定的长度
	 * @author five
	 * @date 2015-6-26
	 * @time 下午6:04:38
	 * @param sourceStr
	 * @param length
	 * @return
	 * @return String
	 */
	public static String getAddO(String sourceStr,int length)
	{
		int sourceStrLength = sourceStr.length();
		for(int i=0;i<length-sourceStrLength;i++)
		{
			sourceStr = sourceStr+"0";
		}
		return sourceStr;
	}
	

	/**
	 * @category生成树级子内部码,如父级是110000 生成的子级是111100
	 * @author xsh
	 * @date 2015-8-21
	 * @time 下午5:11:11
	 * @param fatherCode //父级的内部码
	 * @param maxSameFather //同级的最大内码,如果没有同级可填空 
	 * @param length //生成的内部码位数.位数不够0补全
	 * @return 子内部码
	 * @return String
	 */
	public static String generationInnerCode(String fatherCode,String maxSameFather,int length) {
		
		//if(resResourcestypeFatherId==null||resResourcestypeFatherId.equals("")) return "0";
		
		  String  result = "";
         // 1、获取上级类型的内码
//		  ResResourcestype resResourcestype = this.findById(resResourcestypeFatherId);
//		String fatherCode = resResourcestype.getResResourcestypeSelfCode();//resResourcestypeSelfCode
		 // 2、获取同级组织的最大内码
		
		// String sqlMaxCode = " SELECT MAX(resResourcestypeSelfCode) as mc FROM res_resourcestype where resResourcestypeFatherId = ? ";
		 //  List<HashMap> listHm = (List<HashMap>) this.findListMap(sqlMaxCode, new String[]{resResourcestypeFatherId});
		   if(StringUtil.isNullAndBlank(maxSameFather))
		   {
			   //表示没有同级组织    则  父号 +10
			   //如果 父号码是 0 就直接是10 了
			   if(fatherCode==null || fatherCode.equals("0")||fatherCode.equals(""))
			   {
				   result = "11";
			   }else
			   {
				// 如果不存在同级组织的最大内码，则在上级组织尾巴增加11变成本级组织的内码
				   fatherCode = StringUtil.getReduO(fatherCode);

					result = fatherCode + "11";

			   }
			   
		   }else
		   {
			   //表示有同级组织    则  同级+1 3、如果存在同级组织的最大内码，则加1
			  // String maxSameFather = listHm.get(0).get("mc").toString();
			   maxSameFather = StringUtil.getReduO(maxSameFather);
			   Long i= Long.parseLong(maxSameFather)+1;
			   //如果为0结尾的,要再加1,例如可防止出现:20 跳到30
			   if(i.toString().endsWith("0")){
				   i = i+1;
			   }
			   result =""+i.longValue(); 
		   }
		   
		   //开始补码

		   return  StringUtil.getAddO(result,length);
	}
	
	/**
	 * @category  去掉数字字符串后面的0 ，对去掉后的数字个位数加1  ，再补0获取组织内码
	 * @author five
	 * @date 2015-6-29
	 * @time 上午10:32:07
	 * @param sourceStr
	 * @return
	 * @return String
	 */
	public static String getBigStr(String sourceStr)
	{
		String newStr = "";
		if(sourceStr.substring(sourceStr.length()-1,sourceStr.length()).equals("0"))
		{
			String  newStrTemp = sourceStr.substring(0,sourceStr.length()-1);
			newStr = getBigStr(newStrTemp);
		}else
		{
			Integer newBigInt = Integer.parseInt(sourceStr)+1;
			newStr= getAddO((""+newBigInt),12);
		}

		return newStr;

	}
	
	/**
	 * @category  去掉将web敏感性的符号转换
	 * @author xsh
	 * @date 2015-9-11
	 * @time 上午10:32:07
	 * @param sourceStr
	 * @return
	 * @return String
	 */
	public static String getWebTagReplace(String sourceStr)
	{
		if(StringUtil.isEmpty(sourceStr)){
			return "";
		}
		sourceStr = sourceStr.replace('＜','<' ).replace('＞','>' ).replace("“", "'").replace("‘", "'").replace("’", "'").replace("“", "'").replace("＆", "&");

		return sourceStr;

	}
	
	/**
	 * @category  去掉后面字符串的0，然后+1，再补0 ，字符串前面非0字符串的长度必须为偶数且不能包含0
	 * @author hejianhui 
	 * @date 2015-8-20
	 * @time 上午17:58:07
	 * @param sourceStr
	 * @return
	 * @return String
	 */
	public static String getBigString(String sourceStr,int length)
	{
		int index = sourceStr.indexOf("0");
		String str = sourceStr.substring(index-2, index);
		int value = Integer.valueOf(str)+1;
		if(value%10==0){
			value = value+1;
		}
		String preStr = sourceStr.substring(0, index-2);
		
		String orderStr = getAddO(preStr+value,length);
		
		return orderStr;

	}
	
	/**
	 * @category  去掉尾部的 0 
	 * @author five
	 * @date 2015-7-7
	 * @time 下午4:00:48
	 * @param sourceStr
	 * @return
	 * @return String
	 */
	public static String getReduO(String sourceStr)
	{
		String newStr = "";
		if(sourceStr.substring(sourceStr.length()-1,sourceStr.length()).equals("0"))
		{
			String  newStrTemp = sourceStr.substring(0,sourceStr.length()-1);
			newStr = getReduO(newStrTemp);
		}else
		{
			return sourceStr;
		}
		return newStr;
	}
	
	
	
	/**
	 * @category  两个字符串数组相加
	 * @author five
	 * @date 2015-7-7
	 * @time 下午3:58:54
	 * @param arrayOne
	 * @param arrayTwo
	 * @return
	 * @return String[]
	 */
	public static String[] addTwoArray(String[] arrayOne,String[] arrayTwo)
	{
		
		if(arrayOne==null&&arrayTwo!=null)
		{
			return arrayTwo;
		}
		if(arrayOne!=null&&arrayTwo==null)
		{
			return arrayOne;
		}
		if(arrayOne==null&&arrayTwo==null)
		{
			return new String[]{};
		}
		if(arrayOne.length<=0&&arrayTwo.length>0)
		{
			return arrayTwo;
		}
		if(arrayOne.length>0&&arrayTwo.length<=0)
		{
			return arrayOne;
		}
		if(arrayOne.length<=0&&arrayTwo.length<=0)
		{
			return new String[]{};
		}
		
		ArrayList<String> resultList = new ArrayList<String>();
		
		for(String str:arrayOne)
		{
			resultList.add(str);
		}
		for(String str:arrayTwo)
		{
			resultList.add(str);
		}
	
		String[] strs = new String[resultList.size()];
		for(int i = 0 ;i< resultList.size();i++)
		{
			strs[i]=resultList.get(i).toString();
		}
		return strs;
		
	}
	
	
	/**
	 * @category 获取文件扩展名,所有文件通用
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {

		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i + 1)).toLowerCase();
			}
		}
		return null;
	}
	
	/*
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	  public static boolean isInteger(String str) {  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	  }
	  
	  
	  public static boolean isDouble(String str){
		  try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			logger.warn("解析double错误");
			return false;
		}
	  }

	
	public static void main(String[] args)
	{     String xbm = null;
		if(("0").equals(xbm)){
			 
		}else{
			 
		}
		System.out.println(xbm);
	}
	
}
