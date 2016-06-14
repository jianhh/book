package com.ithxh.baseCore.baseUtils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptUtils {

	/**
	 * JAVA6支持以下任意一种算法 PBEWITHMD5ANDDES PBEWITHMD5ANDTRIPLEDES PBEWITHSHAANDDESEDE PBEWITHSHA1ANDRC2_40 PBKDF2WITHHMACSHA1
	 * */

	/**
	 * 定义使用的算法为:PBEWITHMD5andDES算法
	 */
	public static final String ALGORITHM = "PBEWithMD5AndDES";// 加密算法
	public static final String Salt = "83217654";// 密钥

	/**
	 * 定义迭代次数为1000次
	 */
	private static final int ITERATIONCOUNT = 1000;

	/**
	 * @category 获取加密算法中使用的盐值 8位随机数,解密中使用的盐值必须与加密中使用的相同才能完成操作. 盐长度必须为8字节
	 * 
	 * @return byte[] 盐值 8位随机数
	 * */
	public static byte[] getSalt() throws Exception {

		// 实例化安全随机数
		SecureRandom random = new SecureRandom();
		// 产出盐
		return random.generateSeed(8);
	}

	/**
	 * @category 获取固定的 静态盐值
	 * @return
	 */
	public static byte[] getStaticSalt() {

		// 产出盐
		return Salt.getBytes();
	}

	/**
	 * 根据PBE密码生成一把密钥
	 * 
	 * @param password
	 *            生成密钥时所使用的密码
	 * @return Key PBE算法密钥
	 * */
	private static Key getPBEKey(String password) {

		// 实例化使用的算法
		SecretKeyFactory keyFactory;
		SecretKey secretKey = null;
		try {
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			// 设置PBE密钥参数
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			// 生成密钥
			secretKey = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log4jUtil.error("", e);
		}

		return secretKey;
	}

	/**
	 * 加密明文字符串
	 * 
	 * @param plaintext
	 *            待加密的明文字符串
	 * @param password
	 *            生成密钥时所使用的密码
	 * @param salt
	 *            盐值
	 * @return 加密后的密文字符串
	 * @throws Exception
	 */
	public static String encrypt(String plaintext, String password, byte[] salt) {

		Key key = getPBEKey(password);
		byte[] encipheredData = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

			encipheredData = cipher.doFinal(plaintext.getBytes());
		} catch (Exception e) {
			Log4jUtil.error("", e);
		}
		return bytesToHexString(encipheredData);
	}

	/**
	 * 解密密文字符串
	 * 
	 * @param ciphertext
	 *            待解密的密文字符串
	 * @param password
	 *            生成密钥时所使用的密码(如需解密,该参数需要与加密时使用的一致)
	 * @param salt
	 *            盐值(如需解密,该参数需要与加密时使用的一致)
	 * @return 解密后的明文字符串
	 * @throws Exception
	 */
	public static String decrypt(String ciphertext, String password, byte[] salt) {

		Key key = getPBEKey(password);
		byte[] passDec = null;
		PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
			passDec = cipher.doFinal(hexStringToBytes(ciphertext));
		} catch (Exception e) {
			Log4jUtil.error("", e);
			// TODO: handle exception
		}
		return new String(passDec);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param src
	 *            字节数组
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 将十六进制字符串转换为字节数组
	 * 
	 * @param hexString
	 *            十六进制字符串
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {

		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {

		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	// ************************************
	// md5加密和解密
	// ************************************

	/**
	 * @Description:使用MD5加密指定的字符串.
	 * @category 使用MD5加密指定的字符串
	 * @param s
	 * @return
	 * @author 伍志平
	 * @date Aug 12, 2011
	 * @modify
	 */
	public final static String MD5(String s) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception ex) {
			Log4jUtil.error("", ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Description:加密指定的整型数，通常用来加密ID值！ 编码的原理是根据当前的currentTimeMillis在指定的位置插入其ID值，然后再附加 ID值的长度来进行编码.System.currentTimeMillis获取的值为13位长度，即使100 年后也是这样，所以这个基本上是不变的！
	 * @param id
	 * @return
	 * @author 伍志平
	 * @date Aug 12, 2011
	 * @modify
	 */

	private final static int ENCODE_ID_INSERT_INDEX = 5; // 插入的索引位置，0-12之间

	public final static String encodeId(int id) {

		String _id = String.valueOf(id);
		if (id < 0 || _id.length() > 9)
			return "";
		StringBuilder buf = new StringBuilder();
		buf.append(System.currentTimeMillis());
		for (int i = 0; i < _id.length(); i++) {
			buf.insert(ENCODE_ID_INSERT_INDEX, _id.charAt(i));
		}
		buf.append(_id.length()); // 长度校验位
		return buf.toString();
	}

	/**
	 * @Description:反编码ID后的字符串.<P> 注意，编码和解码的数字长度不能超过9位！
	 * @param str
	 * @return
	 * @author 伍志平
	 * @date Aug 12, 2011
	 * @modify
	 */
	public final static int unEncodeId(String str) {

		String times = System.currentTimeMillis() + "";
		if (str == null || str.length() < times.length())
			return 0;
		if (!str.matches("\\d{13,}"))
			return 0;
		int len = Integer.parseInt(str.substring(str.length() - 1));
		if (str.length() != (times.length() + len + 1))
			return 0;
		return Integer.parseInt(new StringBuilder(str.substring(ENCODE_ID_INSERT_INDEX, ENCODE_ID_INSERT_INDEX + len)).reverse().toString());
	}

	/**
	 * @category 简单的字符替换加密
	 * @param str
	 * @return
	 */
	public static String simpleEncrypt(String str) {

		if (str != null && str.length() > 0) {
			// str = str.replaceAll("0","a");
			str = str.replaceAll("1", "b");
			// str = str.replaceAll("2","c");
			str = str.replaceAll("3", "d");
			// str = str.replaceAll("4","e");
			str = str.replaceAll("5", "f");
			str = str.replaceAll("6", "g");
			str = str.replaceAll("7", "h");
			str = str.replaceAll("8", "i");
			str = str.replaceAll("9", "j");
		}
		return str;

	}

	public static void main(String[] args) {

		int i = 10;
		for (int j = 0; j < i; j++) {
			if ((j) % 3 == 0) {
				System.out.print("<br>");
			} else {
				System.out.print(j);
			}
		}
		System.out.print(-1 % 2 == 0);
		String str = "admin";
		String password = "123456";

		Log4jUtil.info("明文:" + str);
		Log4jUtil.info("密码:" + password);

		try {
			byte[] salt = EncryptUtils.getStaticSalt();
			String ciphertext = EncryptUtils.encrypt(str, password, salt);
			Log4jUtil.info("密文:" + ciphertext);
			String plaintext = EncryptUtils.decrypt(ciphertext, password, salt);
			Log4jUtil.info("明文:" + plaintext);
		} catch (Exception e) {
			Log4jUtil.error("", e);
			e.printStackTrace();
		}

		StringUtil.debug(MD5("123456"));
		// StringUtil.debug(MD5("123"));
		String tmp = encodeId(13455787);
		StringUtil.debug(tmp);
		StringUtil.debug(unEncodeId(tmp));
	}
}