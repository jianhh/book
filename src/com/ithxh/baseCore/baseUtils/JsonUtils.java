package com.ithxh.baseCore.baseUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ithxh.baseCore.model.ComboBox;


/**
 * @category JSON和JAVA的POJO的相互转换
 * @author zhangdai JSONHelper.java
 */
public final class JsonUtils {

	/**
	 * @category 将数组转换成JSON
	 * @param object
	 * @return
	 */
	public static String array2json(Object object) {

		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/**
	 * @category 将JSON转换成数组,其中valueClz为数组中存放的对象的Class
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object json2Array(String json, Class valueClz) {

		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toArray(jsonArray, valueClz);
	}

	//
	/**
	 * @category 将Collection转换成JSON
	 * @param object
	 * @return
	 */
	public static String collection2json(Object object) {

		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	/**
	 * @category 将Map转换成JSON
	 * @return
	 */
	public static String map2json(Object object) {

		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * @category 将JSON转换成Map,其中valueClz为Map中value的Class,keyArray为Map的key
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map json2Map(Object[] keyArray, String json, Class valueClz) {

		JSONObject jsonObject = JSONObject.fromObject(json);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClz);
		}

		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
	}

	/**
	 * @category 将POJO转换成JSON
	 * @return
	 */
	public static String bean2json(Object object) {

		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * @category 将JSON转换成POJO,其中beanClz为POJO的Class
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object json2Object(String json, Class beanClz) {

		return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
	}

	/**
	 * @category json转换为java对象
	 * 
	 * @param <T>
	 *            要转换的对象
	 * @param json
	 *            字符串
	 * @param valueType
	 *            对象的class
	 * @return 返回对象
	 */
	public static <T> T fromJsonToObject(String json, Class<T> valueType) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			Log4jUtil.error(e.getStackTrace().toString());
		} catch (JsonMappingException e) {
			Log4jUtil.error(e.getStackTrace().toString());
		} catch (IOException e) {
			Log4jUtil.error(e.getStackTrace().toString());
		}
		return null;
	}

	//
	/**
	 * @category 将String健值对 转换成JSON
	 * @return
	 */
	public static String string2json(String key, String value) {

		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}

