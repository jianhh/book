package com.ithxh.baseCore.baseUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @category 工具类 - 数组实用方法类
 * @Copyright Copyright(c) 2013
 * @Company
 * @author
 * @version V1.0
 * @date 2013-04-27
 */
public class ListUtil {

	/**
	 * @category 数组去重并排序
	 * @author
	 * @date 2013-4-27 下午02:06:51
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicateWithOrder(List<T> list) {

		return new ArrayList<T>(new LinkedHashSet<T>(list));

	}
	
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection){
		if(collection==null || collection.size()<1){
			return true;
		}
		return false;
	}
	
	/**
	 * @category list转成数组
	 * @Description 
	 * @author 何建辉
	 * @date 2015年11月10日  下午4:16:52
	 * @param list
	 * @return
	 */
	public static String[] toArray(List<String> list){
		String[] str = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			str[i]=list.get(i);
		}
		return str;
	}

}
