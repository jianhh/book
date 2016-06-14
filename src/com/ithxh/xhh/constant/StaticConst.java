package com.ithxh.xhh.constant;


/**
 * @category 工具类 - 静态常量
 */
public class StaticConst {

	/**
	 * 储存验证码的Session Key
	 */
	public static final String SESSION_KEY_OF_RAND_CODE = "RAND_CODE";

	/**
	 * 储存短信验证码的Session Key
	 */
	public static final String SESSION_KEY_OF_SMS_CODE = "SMS_CODE";

	
	public static final String UPLOAD_TEMP_DIR = "/temp";
	
	//表单重复提交
	public static final String RESUBMIT_TOKEN = "resubmit.token";
	
	//后台登录账号
	public static final String BACK_LOGIN_ACCOUNT = "back_login_account";
	
	//后台登录账号
	public static final String FRONT_LOGIN_ACCOUNT = "front_login_account";
	
	//错误页面
	public static final String ERROR_PAGE = "error/error";
	
	//服务器文件保存基础路径
	public static final String SYS_FILE_PATH = "F:/eclipse/Workspaces/MyEclipse 2015 c2/.metadata/.me_tcat7/webapps/ROOT";
	
	
	//网络图片保存路径-京东
	public static final String JD_BOOKPIC_PATH = "/xhh/book/official/net/jingdong/";
	//网络图片保存路径-天猫
	public static final String TM_BOOKPIC_PATH = "/xhh/book/official/net/tianmao/";
	//网络图片保存路径-豆瓣
	public static final String DB_BOOKPIC_PATH = "/xhh/book/official/net/douban/";
	//网络图片保存路径-亚马孙
	public static final String AMA_BOOKPIC_PATH = "/xhh/book/official/net/amazon/";
	
	
	//网络图片保存路径-亚马孙
	public static final String BOOKPOCKET = "book_pocket";
	
	
	
	

	public enum AndOrOr {
		AND(" and "), OR(" or "), ;

		private String code;

		private AndOrOr(String code) {

			this.code = code;

		}

		public String getCode() {

			return code;
		}
	}
	
	public enum NETBOOKSOURCE {
		JD("jingdong"), BD("baidu"),
		DB("douban"), TM("tianmao");

		private String code;

		private NETBOOKSOURCE(String code) {

			this.code = code;
		}

		public String getCode() {

			return code;
		}
	}
	
	
	/**
	 * @category 返回枚举
	 * @author 何建辉
	 * @date 2015年10月26日 上午9:51:27
	 */
	public enum ReturnMsg{
		
		ADD_FAIL("add_fail",false,"添加失败!"),
		ADD_SUCCESS("add_success",true,"添加成功!"),
		DELETE_FAIL("delete_fail",false,"删除失败!"),
		DELETE_SUCCESS("delete_success",true,"删除成功!"),
		UPDATE_FAIL("update_fail",false,"修改失败!"),
		UPDATE_SUCCESS("update_success",true,"修改成功!"),
		NOT_FOUND("not_found",false,"没有查询到对应的记录!"),
		REPEAT_ACCOUNT("repeat_account",false,"该账号已注册"),
		NOT_NULL("not_null",false,"信息不能为空"),
		BASE_TRUE("true",true,""),
		BASE_FALSE("false",false,"");
		
		private String code;
		private boolean result;
		private String value;
		
		private ReturnMsg(String code,boolean result,String value){
			this.code = code;
			this.result = result;
			this.value = value;
		}
		
		public String getCode(){
			return this.code;
		}
		
		public boolean getResult(){
			return this.result;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * @description 业务文件类型 枚举 
	 * @author hxh
	 * @date 2016年1月23日 下午3:45:04
	 */
	public enum FileType{
		
		IMG("img","图片"),
		VIDEO("video","视频"),
		DOCUMENT("document","文档"),
		OTHER("other","其他");
		
		private String code;
		private String value;
		
		private FileType(String code,String value){
			this.code = code;
			this.value = value;
		}
		
		public String getCode(){
			return this.code;
		}
		
		public String getValue(){
			return this.value;
		}
	}

}