	/**
	 * @category 将JSON转换成String
	 * @return
	 */
	public static String json2String(String json, String key) {

		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key).toString();
	}

	/***
	 * @category 将List对象序列化为JSON文本
	 */

	public static <T> String toJSONString(List<T> list) {

		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray.toString();
	}

	/***
	 * @category 将对象序列化为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {

		JSONArray jsonArray = JSONArray.fromObject(object);

		return jsonArray.toString();
	}

	/***
	 * @category 将对象转换为List对象,例如Map对象转换
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toArrayList(Object object) {

		List arrayList = new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(object);

		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();

			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}

		return arrayList;
	}

	/***
	 * @category 将对象转换为JSON对象
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {

		return JSONObject.fromObject(object);
	}

	/***
	 * @category 将对象转换为HashMap
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap toHashMap(Object object) {

		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = JsonUtils.toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}

		return data;
	}

	/***
	 * @category 将对象转换为List<Map<String,Object>>
	 * @param object
	 * @return
	 */
	// 返回非实体类型(Map<String,Object>)的List
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> toList(Object object) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}

	/***
	 * @category 将对象转换为传入类型的List
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> toList(Object object, Class<T> objectClass) {

		JSONArray jsonArray = JSONArray.fromObject(object);

		return JSONArray.toList(jsonArray, objectClass);
	}

	/**
	 * 将一个json字串转为list
	 * 
	 * @param props
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> strToList(String str, Class<T> mainClass) {

		if (str == null || str.equals(""))
			return new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject(str);
		List<T> list = (List) JSONArray.toCollection(jsonArray, mainClass);
		return list;
	}

	/***
	 * @category 将JSON对象转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {

		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***
	 * @category 将将对象转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param object
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(Object object, Class<T> beanClass) {

		JSONObject jsonObject = JSONObject.fromObject(object);

		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***
	 * @category 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>
	 *            泛型T 代表主实体类型
	 * @param <D>
	 *            泛型D 代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName
	 *            从实体类在主实体类中的属性名称
	 * @param detailClass
	 *            从实体类型
	 * @return
	 */
	public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

		T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
		List<D> detailList = JsonUtils.toList(jsonArray, detailClass);

		try {
			BeanUtils.setProperty(mainEntity, detailName, detailList);
		} catch (Exception ex) {
			Log4jUtil.error("", ex);
			throw new RuntimeException("主从关系JSON反序列化实体失败！");

		}

		return mainEntity;
	}

	/***
	 * @category 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>泛型T 代表主实体类型
	 * @param <D1>泛型D1 代表从实体类型
	 * @param <D2>泛型D2 代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName1
	 *            从实体类在主实体类中的属性
	 * @param detailClass1
	 *            从实体类型
	 * @param detailName2
	 *            从实体类在主实体类中的属性
	 * @param detailClass2
	 *            从实体类型
	 * @return
	 */
	public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

		T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
		List<D1> detailList1 = JsonUtils.toList(jsonArray1, detailClass1);
		List<D2> detailList2 = JsonUtils.toList(jsonArray2, detailClass2);

		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
		} catch (Exception ex) {
			Log4jUtil.error("", ex);
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}

		return mainEntity;
	}

	/***
	 * @category 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>泛型T 代表主实体类型
	 * @param <D1>泛型D1 代表从实体类型
	 * @param <D2>泛型D2 代表从实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailName1
	 *            从实体类在主实体类中的属性
	 * @param detailClass1
	 *            从实体类型
	 * @param detailName2
	 *            从实体类在主实体类中的属性
	 * @param detailClass2
	 *            从实体类型
	 * @param detailName3
	 *            从实体类在主实体类中的属性
	 * @param detailClass3
	 *            从实体类型
	 * @return
	 */
	public static <T, D1, D2, D3> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2, String detailName3, Class<D3> detailClass3) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
		JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

		T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
		List<D1> detailList1 = JsonUtils.toList(jsonArray1, detailClass1);
		List<D2> detailList2 = JsonUtils.toList(jsonArray2, detailClass2);
		List<D3> detailList3 = JsonUtils.toList(jsonArray3, detailClass3);

		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
			BeanUtils.setProperty(mainEntity, detailName3, detailList3);
		} catch (Exception ex) {
			Log4jUtil.error("", ex);
			throw new RuntimeException("主从关系JSON反序列化实体失败！");
		}

		return mainEntity;
	}

	/***
	 * @category 将JSON文本反序列化为主从关系的实体
	 * 
	 * @param <T>
	 *            主实体类型
	 * @param jsonString
	 *            JSON文本
	 * @param mainClass
	 *            主实体类型
	 * @param detailClass
	 *            存放了多个从实体在主实体中属性名称和类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(String jsonString, Class<T> mainClass, HashMap<String, Class> detailClass) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T mainEntity = JsonUtils.toBean(jsonObject, mainClass);
		for (Object key : detailClass.keySet()) {
			try {
				Class value = detailClass.get(key);
				BeanUtils.setProperty(mainEntity, key.toString(), value);
			} catch (Exception ex) {
				Log4jUtil.error("", ex);
				throw new RuntimeException("主从关系JSON反序列化实体失败！");
			}
		}
		return mainEntity;
	}

	/**
	 * @category 传入任意一个对象生成一个指定规格的字符串
	 * @param object
	 *            任意对象，如果是基础类型对象Float Integer等，前后需要加斜线
	 * @return String
	 */
	public static String objectToJson(Object obj) {

		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(stringToJson(obj.toString())).append("\"");
		}
		return json.toString();
	}

	/**
	 * @category 传入任意一个字符串生成一个指定规格的字符串
	 * @param s
	 *            任意字符串。如果字符含有特殊字符需要特殊处理
	 * @return String
	 */
	public static String stringToJson(String s) {

		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @category 传入任意一个 Javabean对象生成一个指定规格的Json字符串
	 * @param bean
	 *            bean对象
	 * @return String "{}"
	 */
	public static String beanToJson(Object bean) {

		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (Exception e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = objectToJson(props[i].getName());
					String value = objectToJson(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
					Log4jUtil.error("", e);
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @category 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 * 
	 * @param list
	 *            列表对象
	 * @return String "[{},{}]"
	 */
	public static String listToJson(List<?> list) {

		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(beanToJson(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @category 手工拼接JSON
	 */
	@SuppressWarnings("rawtypes")
	public static String getComboBoxJson(List<HashMap> list, List<HashMap> roles) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (HashMap<?, ?> node : list) {
			if (roles.size() > 0) {
				buffer.append("{\"id\":" + node.get("id") + ",\"text\":\"" + node.get("name") + "\"");
				for (HashMap<?, ?> node1 : roles) {
					if (node.get("id") == node1.get("id")) {
						buffer.append(",\"selected\":true");
					}
				}
				buffer.append("},");
			} else {
				buffer.append("{\"id\":" + node.get("id") + ",\"text\":\"" + node.get("name") + "\"},");
			}

		}
		buffer.append("]");

		// 将,\n]替换成\n]

		String tmp = buffer.toString();
		tmp = tmp.replaceAll(",]", "]");
		return tmp;

	}

	/**
	 * @category 根据模型生成JSON
	 */
	@SuppressWarnings("rawtypes")
	public static List<ComboBox> getComboBox(List<HashMap> list, List<HashMap> roles) {

		StringBuffer buffer = new StringBuffer();
		List<ComboBox> comboxBoxs = new ArrayList<ComboBox>();
		buffer.append("[");
		for (HashMap<?, ?> node : list) {
			ComboBox box = new ComboBox();
			box.setId(node.get("id").toString());
			box.setText(node.get("name").toString());
			if (roles.size() > 0) {
				for (HashMap<?, ?> node1 : roles) {
					if (node.get("id") == node1.get("id")) {
						box.setSelected(true);
					}
				}
			}
			comboxBoxs.add(box);
		}
		return comboxBoxs;

	}
}
