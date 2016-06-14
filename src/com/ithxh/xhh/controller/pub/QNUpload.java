package com.ithxh.xhh.controller.pub;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QNUpload {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private String ACCESS_KEY = "9qX1KZ3TGwmTn8MRH3VW9EnCxlAMhTQmLsWWzjNY";
	private String SECRET_KEY = "dusTmT5BHbHSDutTrwdxLHx67sHlLOhdlogRd5dk";
	// 要上传的空间
	private String bucketname = "rldhxh";
	// 上传到七牛后保存的文件名
	private String key = "mybook1.png";
	// 上传文件的路径
	@SuppressWarnings("unused")
	private String originpath = "F:\\eclipse\\Workspaces\\MyEclipse 2015 c2\\.metadata\\.me_tcat7\\webapps\\ROOT\\xhh\\book\\official\\net\\jingdong\\2016\\03\\23\\8a4fc9dc53a302000153a30f60af00d3.jpg";

	private byte[] bytes;
	// 密钥配置
	private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	private String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public QNUpload() {
	}

	public QNUpload(String originpath) {
		this.originpath = originpath;
	}
	
	public QNUpload(String key,byte[] bytes) {
		this.key = key;
		this.bytes = bytes;
	}

	public String upload() throws IOException {
		try {
			// 创建上传对象
			UploadManager uploadManager = new UploadManager();
			// 调用put方法上传
			Response res = uploadManager.put(bytes, key, getUpToken());
//			Response res = uploadManager.put(originpath, null, getUpToken());
			// 打印返回的信息
			JSONObject json = JSONObject.fromObject(res.bodyString());
			String url = (String) json.get("key");
			return url;
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}

		return null;
	}

	public static void main(String args[]) throws IOException {
		new QNUpload().upload();
	}

}