package com.ithxh.baseCore.baseUtils;

import java.net.InetAddress;
import java.util.Random;

/**
 * @category UUID生成器
 * @author five
 * 
 */
public class IDGenerator {

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			System.out.println(IDGenerator.uuidGenerate());
		}
	}

	/**
	 * @category 产生一个32位的UUID
	 * 
	 * @return
	 */

	public static String uuidGenerate() {

		return new StringBuilder(32).append(format(getIP())).append(format(getJVM())).append(format(getHiTime())).append(format(getLoTime())).append(format(getCount())).toString().substring(0, 32);

	}

	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			Log4jUtil.error("", e);
			ipadd = 0;
		}
		IP = ipadd;
	}

	private static short counter = (short) 0;

	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	private final static String format(int intval) {

		String formatted = Integer.toHexString(intval);
		StringBuilder buf = new StringBuilder("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	private final static String format(short shortval) {

		String formatted = Integer.toHexString(shortval);
		StringBuilder buf = new StringBuilder("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	private final static int getJVM() {

		return JVM;
	}

	private final static short getCount() {

		synchronized (IDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	private final static int getIP() {

		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	private final static short getHiTime() {

		return (short) (System.currentTimeMillis() >>> 32);
	}

	private final static int getLoTime() {

		return (int) System.currentTimeMillis();
	}

	private final static int toInt(byte[] bytes) {

		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}

	// ******************
	// 生成不重复的18 位 ID
	// ******************
	public static int SEED = 1;

	/**
	 * @category 生成主键18位 yyyyMMddkkmmss 再加4位序列号
	 * @return
	 */
	public static String generatePKID() {

		if (SEED < 9999) {
			SEED += 1;
		} else {
			SEED = 1;
		}
		String textSeed = String.valueOf(SEED);
		while (textSeed.length() < 4) {
			textSeed = String.format("%04d", SEED);
		}
		String textTotalSecs = DateUtils.getFormateNowDate("yyyyMMddkkmmss");
		if (textTotalSecs.length() < 14) {
			textTotalSecs = String.format("%014d", StringUtil.parseInteger(textTotalSecs, 0));
		}
		return textTotalSecs + textSeed;
	}

	/**
	 * @category 生成四位随机数
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String generateCaptcha() {

		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		int res = tmp % (9999 - 1000 + 1) + 1000;
		return String.valueOf(res);
	}

	/**
	 * @category 当天的日期生成 2014/06/12/ 的路径格式
	 * @return 2014/06/12/
	 */
	public static String generateDatePath() {

		return DateUtils.getFormateNowDate("yyyy/MM/dd/");
	}

}
