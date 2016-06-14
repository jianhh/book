package com.ithxh.baseCore.model;

import java.util.List;
import java.util.Map;

import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;


/**
 * @category 工具类 - 返回结果
 * @author Administrator
 * 
 */
public class ReturnMessage<T> {

	private boolean result; // 返回的结果,例如 : true

	private String message; // 结果对应信息 ,例如 ： “登录成功”

	private T o; // 单个对象 ，例如 ： admin HashMap<admin> 如果是查询，这里放查询参数的VO

	private List<T> list; // 列表对象 例如： list<pager>

	private Map<?, ?> map; // 单个对象 ，例如 ： HashMap<admin>
	
	private Pager pager; // 放置分页数据

	private String state; // 如果true 和 false 不足以表达所有含义的时候，就用这个多种结果代码，
							// 以便让页面根据这个做需要的业务逻辑判断

	private int isTimeOut = 0; // session是否超时

	private String id; // 如果是添加，等操作需要返回ID，就放在这里
	
	
	public static <T> ReturnMessage<T> get(ReturnMsg rm,String message){
		ReturnMessage<T> r = new ReturnMessage<T>();
		
		if(StringUtil.isEmpty(message)){
			message = rm.getValue();
		}
		
		r.setResult(rm.getResult());
		r.setMessage(message);
		return r;
	}
	
	public  static <T> ReturnMessage<T> get(ReturnMsg rm){
		ReturnMessage<T> r = new ReturnMessage<T>();
		
		r.setResult(rm.getResult());
		r.setMessage(rm.getValue());
		return r;
	}


	public Map<?, ?> getMap() {
		return map;
	}

	public void setMap(Map<?, ?> map) {
		this.map = map;
	}

	public int getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(int isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Pager getPager() {

		return pager;
	}

	public void setPager(Pager pager) {

		this.pager = pager;
	}

	public String getState() {

		return state;
	}

	public void setState(String state) {

		this.state = state;
	}

}
