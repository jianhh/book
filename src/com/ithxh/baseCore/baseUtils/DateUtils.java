package com.ithxh.baseCore.baseUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import org.springframework.util.StringUtils;

/**
 * 
 * 类描述：时间操作定义类
 * 
 * @author: five
 * @date： 日期：2012-12-8 时间：下午12:15:03
 * @version 1.0
 */
public class DateUtils extends PropertyEditorSupport {

	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;

	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {

		return new SimpleDateFormat(pattern);
	}

	/**
	 * @category 当前日历，这里用中国时间表示
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {

		return Calendar.getInstance();
	}

	/**
	 * @category 指定毫秒数表示的日历对象
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {

		Calendar cal = Calendar.getInstance();
		// --------------------cal.setTimeInMillis(millis);
		cal.setTime(new Date(millis));
		return cal;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getDate
	// 各种方式获取的Date
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 获取当前日期
	 * @return 系统当前时间
	 */
	public static Date getDate() {

		return new Date();
	}

	//
	// /**
	// * @category 获取当前时间
	// * @return
	// */
	// public static Date getDateYMD(){
	// String nowDate = DataUtils.getNowDate();
	// Date result = DataUtils.parseDate(nowDate);
	// return result;
	// }

	/**
	 * @category 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {

		return new Date(millis);
	}

	/**
	 * @category 数据库的时间戳转换为字符串 yyyy-MM-dd
	 * @param time
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String timestamp2Str(Timestamp time) {

		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date_sdf);
	}

	/**
	 * @category 字符串转换时间戳
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {

		Date date = str2Date(str, date_sdf);
		return new Timestamp(date.getTime());
	}

	/**
	 * @category 指定字符串用指定格式转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {

		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			Log4jUtil.error("", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @category 今天的日期转换为指定格式字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(SimpleDateFormat date_sdf) {

		Date date = getDate();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * @category 将时间字符串按要求格式转换成另一种格式串
	 * @param data
	 * @param format
	 * @return
	 */
	public static String dataFormat(String data, String format) {

		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sformat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		return sformat.format(date);
	}

	/**
	 * @category 日期转换为指定格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {

		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * @category 今天的日期转换为指定格式字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */

