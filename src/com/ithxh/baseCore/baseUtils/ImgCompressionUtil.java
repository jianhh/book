package com.ithxh.baseCore.baseUtils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/***
 * @category 缩放图片
 * @Company 中山集智网络科技有限公司
 * @author 梁永明
 * @Copyright Copyright(c) 2014
 * @version V1.0
 * @date 2014-06-26 上午10:35:55
 * 
 */
public class ImgCompressionUtil {

	/**
	 * * 图片文件读取
	 * 
	 * @param srcImgPath
	 * @return
	 * */
	private static BufferedImage InputImage(String srcImgPath) {

		BufferedImage srcImage = null;
		try {
			FileInputStream in = new FileInputStream(srcImgPath);
			srcImage = javax.imageio.ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("读取图片文件出错！" + e.getMessage());
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
		return srcImage;
	}

	/**
	 * @category 将图片按照指定的图片尺寸压缩
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param new_w
	 *            :压缩后的图片宽
	 * @param new_h
	 *            :压缩后的图片高
	 */
	public static void compressImage(String srcImgPath, String outImgPath, int new_w, int new_h) {

		BufferedImage src = InputImage(srcImgPath);
		disposeImage(src, outImgPath, new_w, new_h);
	}

	/**
	 * 指定长或者宽的最大值来压缩图片
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param maxLength
	 *            :将源图片的长或者宽的最大值，改成这个值，并且等比缩小。
	 */
	public static void compressImage(String srcImgPath, String outImgPath, int maxLength) {

		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		if (null != src) {
			int old_w = src.getWidth();
			// 得到源图宽
			int old_h = src.getHeight();
			// 得到源图长
			int new_w = 0;
			// 新图的宽
			int new_h = 0;
			// 新图的长
			// 根据图片尺寸压缩比得到新图的尺寸
			if (old_w > old_h) {
				// 图片要缩放的比例
				new_w = maxLength;
				new_h = Math.round(old_h * ((float) maxLength / old_w));
			} else {
				new_w = Math.round(old_w * ((float) maxLength / old_h));
				new_h = maxLength;
			}
			disposeImage(src, outImgPath, new_w, new_h);
		}
	}

	/**
	 * @category 压缩并生成处理图片 图片会变形
	 * @param src
	 *            源图片
	 * @param outImgPath
	 *            目标路径
	 * @param new_w
	 *            目标的宽
	 * @param new_h
	 *            目标的高
	 * */
	private synchronized static void disposeImage(BufferedImage src, String outImgPath, int new_w, int new_h) {

		// 得到图片
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		BufferedImage newImg = null;
		// 判断输入图片的类型
		switch (src.getType()) {
		case 13:
			// png,gifnewImg = new BufferedImage(new_w, new_h,
			// BufferedImage.TYPE_4BYTE_ABGR);
			break;
		default:
			newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			break;
		}
		Graphics2D g = newImg.createGraphics();
		// 从原图上取颜色绘制新图
		g.drawImage(src, 0, 0, old_w, old_h, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件
		OutImage(outImgPath, newImg);
	}

	/**
	 * @category 将图片文件输出到指定的路径
	 * @param outImgPath
	 *            指定的图片路径
	 * @param newImg
	 *            新生成的内存图片
	 */
	private static void OutImage(String outImgPath, BufferedImage newImg) {

		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}// 输出到文件流
		try {
			ImageIO.write(newImg, outImgPath.substring(outImgPath.lastIndexOf(".") + 1), new File(outImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		} catch (IOException e) {
			e.printStackTrace();
			Log4jUtil.error("", e);
		}
	}

	public static void main(String[] args) {

		// 测试
		ImgCompressionUtil.compressImage("c:\\107116955.jpg", "c:\\1234.jpg", 100, 100);
		System.out.println("-------");

	}

}
