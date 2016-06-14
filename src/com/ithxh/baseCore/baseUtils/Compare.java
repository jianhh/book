package com.ithxh.baseCore.baseUtils;

import java.util.List;

/***
 * 比较数组中数的大小
 * 
 * @author 梁永明 2014-06-12 17:18
 */
public class Compare {

	/***
	 * 
	 * @param i
	 * @return 最大的数
	 */
	public static String compareMax(int[] i) {

		int size = i.length;
		for (int a = 1; a < size; a++) {
			for (int b = 0; b < size - a; b++) {
				if (i[b] > i[b + 1]) {
					int temp = i[b];
					i[b] = i[b + 1];
					i[b + 1] = temp;

				}
			}
		}
		return "" + i[size - 1];

	}

	/***
	 * @param list
	 * @return 封装比较的数为数组
	 */
	public static int[] compareGet(List<?> list) {

		int[] maxCode = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			maxCode[i] = Integer.parseInt(list.get(i).toString());
		}
		return maxCode;
	}
}