package com.ithxh.baseCore.baseUtils;
//package com.tengo.core.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 字典类二 叉树类顺序生成
// */
//public class MultipleSysDicTree {
//
//	// 存放结果列表
//	List<SysDic> resultList = new ArrayList<SysDic>();
//
//	List<SysDic> dataList = new ArrayList<SysDic>();
//
//	public MultipleSysDicTree(List<SysDic> dataList) {
//
//		this.dataList = dataList;
//	}
//
//	/**
//	 * @category 获取父亲Id 获取子Id列表,并对子ID执行排序
//	 * @param dataList
//	 * @return
//	 */
//	public List<SysDic> getChildSysDic(String fatherId) {
//
//		// 获取列表
//		List<SysDic> rootList = new ArrayList<SysDic>();
//		for (int i = 0; i < dataList.size(); i++) {
//
//			if (dataList.get(i).getSysDicFatherSelfCode().trim().equals(fatherId)) {
//				rootList.add(dataList.get(i));
//			}
//		}
//		// 执行排序
//		rootList = sortSysDicByCodeMaoPao(rootList);
//		for (int i = 0; i < rootList.size(); i++) {
//			resultList.add(rootList.get(i));
//			// System.out.println(rootList.get(i).getSysDicSelfCode()+"------------"+rootList.get(i).getSysDicValue());
//			getChildSysDic(rootList.get(i).getSysDicSelfCode());
//		}
//
//		return resultList;
//	}
//
//	/**
//	 * @category 用数据库字段 sysDicByCode 冒泡排序 sysDicByCode 字段的值不能重复,越小越在前面。
//	 */
//	private List<SysDic> sortSysDicByCodeMaoPao(List<SysDic> dataListOrder) {
//
//		for (int i = 0; i < dataListOrder.size() - 1; i++) {
//			for (int j = 1; j < dataListOrder.size() - i; j++) {
//				SysDic sysDicTemp;
//				if ((dataListOrder.get(j - 1).getSysDicByCode()).compareTo(dataListOrder.get(j).getSysDicByCode()) > 0) { // 比较两个整数的大小
//					sysDicTemp = dataListOrder.get(j - 1);
//					dataListOrder.set((j - 1), dataListOrder.get(j));
//					dataListOrder.set(j, sysDicTemp);
//				}
//			}
//		}
//		// for (SysDic s : dataListOrder) {
//		// System.out.println(s.getSysDicByCode());
//		// }
//		return dataListOrder;
//	}
//
//	/**
//	 * @category 在value 前增加空格
//	 */
//	public static List<SysDic> addNullForValue(List<SysDic> list) {
//
//		for (int i = 0; i < list.size(); i++) {
//			SysDic sysDic = list.get(i);
//			int leng = (int) MatheMaticsUtil.div(sysDic.getSysDicSelfCode().length(), 3);
//			String newValue = " └--" + sysDic.getSysDicValue();
//			String nullValue = "";
//			for (int j = 0; j < leng - 1; j++) {
//				nullValue += "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;";
//			}
//			sysDic.setSysDicValue(nullValue + newValue);
//			// System.out.println(nullValue+newValue);
//			list.set(i, sysDic);
//		}
//		return list;
//	}
//
//}