	public static String getDate(String format) {

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @category 生成指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {

		return new Timestamp(millis);
	}

	/**
	 * @category 以Long字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {

		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * @category 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {

		return new Timestamp(new Date().getTime());
	}

	/**
	 * @category 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {

		return new Timestamp(date.getTime());
	}

	/**
	 * @category 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {

		// ---------------------return new Timestamp(cal.getTimeInMillis());
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * @category 按照当前时间的yyyy MM dd HH mm ss字符串格式获取时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp gettimestamp() {

		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
		return buydate;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getMillis
	// 各种方式获取的Millis
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {

		return new Date().getTime();
	}

	/**
	 * @category 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * 
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {

		// --------------------return cal.getTimeInMillis();
		return cal.getTime().getTime();
	}

	/**
	 * @category 指定日期的毫秒数
	 * 
	 * @param date
	 *            指定日期
	 * 
	 * @return 指定日期的毫秒数
	 */
	public static long getMillis(Date date) {

		return date.getTime();
	}

	/**
	 * @category 指定时间戳的毫秒数
	 * 
	 * @param ts
	 *            指定时间戳
	 * @return 指定时间戳的毫秒数
	 */
	public static long getMillis(Timestamp ts) {

		return ts.getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 默认方式表示的系统当前日期 具体格式：yyyy MM dd
	 * 
	 * @return 默认日期按“yyyy-MM-dd“格式显示
	 */
	public static String formatDate() {

		return date_sdf.format(getCalendar().getTime());
	}
	
	/**
	 * @category 默认方式表示的系统当前日期 具体格式：yyyymmddhhmmss
	 * 
	 * @return 默认日期按“yyyymmddhhmmss“格式显示
	 */
	public static String formatMmssDate() {

		return yyyymmddhhmmss.format(getCalendar().getTime());
	}

	/**
	 * @category 指定格式获取当前时间字符串
	 * 
	 */
	public static String getDataString(SimpleDateFormat formatstr) {

		return formatstr.format(getCalendar().getTime());
	}

	/**
	 * @category 指定日期的默认显示 具体格式 yyyy MM dd
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“yyyy-MM-dd“格式显示
	 */
	public static String formatDate(Calendar cal) {

		return date_sdf.format(cal.getTime());
	}

	/**
	 * @category 指定日期的默认显示 具体格式 yyyy MM dd
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“yyyy-MM-dd“格式显示
	 */
	public static String formatDate(Date date) {

		return date_sdf.format(date);
	}

	/**
	 * @category 指定毫秒数表示日期的默认显示 具体格式 yyyy MM dd
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“yyyy-MM-dd“格式显示
	 */
	public static String formatDate(long millis) {

		return date_sdf.format(new Date(millis));
	}

	/**
	 * @category 默认日期按指定格式显示
	 * 
	 * @param pattern
	 *            指定的格式
	 * @return 默认日期按指定格式显示
	 */
	public static String formatDate(String pattern) {

		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	/**
	 * @category 指定日期按指定格式显示
	 * 
	 * @param cal
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Calendar cal, String pattern) {

		return getSDFormat(pattern).format(cal.getTime());
	}

	/**
	 * @category 指定日期按指定格式显示
	 * 
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Date date, String pattern) {

		return getSDFormat(pattern).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 当前日期显示 具体格式 yyyy mm dd HH MM
	 * 
	 * @return 默认日期按“yyy-mm-dd HH:MM“格式显示
	 */
	public static String formatTime() {

		return time_sdf.format(getCalendar().getTime());
	}

	/**
	 * @category 指定毫秒显示 具体格式 yyyy mm dd HH MM
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“yyy-mm-dd HH:MM“格式显示
	 */
	public static String formatTime(long millis) {

		return time_sdf.format(new Date(millis));
	}

	/**
	 * @category 指定日期的默认显示 具体格式 yyyy mm dd HH MM
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“yyy-mm-dd HH:MM“格式显示
	 */
	public static String formatTime(Calendar cal) {

		return time_sdf.format(cal.getTime());
	}

	/**
	 * @category 指定日期的默认显示 具体格式 yyyy mm dd HH MM
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“yyy-mm-dd HH:MM“格式显示
	 */
	public static String formatTime(Date date) {

		return time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatShortTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 当前时间显示 具体格式 hh mm
	 * @return 默认日期按“时：分“格式显示
	 */
	public static String formatShortTime() {

		return short_time_sdf.format(getCalendar().getTime());
	}

	/**
	 * @category 指定毫秒数显示 具体格式 hh mm
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“时：分“格式显示
	 */
	public static String formatShortTime(long millis) {

		return short_time_sdf.format(new Date(millis));
	}

	/**
	 * @category 指定日期的默认显示 具体格式 hh mm
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Calendar cal) {

		return short_time_sdf.format(cal.getTime());
	}

	/**
	 * @category 指定日期的默认显示 具体格式 hh mm
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Date date) {

		return short_time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 根据指定的格式将字符串转换成Date
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Date parseDate(String src, String pattern) throws ParseException {

		return getSDFormat(pattern).parse(src);
	}

	/**
	 * @category 从指定样式的字符串获取 Calendar
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Calendar parseCalendar(String src, String pattern) throws ParseException {

		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * @category 将日期样式字符串 增加 多少天 再日期样式输出
	 * @param src
	 * @param pattern
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	public static String formatAddDate(String src, String pattern, int amount) {

		Calendar cal = null;
		try {
			cal = parseCalendar(src, pattern);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		cal.add(Calendar.DATE, amount);
		return formatDate(cal);
	}

	/**
	 * @category 将日期样式字符串 增加 多少月 再日期样式输出
	 * @param src
	 * @param pattern
	 * @param amount
	 * @author 梁永明
	 * @date 2014-10-13 16:37
	 * @return
	 * @throws ParseException
	 */
	public static String formatAddMonth(String src, String pattern, int amount) {

		Calendar cal = null;
		;
		try {
			cal = parseCalendar(src, pattern);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		cal.add(Calendar.MONTH, amount);
		return formatDate(cal);
	}

	/**
	 * @category 根据指定的格式将字符串转换成Date 如输入 2003-11-19 11:20:20
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的时间戳
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Timestamp parseTimestamp(String src, String pattern) throws ParseException {

		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	// ////////////////////////////////////////////////////////////////////////////
	// dateDiff
	// 计算两个日期之间的差值
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * @category 计算两个时间之间的差值 根据标志的不同而不同 没有月份
	 * 
	 * @param flag
	 *            计算标志，表示按照年/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	@SuppressWarnings("static-access")
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}

	/**
	 * @category 获取当前年份 yyyy
	 * @return
	 */
	public static int getYear() {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @category 将date类型转换成string类型 yyyy-MM-dd kk:mm:ss
	 * @param d
	 * @return
	 */
	public final static String getDateTimeToString(Date d) {

		if (d == null)
			return "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.CHINESE);
		return f.format(d);
	}

	/**
	 * @category 将date类型转换成string类型 yyyy-MM-dd
	 * @param formate
	 * @return
	 */
	public final static String getDateToString(Date d) {

		if (d == null)
			return "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		return f.format(d);
	}

	/**
	 * @category 取得格式化效果的系统日期！ 格式如：yyyy-MM-dd kk:mm:ss
	 * @param formate
	 * @return
	 */
	public final static String getFormateNowDate(String formate) {

		SimpleDateFormat f = new SimpleDateFormat(formate, Locale.US);
		return f.format(new Date());
	}

	/**
	 * @category 获取默认格式的日期和时间 形如 2007-07-08 12:23:54
	 * @return
	 */
	public final static String getNowDateTime() {

		return getFormateNowDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @category 获取默认格式的日期形如 2007-07-08
	 * @return
	 */
	public final static String getNowDate() {

		return getFormateNowDate("yyyy-MM-dd");
	}

	/**
	 * @category 获取当前的年份
	 * @return
	 */
	public final static String getNowYear() {

		return getFormateNowDate("yyyy");
	}

	/**
	 * @category 获取当前的短年份
	 * @return
	 */
	public final static String getNowShortYear() {

		return getFormateNowDate("yy");
	}

	/**
	 * @category 获取当前的月份
	 * @return
	 */
	public final static String getNowMonth() {

		return getFormateNowDate("MM");
	}

	/**
	 * @category 获取当前的短月份
	 * @return
	 */
	public final static String getNowShortMonth() {

		return getFormateNowDate("M");
	}

	/**
	 * @category 获取当前的日期
	 * @return
	 */
	public final static String getNowDay() {

		return getFormateNowDate("dd");
	}

	/**
	 * @category 获取当前的短日期
	 * @return
	 */
	public final static String getNowShortDay() {

		return getFormateNowDate("d");
	}

	/**
	 * @category 获取默认格式的时间(24小时制).形如：16:23:54
	 * @return
	 */
	public final static String getNowTime() {

		return getFormateNowDate("kk:mm:ss");
	}

	/**
	 * @category 判断指定的字符串是否是正确的日期时间字符串 该方法支持日期或日期时间的判断
	 * 
	 * @param dateStr
	 * @return
	 */

	public final static boolean isDate(String dateStr) {

		Date dt = parseSimpleDate(dateStr);
		if (dt != null)
			return true;
		return parseSimpleDateTime(dateStr) != null;

	}

	/**
	 * @category 使用指定的模式来判断字符串是否是日期时间字符串.
	 * @param pattern
	 * @param dateStr
	 * @return
	 */
	public final static boolean isDate(String pattern, String dateStr) {

		return parseSimpleDate(pattern, dateStr) != null;
	}

	/**
	 * @category 将指定的日期时间格式的字符串转换成 日期时间 对象.
	 * @param dateStr
	 * @return
	 */
	public final static Date parseDateTime(String dateStr) {

		try {
			return DateFormat.getDateTimeInstance().parse(dateStr);
		} catch (ParseException ex) {
			Log4jUtil.error("", ex);
			return null;
		}
	}

	/**
	 * @category 将指定日期格式的字符串转换成 日期 对象.
	 * @param dateStr
	 * @return
	 */
	public final static Date parseDate(String dateStr) {

		try {
			return DateFormat.getDateInstance().parse(dateStr);
		} catch (ParseException ex) {
			Log4jUtil.error("", ex);
			return null;
		}
	}

	/**
	 * @category 使用简单化的方式来解析日期时间格式 格式：yyyy-MM-dd kk:mm:ss
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleDateTime(String dateStr) {

		return parseSimpleDate("yyyy-MM-dd kk:mm:ss", dateStr);
	}

	/**
	 * @category 使用简单化的方式来解析日期时间格式格式：yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleDate(String dateStr) {

		return parseSimpleDate("yyyy-MM-dd", dateStr);
	}

	/**
	 * @category 使用简单化的方式来解析日期时间格式格式：kk:mm:ss
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleTime(String timeStr) {

		return parseSimpleDate("kk:mm:ss", timeStr);
	}

	/**
	 * @category 使用指定的模式来解析字符串日期时间.
	 * @param pattern
	 * @param dateStr
	 * @return
	 */
	public final static Date parseSimpleDate(String pattern, String dateStr) {

		try {
			return new SimpleDateFormat(pattern, Locale.US).parse(dateStr);
		} catch (ParseException ex) {
			Log4jUtil.error("", ex);
			return null;
		}
	}

	/**
	 * @category 比较两个日期的大小.返回-1表示date1在date2之前，返回0表示两者相等，返回1 则表示date1在date2之后.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static int compareDate(Date date1, Date date2) {

		if (date1 == null || date2 == null) {
			return 0;
		}

		if (date1.before(date2))
			return -1;
		if (date1.after(date2))
			return 1;
		return 0;
	}

	/**
	 * @category 测试日期date1是否在date2之前.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isBefore(Date date1, Date date2) {

		if (date1 == null || date2 == null)
			return false;
		return date1.before(date2);
	}

	/**
	 * @category 测试日期date1是否在当前时间之前.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isBeforeNow(Date date1) {

		return isBefore(date1, Calendar.getInstance().getTime());
	}

	/**
	 * @category 测试日期date1是否在date2之后.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isAfter(Date date1, Date date2) {

		if (date1 == null || date2 == null)
			return false;
		return date1.after(date2);
	}

	/**
	 * @category 测试日期date1是否在当前时间之后.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isAfterNow(Date date1) {

		return isAfter(date1, Calendar.getInstance().getTime());
	}

	/**
	 * @category 测试日期date1和date2是否相等.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isEquals(Date date1, Date date2) {

		if (date1 == null || date2 == null)
			return false;
		return date1.equals(date2);
	}

	/**
	 * @category 测试日期date1和当前时间是否相等.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isEqualsNow(Date date1) {

		return isEquals(date1, Calendar.getInstance().getTime());
	}

	/**
	 * @category 按照 年 月 日 时 分 秒 的int顺序数组对当前时间执行修正
	 * @param deviation
	 * @return
	 */
	public final static Date getCorrectNowDate(int... deviation) {

		return correctDate(new Date(), deviation);
	}

	/**
	 * @category 按照 年 月 日 时 分 秒 的int顺序数组对date执行修正
	 * @param date
	 * @param deviation
	 *            (按照 年 月 日 时 分 秒 的int顺序数组对date执行修正)
	 * @return
	 */
	public final static Date correctDate(Date date, int... deviation) {

		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		if (deviation.length < 1)
			return cal.getTime();
		final int[] filed = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };
		for (int i = 0; i < deviation.length; i++) {
			cal.add(filed[i], deviation[i]);
		}
		return cal.getTime();
	}

	/**
	 * @category 对日期时间字符串的提示字符串生成方法. 该方法主要是对日期时间字符串的提示,类似:1分钟前,1小时前等.对于大于1天的,则会提示 1天前,2天前等等这样的提示.
	 * @param dateTime
	 *            日期时间字符串,必须包含时间
	 * @return String
	 * @modify
	 */
	public final static String dateTimeAgo(Date dt) {

		Calendar cal = Calendar.getInstance(); // 获取当前日期时间
		long times = cal.getTimeInMillis() - dt.getTime(); // 获取时间差
		if (times <= 60 * 1000L)
			return "1 分钟前";
		else if (times <= 60 * 60 * 1000L)
			return (times / (60 * 1000)) + " 分钟前";
		else if (times <= 24 * 60 * 60 * 1000L)
			return (times / (60 * 60 * 1000L)) + " 小时前";
		else if (times <= 7 * 24 * 60 * 60 * 1000L)
			return (times / (24 * 60 * 60 * 1000L)) + " 天前";
		else if (times <= 30 * 24 * 60 * 60 * 1000L)
			return (times / (7 * 24 * 60 * 60 * 1000L)) + " 星期前";
		else if (times <= 12 * 30 * 24 * 60 * 60 * 1000L)
			return (times / (30 * 24 * 60 * 60 * 1000L)) + " 个月前";
		return (times / (12 * 30 * 24 * 60 * 60 * 1000L)) + " 年前";
	}

	/**
	 * @category 对日期时间字符串的提示字符串生成方法.（最小时间在天） 该方法主要是对日期时间字符串的提示,类似:1分钟前,1小时前等.对于大于1天的,则会提示 1天前,2天前等等这样的提示.
	 * @param dateStr
	 *            字符串类型的日期
	 * @return String
	 * @modify
	 */
	public final static String dateAgo(String dateStr) {

		Date dt = parseSimpleDate(dateStr);
		if (dt == null)
			return dateStr;
		return dateTimeAgo(dt);
	}

	/**
	 * @category 对日期时间字符串的提示字符串生成方法.（最小时间在分钟） 该方法主要是对日期时间字符串的提示,类似:1分钟前,1小时前等.对于大于1天的,则会提示 1天前,2天前等等这样的提示.
	 * @param dateTime
	 *            字符串类型的日期
	 * @return String
	 * @modify
	 */
	public final static String dateTimeAgo(String dateTime) {

		Date dt = parseSimpleDateTime(dateTime); // 转换成日期时间类型
		if (dt == null)
			return dateTime;
		return dateTimeAgo(dt);
	}

	/**
	 * @category java util date转换成java sql date 适合保存数据库
	 * @param d
	 * @return java.sql.Date
	 */
	public static java.sql.Date stringToDate(java.util.Date d) {

		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		return sqlDate;
	}

	/**
	 * @category java util date 转换成java sql Timestamp 适合保存数据库 带时间格式
	 * @param d
	 * @author Jason
	 * @return java.sql.Timestamp 2011-01-01 12:52:00
	 */
	public static java.sql.Timestamp stringToDateTime(java.util.Date d) {

		java.sql.Timestamp time = new java.sql.Timestamp(d.getTime());
		return time;
	}

	/**
	 * @category 将string类型的日期转换成java.sql.date yyyy-MM-dd kk:mm:ss,适合保存数据库
	 * @date 2013-3-19 下午03:09:19
	 * @param date
	 * @return
	 */
	public static java.sql.Date stirngToDateTime(String date) {

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		java.util.Date d = null;
		try {
			d = bartDateFormat.parse(date);
		} catch (ParseException e) {
			Log4jUtil.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		return sqlDate;
	}

	/**
	 * @category 将string类型的日期转换成java.sql.date yyyy-MM-dd ,适合保存数据库
	 * 
	 * @param date
	 * @return java.sql.Date
	 */
	public static java.sql.Date stringToDate(String date) {

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = bartDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		return sqlDate;
	}

	/**
	 * @category 返回2个日期之间相差几个日
	 * 
	 * @param D1
	 *            开始日期（日期类型）
	 * @param D2
	 *            结束日期（日期类型）
	 * @return 相差的天数
	 */
	public static int getTowDateDays(Date D1, Date D2) {

		int returnValue = 0;
		long aL = 0, oneday = 3600 * 24 * 1000;
		aL = D2.getTime() - D1.getTime();
		returnValue = Integer.parseInt(aL / oneday + "");
		return returnValue;
	}

	/**
	 * @category 返回2个日期之间相差几个月
	 * @param D1
	 *            开始日期
	 * @param D2
	 *            结束日期
	 * @author 梁永明
	 * @date 2014-10-14 11:58
	 * @return 相差的月数
	 */
	public static int getTowDateMonths(String D1, String D2) {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(sdf.parse(D1));
			c2.setTime(sdf.parse(D2));
		} catch (ParseException e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 0 : Math.abs(result);

	}

	/**
	 * @category 返回2个日期之间相差几个日
	 * 
	 * @param D1
	 *            开始日期（日期类型）
	 * @param D2
	 *            结束日期（日期类型）
	 * @return 相差的天数
	 */
	public static int getTowDateDays(String D1, String D2) {

		Date D11 = stringToDate(D1);
		Date D22 = stringToDate(D2);

		return getTowDateDays(D11, D22);
	}

	/**
	 * @category 获取几天后的日期 格式是yyyy-MM-dd
	 * @param day
	 * @param x
	 * @return
	 */
	public static Date addDate(String day, int x) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4jUtil.error("", ex);
		}
		// if (date == null) return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		cal = null;
		return date;

	}

	/**
	 * @category 获取明天开始的，几天内的日期列表
	 * @param x
	 * @return
	 */
	public static List<String> getAfterDay(int x)
	{
		List<String> list = new ArrayList<String>();
		String nowDate = getNowDate();
		for(int i=0;i<x;i++)
		{
			Date date = addDate(nowDate,i+1);
			list.add(date_sdf.format(date));
		}
		return list;
	}
	
	
	/**
	 * @category 获取几天后的日期 格式是yyyy-MM-dd HH:mm:ss
	 * @param day
	 * @param x
	 * @return
	 */
	public static Date addDateTime(String day, int x) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4jUtil.error("", ex);
		}
		// if (date == null) return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, x);// 24小时制
		// cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		cal = null;
		return date;
	}

	public static String getMaxDateTime() {

		return "9999-12-31 23:59:59";
	}

	public static String getMinDateTime() {

		return "1900-01-01 00:00:00";
	}

	public static String getMaxDate() {

		return "9999-12-31";
	}

	public static String getMinDate() {

		return "1900-01-01";
	}

	/**
	 * @category 判断betweenDate是否在开始时间startDate和结束时间endDate的闭区间内
	 * @param startDate
	 * @param endDate
	 * @param betweenDate
	 * @author LEIYINING
	 * @return
	 */
	public final static boolean IsBetween(Date startDate, Date endDate, Date betweenDate) {

		int res1 = compareDate(startDate, betweenDate);
		if (res1 == 1) {
			// startDate > betweenDate
			return false;
		}

		int res2 = compareDate(betweenDate, endDate);
		if (res2 == 1) {
			// betweenDate > endDate
			return false;
		}

		return true;
	}

	/**
	 * @category 判断betweenDate是否在开始时间startDate和结束时间endDate的闭区间内
	 * @param startDate
	 * @param endDate
	 * @param betweenDate
	 * @author LEIYINING
	 * @return
	 */
	public final static boolean IsBetween(String startDate, String endDate, String betweenDate) {

		Date startDate1 = parseDate(startDate);
		Date endDate1 = parseDate(endDate);
		Date betweenDate1 = parseDate(betweenDate);

		int res1 = compareDate(startDate1, betweenDate1);
		if (res1 == 1) {
			// startDate > betweenDate
			return false;
		}

		int res2 = compareDate(betweenDate1, endDate1);
		if (res2 == 1) {
			// betweenDate > endDate
			return false;
		}

		return true;
	}

	/**
	 * @category 获取当前 日期内 已经过去的 秒数
	 * @return
	 */
	public static int getNowDataSeconds() {

		String NowDate = DateUtils.getNowTime();
		String[] timess = NowDate.split(":");
		int hours = Integer.parseInt(timess[0]) * 60 * 60;
		int mills = Integer.parseInt(timess[1]) * 60;
		int seco = Integer.parseInt(timess[2]);
		return hours + mills + seco;

	}

	/**
	 * @category 在某一指定的日期基础上进行日期的偏差设置，参数意义同getNowDate
	 * @param date
	 * @param deviation
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public final static Date setDate(Date date, int... deviation) {

		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		if (deviation.length < 1)
			return cal.getTime();
		final int[] filed = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND };
		for (int i = 0; i < deviation.length; i++) {
			cal.add(filed[i], deviation[i]);
		}

		int beforeYear = date.getYear();
		Map<Integer, Integer> dayOfMonth = new HashMap<Integer, Integer>();
		dayOfMonth.put(0, 31);
		// 闰年
		if (((beforeYear % 4 == 0) && (beforeYear % 100 != 0)) || (beforeYear % 400 == 0)) {
			dayOfMonth.put(1, 29);
		} else {
			dayOfMonth.put(1, 28);
		}
		dayOfMonth.put(2, 31);
		dayOfMonth.put(3, 30);
		dayOfMonth.put(4, 31);
		dayOfMonth.put(5, 30);
		dayOfMonth.put(6, 31);
		dayOfMonth.put(7, 31);
		dayOfMonth.put(8, 30);
		dayOfMonth.put(9, 31);
		dayOfMonth.put(10, 30);
		dayOfMonth.put(11, 31);

		if (date.getDate() == dayOfMonth.get(date.getMonth()) && cal.getTime().getDate() >= date.getDate()) {
			int afterYear = cal.getTime().getYear();
			// 闰年
			if (((afterYear % 4 == 0) && (afterYear % 100 != 0)) || (afterYear % 400 == 0)) {
				dayOfMonth.put(1, 29);
			} else {
				dayOfMonth.put(1, 28);
			}

			cal.add(Calendar.DAY_OF_MONTH, dayOfMonth.get(cal.getTime().getMonth()) - cal.getTime().getDate());
		}

		return cal.getTime();
	}
	
	/**
	 * @category 将小时的时间转换成分钟  例如  08:30 转换后 成为  510
	 * @param hours
	 * @return
	 */
	public static int getMinuteByHour(String hours)
	{
		int result = 0;
		if(hours==null||hours.equals("")) return 0;
		String[] hour =hours.split(":");
		if(hour.length>0)
		{
			if(hour[0]!=null)
			{
				result = Integer.parseInt(hour[0])*60;
			}
			if(hour[1]!=null)
			{
				result +=Integer.parseInt(hour[1]);
			}
		}
		return result;
	}
	
	/**
	 * @category 将时间转成多少分钟前,多少小时前,多少天前
	 * @Description 
	 * @Author 何建辉
	 * @date 2015年9月25日  上午11:52:10
	 * @return
	 */
	@SuppressWarnings({"deprecation" })
	public static String tranTimeFormat(String datetime){
		Date date = parseSimpleDateTime(datetime);
		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = date.getMonth()+1;
		int day = date.getDate();
		int hours = date.getHours();
		int minutes = date.getMinutes();
		
		String nowYear = getNowYear();
		String nowMonth = getNowMonth();
		String nowDay = getNowDay();
		String nowDateTime = getNowTime();
		String[] dates = nowDateTime.split(":");
		String nowHours = dates[0];
		String nowMinutes = dates[1];
		
		if(Integer.parseInt(nowYear)-year>0){
			return Integer.parseInt(nowYear)-year+"年前";
		}
		
		if(Integer.parseInt(nowMonth)-month>0){
			return Integer.parseInt(nowMonth)-month+"个月前";
		}
		
		if(Integer.parseInt(nowDay)-day>0){
			return Integer.parseInt(nowDay)-day+"天前";
		}
		
		if(Integer.parseInt(nowHours)-hours>0){
			return Integer.parseInt(nowHours)-hours+"小时前";
		}
		
		if(Integer.parseInt(nowMinutes)-minutes>0){
			return Integer.parseInt(nowMinutes)-minutes+"分钟前";
		}
		
		return "1分钟前";
		
	}
	
	
	
	
	
	// 测试
	public static void main(String[] args) {

		StringUtil.debug("getDateTime : " + getNowDateTime());
		StringUtil.debug("getDate : " + getNowDate());
		StringUtil.debug("getTime : " + getNowTime());
		StringUtil.debug("getFormateDate : " + getFormateNowDate("/yyyy/MM/dd/"));
		StringUtil.debug(DateUtils.isDate("2007-8-29 12:56:15"));
		StringUtil.debug(DateUtils.isDate("2007985-29"));
		StringUtil.debug(DateUtils.parseDateTime("2007-9-23 12:45:58.3"));
		StringUtil.debug(DateUtils.parseDate("2009-9-23 12:45:58"));
		StringUtil.debug(DateUtils.getCorrectNowDate());
		StringUtil.debug(DateUtils.correctDate(DateUtils.parseDateTime("2007-10-29 11:54:34.0"), 0, 0, 3));
		StringUtil.debug(DateUtils.isBefore(DateUtils.correctDate(DateUtils.parseDateTime("2007-10-29 11:54:34.0"), 0, 0, 3), DateUtils.getCorrectNowDate()));
		StringUtil.debug(DateUtils.getNowShortYear() + "/" + DateUtils.getNowShortMonth() + "/" + DateUtils.getNowShortDay());
		StringUtil.debug(DateUtils.dateAgo("2007-11-8 13:9:20"));
		StringUtil.debug(DateUtils.dateAgo("2008-1-8 7:9:20"));
		StringUtil.debug(DateUtils.dateAgo("2007-1-8 7:9:20"));
		StringUtil.debug(DateUtils.dateAgo("2008@1-8 7:9:20"));
		StringUtil.debug(DateUtils.dateAgo("2008-1-8"));
		StringUtil.debug(DateUtils.parseSimpleDateTime("2008-1-8 12:56:52"));
		StringUtil.debug(DateUtils.parseSimpleDate("2008-1-8"));
		StringUtil.debug(DateUtils.parseSimpleTime("22:56:52"));

		System.out.println(DateUtils.getTowDateDays(DateUtils.parseSimpleDate("2011-8-12"), new Date()));
	}

	

}