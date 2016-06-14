package com.ithxh.baseCore.baseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @category 一个数组根据特殊符号分割成多个数组
 * @author five 2014-05-14
 * 
 */
public class ArrayUtil {

	// private String[] sourceStringArray; //源
	// private ArrayList<String[]> TargetStringArray; //目标

	/**
	 * @category 一个数组根据特殊符号分割成多个数组
	 * @param sourceStringArray
	 * @param sign
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String[]> getListArray(String[] sourceStringArray, String sign) {

		ArrayList resultList = new ArrayList();

		if (null == sourceStringArray) {
			return null;
		}
		if (null == sign || sign.equals("")) {
			resultList.add(sourceStringArray);
			return resultList;
		}

		// 获取结果目标数组列的个数
		String[] firstArrayForLength = sourceStringArray[0].split(sign);
		int targetArraySize = firstArrayForLength.length;

		// 建立 resultList 对象内容
		for (int i = 0; i < targetArraySize; i++) {
			resultList.add(new String[sourceStringArray.length]);
		}

		String[] tempValue = null;
		String[] tempValue2 = null;
		for (int i = 0; i < sourceStringArray.length; i++) {
			tempValue = sourceStringArray[i].split(sign);
			for (int j = 0; j < resultList.size(); j++) {
				tempValue2 = (String[]) resultList.get(j); // 获取每一个list里面的数组
				tempValue2[i] = tempValue[j];
			}
		}

		return resultList;
	}

	/**
	 * @category 一维数组合并
	 * @param al
	 * @param bl
	 * @return
	 */
	public static String[] getMergeArray(String[] al, String[] bl) {

		if (al != null && bl != null) {
			String[] a = al;
			String[] b = bl;
			String[] c = new String[a.length + b.length];
			System.arraycopy(a, 0, c, 0, a.length);
			System.arraycopy(b, 0, c, a.length, b.length);

			return c;
		} else {
			return al != null ? al : bl;
		}
	}

	/**
	 * @category 二维数组纵向合并
	 * @param al
	 * @param bl
	 * @return
	 */
	public static String[][] unite(String[][] content1, String[][] content2) {

		String[][] newArrey = new String[][] {};
		List<String[]> list = new ArrayList<String[]>();
		list.addAll(Arrays.<String[]> asList(content1));
		list.addAll(Arrays.<String[]> asList(content2));
		return list.toArray(newArrey);
	}

	/**
	 * @category 二维数组横向合并
	 * @param al
	 * @param bl
	 * @return
	 */
	public static String[][] getMergeArray(String[][] al, String[][] bl) {

		if (al == null || al.length == 0)
			return bl;
		if (bl == null || bl.length == 0)
			return al;
		String[][] newArrey = null;
		// 根据数组的长度，判断要补全哪个数组
		if (al.length > bl.length) {
			newArrey = new String[al.length][];
			// 补全较短的数组
			String[][] temps = new String[al.length - bl.length][bl[0].length];
			for (int i = 0; i < temps.length; i++) {
				for (int j = 0; j < temps[0].length; j++)
					temps[i][j] = "";
			}
			String[][] btemp = unite(bl, temps);
			// 合并
			for (int k = 0; k < al.length; k++) {
				newArrey[k] = getMergeArray(al[k], btemp[k]);
			}
		} else {
			newArrey = new String[bl.length][];
			String[][] temps = new String[bl.length - al.length][al[0].length];
			for (int i = 0; i < temps.length; i++) {
				for (int j = 0; j < temps[0].length; j++)
					temps[i][j] = "";
			}
			String[][] atemp = unite(al, temps);
			for (int k = 0; k < bl.length; k++) {
				newArrey[k] = getMergeArray(atemp[k], bl[k]);
			}
		}
		return newArrey;
	}

	
	public static String[]  getListReplace(String[] strs)
	{
		String[] result = new String[strs.length];
		if(strs!=null)
		{
			for(int i=0;i<strs.length;i++)
			{
				result[i] = strs[i].replace('＃','#' );
				result[i] = result[i].replace('＜','<' );
				result[i] =result[i].replace('＞','>' );
			}
			
		}
		return result;
	}
 	
	public static void main(String[] args) {

		String[] sss = new String[] { "shopName#like#1#k", "shopManager#=#2#kk", "shopId#<#3#kkk" };
		List<String[]> list = ArrayUtil.getListArray(sss, "#");
		System.out.print(list.size());
	}

}
