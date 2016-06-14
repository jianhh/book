package com.ithxh.baseCore.model;

import java.util.List;

/**
 * @category 工具类 - 返回结果  专给视频对接使用的返回对象
 * @author five
 */

public class VideoReturnMessage<T> {

	private boolean result;  // 返回的结果,例如 : 0表示失败  1表示成功  2表示其它状态 

	private String message;   // 结果对应信息 ,例如 ： “成功”

	private T o;     // 单个对象 ， 例如 ： admin HashMap<admin> 如果是查询，这里放查询参数的VO

	private List<T> list; // 如果是返回列表对象，就存放在这里   例如： list<BookingVo>


	public boolean isResult() {

		return result;
	}

	public void setResult(boolean result) {

		this.result = result;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public T getO() {

		return o;
	}

	public void setO(T o) {

		this.o = o;
	}

	public List<T> getList() {

		return list;
	}

	public void setList(List<T> list) {

		this.list = list;
	}

}
