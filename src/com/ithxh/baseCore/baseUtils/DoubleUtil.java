package com.ithxh.baseCore.baseUtils;

public class DoubleUtil {

	/**
	 * @category string 类型转换成 double类型
	 * @param str
	 * @return
	 */
	public final static double StringToDouble(String str) {

		if (str.equals("null") || str.equals("") || str == null) {
			return 0;
		} else {
			return Double.parseDouble(str);
		}
	}
}
