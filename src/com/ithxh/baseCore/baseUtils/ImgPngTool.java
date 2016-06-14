package com.ithxh.baseCore.baseUtils;
//package com.tengo.baseCore.baseUtil;
//
//import java.awt.AlphaComposite;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.Transparency;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import com.tengo.baseCore.baseUtil.hdfsUtils.HdfsUtils;
//import com.tengo.edu.tools.SysConfigUtil;
//
// 
//
///***
// * @category 图片工具类用于png
// * @Company
// * @author  xsh
// * @Copyright Copyright(c) 2013
// * @version V1.0
// * @date 2014-12-6 上午10:35:55
// * 
// */
//public class ImgPngTool {
//	
//	
//
//	/*
//	 * 获取配置文件，看是本地的文件系统，还是远程的HDFS文件系统。
//	 */
//	
//	private static boolean getUseHdfs()
//	{
//		boolean useHDFS = SysConfigUtil.getSysConfig().getUseHDFS();
//		return useHDFS ;
//		
//	}
//	
////	/**
////	 * 按最大宽高来缩放图片(图片自适应最大宽高) 图片工具类用于png
////	 * 
////	 * @param bitmap
////	 *            原图
////	 * @param width
////	 *            最大的宽
////	 * @param height
////	 *            最大的高
////	 * @param type
////	 *            图片格式
////	 * @param temp
////	 *            输出的文件
////	 * @return 缩放完后图片的宽和高（int[0]为宽，int[1]为高）
////	 * @throws IOException
////	 */
////	public static int[] zoom(BufferedImage bitmap, int width, int height,
////			String type, File temp) throws IOException {
////		if (bitmap == null) {
////			return null;
////		}
////		if (width < 1 || height < 1) {
////			return null;
////		}
////		Image itemp = null;
////		float oldWidth = bitmap.getWidth();
////		float oldHeight = bitmap.getHeight();
////		double ratio = (height / oldHeight) < (width / oldWidth) ? (height / oldHeight)
////				: (width / oldWidth);// 缩放比例
////		itemp = bitmap.getScaledInstance(width, height,
////				BufferedImage.SCALE_SMOOTH);
////		AffineTransformOp op = new AffineTransformOp(
////				AffineTransform.getScaleInstance(ratio, ratio), null);
////		itemp = op.filter(bitmap, null);
////		ImageIO.write((BufferedImage) itemp, type, temp);
////		int[] wh = { itemp.getWidth(null), itemp.getHeight(null) };
////		return wh;
////	}
//
//	/**
//	 * 绘制缩放图  图片工具类用于png
//	 * 
//	 * @param img
//	 *            原图
//	 * @param width
//	 *            目标图宽
//	 * @param height
//	 *            目标图高
//	 * @return
//	 */
//	private static BufferedImage makeThumbnail(Image img, int width, int height) {
//		BufferedImage tag = new BufferedImage(width, height,
//				BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = tag.createGraphics();
//		g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH),
//				0, 0, null);
//		g.dispose();
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
//		return tag;
//	}
//	
//	/**
//	 * @see 放大或缩小图片  图片工具类用于png
//	 * @param base 根目录
//	 * @param imgPath 图片路径(虚拟路径)
//	 * @param ratio 放大或缩小的倍数(1倍不会改变原图)
//	 * @param response 可以为null
//	 * @return
//	 */
//	public static BufferedImage zoomRatio(BufferedImage img, double ratio) {
//	        int width = new Double(img.getWidth() * ratio).intValue();
//	        int height = new Double(img.getHeight() * ratio).intValue();
//	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//	        Graphics2D graphics  = image.createGraphics();
//	        image = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//	        graphics.dispose();
//	        graphics = image.createGraphics();
//	        graphics.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH),
//					0, 0, null);
//	      
//		return image;
//	}
//
//	/**
//	 * 裁剪图片  图片工具类用于png
//	 *  
//	 * @param image
//	 *            原图
//	 * @param subImageBounds
//	 *            裁剪矩形框
//	 * @param subImageFile
//	 *            保存路径
//	 * @throws IOException
//	 */
//	private static void saveSubImage(BufferedImage image,
//			Rectangle subImageBounds, File subImageFile) throws IOException {
//		String fileName = subImageFile.getName();
//		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
//		if(subImageBounds == null){
//			 ImageIO.write(image, formatName, subImageFile);
//			 return;
//		}
//		BufferedImage subImage = new BufferedImage(subImageBounds.width,
//				subImageBounds.height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = subImage.createGraphics();
// 
//			int left = subImageBounds.x;
//			int top = subImageBounds.y;
//			if (image.getWidth() < subImageBounds.width)
//				left = (int) ((subImageBounds.width - image.getWidth()) / 2);
//			if (image.getHeight() < subImageBounds.height)
//				top = (int) ((subImageBounds.height - image.getHeight()) / 2);
//			/*Color c = new Color(1,1,1,0.0f);
//			g.setColor(c.orange);
//			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);*/
//
//			// ----------  增加下面的代码使得背景透明  -----------------
//			subImage = g.getDeviceConfiguration().createCompatibleImage(subImageBounds.width, subImageBounds.height, Transparency.TRANSLUCENT);
//			g.dispose();
//			g = subImage.createGraphics();
//			
//			g.drawImage(image, left, top, null);
//			ImageIO.write(image, formatName, subImageFile);
//		 
//		g.dispose();
//		ImageIO.write(subImage, formatName, subImageFile);
//	}
//
//	/**
//	 * 图片缩放裁剪并保存到指定文件  图片工具类用于png
//	 * 
//	 * @param srcImageFile
//	 *            原图保存路径
//	 * @param descDir
//	 *            目标图保存路径
//	 * @param width
//	 *            缩放后图片宽度
//	 * @param height
//	 *            缩放后图片高度
//	 * @param rect
//	 *            裁剪矩形框
//	 * @throws IOException
//	 */
//
//	
//	public static void cut(String srcImageFile,String realPath,String picPath, int width , int height )
//	  throws IOException {
//	
//		  Rectangle rec = new Rectangle(0, 0, width, height);
//			double ratio = 1;
//			if(getUseHdfs())
//			{
//				//如果是用HDFS,那么就从远程下载到本地，再在本地执行操作，再上传，再把本地删除。
//				HdfsUtils.downFile(srcImageFile, realPath+srcImageFile);
//			}
//			BufferedImage image = ImageIO.read(getFile(realPath+srcImageFile));
//			
//			double ratio_height =  (double)height/image.getHeight();
//			double ratio_width = (double)width/image.getWidth();
//			 if(ratio_height>ratio_width){
//				 ratio  = ratio_width;
//			 }else{
//				 ratio  = ratio_height;
//			 }
//			 //rec = null;
//			  BufferedImage bImage = zoomRatio(image, ratio);
//			  saveSubImage(bImage, rec, getFile(realPath+picPath));
//			 if(getUseHdfs())
//			 {
//				 HdfsUtils.uploadFile2(realPath+picPath, picPath);
//				 //删除本地的大小图片
//				 File ftemp = new File(realPath+srcImageFile);
//				 ftemp.delete();
//				 File ftemp2 = new File(realPath+picPath);
//				 ftemp2.delete();
//			 }
//				
//	}
//	
//
//
////	/**
////	 * 图片缩放裁剪并保存到指定文件  图片工具类用于png
////	 * 
////	 * @param srcImageFile
////	 *            原图保存路径
////	 * @param descDir
////	 *            目标图保存路径
////	 * @param width
////	 *            缩放后图片宽度
////	 * @param height
////	 *            缩放后图片高度
////	 * @param rect
////	 *            裁剪矩形框
////	 * @throws IOException
////	 */
////	public static void cut(File srcImageFile, File descDir, int width,
////			int height, Rectangle rect) throws IOException {
////		
////		Image image = ImageIO.read(srcImageFile);
////		BufferedImage bImage = makeThumbnail(image, width, height);
////
////		saveSubImage(bImage, rect, descDir);
////	}
////
////	/**
////	 * 裁切文件的指定部分并保存到指定文件
////	 * 
////	 * @param tFile
////	 *            要裁切的文件
////	 * @param sFile
////	 *            裁切后的文件
////	 * @param x
////	 *            裁切的起始X坐标
////	 * @param y
////	 *            裁切的起始Y坐标
////	 * @param w
////	 *            裁切的宽
////	 * @param h
////	 *            裁切的高
////	 * @return 是否成功
////	 * @throws IOException
////	 */
////	public static boolean cutToFile(File tFile, File sFile, int x, int y,
////			int w, int h) throws IOException {
////		BufferedImage tBi = ImageIO.read(tFile);
////		Rectangle rec = new Rectangle(x, y, w, h);
////		saveSubImage(tBi, rec, sFile);
////		return true;
////	}
//
//private static File getFile(String path)
//  {
//	  File file = new File(path);
//	 // File file2 = HdfsUtils.getFileSystem()
//	  return file;
//  }
//	
//	
//	public static void main(String[] args) {
//		
////		try {
////			
////			//cut("c://2.jpeg", "c://4.jpeg",100, 100);
////			//ImgCutUtil.cutHalfImage("c://107116955.jpg", "c://1231.jpg","jpg");
////			//ImgCutUtil.cutImage("c://107116955.jpg", "c://1232.jpg","jpg",10,10,100,100);
////			//ImgCutUtil.zoomImage("c://107116955.jpg", "c://1233.jpg",300,150);
////			
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
//}