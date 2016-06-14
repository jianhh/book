package com.ithxh.baseCore.baseUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadUtil {

	/**
	 * 下载文件的方法
	 * 
	 * @param file_path
	 *            文件绝对路径
	 * @param file_name
	 *            文件名称
	 * @param request
	 * @param response
	 * @param backurl
	 *            返回地址
	 * @param file_size
	 *            文件大小
	 * @return
	 */
	public static boolean downloadFile(String file_path, String file_name, HttpServletRequest request, HttpServletResponse response)

	{

		boolean foundFile = false;
		File fileLoad = new File(file_path); // 服务器文件地址
		long file_size_int = fileLoad.length();
		PrintWriter pout = null;
		if (fileLoad.exists()) {
			foundFile = true;
		}

		if (foundFile) {
			String filenamedisplay = "";
			try {
				filenamedisplay = URLEncoder.encode(file_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				Log4jUtil.error("进行字符集转换时抛出异常", e);

			}

			// 点击会提供对话框选择另存为：
			// response.setHeader( “Content-Disposition “, “filename= “+filename);
			//
			// 通过IE浏览器直接选择相关应用程序插件打开：
			// response.setHeader( “Content-Disposition “, “inline;filename= “+fliename)
			//
			// 下载前询问（是打开文件还是保存到计算机）
			// response.setHeader( “Content-Disposition “, “filename= “+filename);

			response.setHeader("Content-disposition", "attachment;filename=" + filenamedisplay);

			response.setHeader("Content-Length", String.valueOf(file_size_int));
			BufferedInputStream bufferedInputStream = null;
			OutputStream outputStream = null;
			try {
				outputStream = response.getOutputStream();
				bufferedInputStream = new BufferedInputStream(new FileInputStream(file_path));
				int bytesRead = 0;
				final int length = 99999999;
				byte[] buffer = new byte[length];
				while ((bytesRead = bufferedInputStream.read(buffer, 0, length)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				return true;
			} catch (Exception e) {
				Log4jUtil.error("取消也会抛出异常", e);

				return false;
			} finally {
				if (bufferedInputStream != null) {
					try {
						bufferedInputStream.close();
					} catch (IOException ioe) {
						Log4jUtil.error("关闭转入流FileinputStream时抛出异常:", ioe);

					}
				}

				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException ioe) {
						Log4jUtil.error("关闭转出流OutputStream时抛出异常:", ioe);

					}
				}
			}
		} else {
			// Log4jUtil.error("关闭转出流OutputStream时抛出异常:", ioe);
			System.out.println("文件下载路径地址错误!");
			response.setContentType("text/html; charset=utf-8");
			try {
				pout = response.getWriter();
				pout.print("<script language=\"javascript\">alert(\"文件下载路径地址错误\");history.back(-1);</script>");
			} catch (IOException e) {
				Log4jUtil.error("IO输出异常", e);

			} finally {
				pout.close();
			}
			return false;
		}
	}

	public static String round(String num_str, int scale, int round_mode) {

		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(num_str);
		return b.setScale(scale, round_mode).toString();
	}
}
