package com.ithxh.baseCore.baseUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 根据地名请求Geocoding提供的api获取GPS坐标的辅助类
 */
public class GetGpsFromGeoCoding {

	private static Log log = LogFactory.getLog(GetGpsFromGeoCoding.class);

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

	// private static final String URL_PARAM_CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
	}

	public static void main(String[] args) {

		Map<String, String> s = GetGpsFromGeoCoding.askGpsForAreaName("中山市", "");
		System.out.println(s.get("lo"));
		System.out.println(s.get("la"));
	}

	/**
	 * POST方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLPost(String url, Map<String, String> params, String enc) {

		String response = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			// 将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			Log4jUtil.error("", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			Log4jUtil.error("", e);
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		return response;
	}

	/**
	 * @category 根据地名获取GPS address:城市具体地点 city:具体城市
	 */

	public static Map<String, String> askGpsForAreaName(String address, String city) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ak", "53f811e197d38ac52759ad6114b26b18");
		map.put("callback", "renderOption");
		map.put("output", "json");
		map.put("address", address);
		map.put("city", city);

		Map<String, String> gpsMap = new HashMap<String, String>();

		String json = GetGpsFromGeoCoding.URLPost("http://api.map.baidu.com/geocoder/v2/", map, GetGpsFromGeoCoding.URL_PARAM_DECODECHARSET_UTF8);

		if (!StringUtil.isNullAndBlank(json)) {

			json = "[" + json + "]";
			JSONArray array = JSONArray.fromObject(json);
			Object[] o = array.toArray();
			JSONObject obj = JSONObject.fromObject(o[0]);

			if (obj.get("result") != null) {
				String resultJson = obj.get("result").toString();

				resultJson = "[" + resultJson + "]";
				array = JSONArray.fromObject(resultJson);
				o = array.toArray();
				if (o.length < 1) {// 如果查不到相对应的坐标
					gpsMap.put("la", "");
					gpsMap.put("lo", "");
				} else {
					obj = JSONObject.fromObject(o[0]);

					String locationJson = obj.get("location").toString();
					locationJson = "[" + locationJson + "]";
					array = JSONArray.fromObject(locationJson);
					o = array.toArray();
					obj = JSONObject.fromObject(o[0]);

					gpsMap.put("la", obj.getString("lat"));
					gpsMap.put("lo", obj.getString("lng"));
				}
			} else {
				System.out.println("查找不到数据");
				gpsMap.put("la", "");
				gpsMap.put("lo", "");
			}
		} else {
			System.out.println("查找不到数据");
			gpsMap.put("la", "");
			gpsMap.put("lo", "");
		}

		return gpsMap;
	}

}
