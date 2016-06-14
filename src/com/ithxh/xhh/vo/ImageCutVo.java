package com.ithxh.xhh.vo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.xhh.constant.StaticConst;


/**
 * @category 在线切图
 * @Author 何建辉
 * @date 2015年10月21日 上午8:54:22
 */
public class ImageCutVo {
	
	//用户切图相对于网页图片左上角的坐标 xy轴
	private int x;
	private int y;
	
	//用户切图的宽高
	private int width;
	private int height;
	
	//网页缩小的宽高
	private int scaleWidth;
	private int scaleHeight;
	
	//原文件宽高
	private int srcWidth;
	private int srcHeight;
	
	//处理前的图片路径
	private String beforeImgPath = "";
	//处理后的图片路径，如果为空，则使用 beforeImgPath去掉/temp目录的路径
	private String afterImgPath;
	
	//目标图片的输出宽度
	private int distWidth = 200;
	//目标图片的输出高度
	private int distHeight = 160;
	
	//项目路径
	private String webAppPath;
	
	private boolean isDeleteOldImage = true;
	
	private boolean isHandle = true;
	
	public ImageCutVo(){
		super();
	}
	
	public ImageCutVo(int width, int height, int x, int y,
			String imagePath) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.beforeImgPath = imagePath;
	}
	
	public ImageCutVo(String imagePath, int distWidth, int distHeight) {
		super();
		this.beforeImgPath = imagePath;
		this.distWidth = distWidth;
		this.distHeight = distHeight;
	}

	public ImageCutVo(int width, int height, int x, int y,
			String imagePath, int distWidth, int distHeight) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.beforeImgPath = imagePath;
		this.distWidth = distWidth;
		this.distHeight = distHeight;
	}
	
	
	/**
	 * @category 转换成本地路径
	 * @Description 
	 * @Author 何建辉
	 * @date 2015年10月20日  下午5:51:00
	 * @param path
	 * @return
	 */
	private String tranLocalPath(String path){
		if(File.separator.equals("\\")){
			//window下的路径
			return path.replace("/", "\\");
		}else{
			//ios 下的路径 /
			return path.replace("\\", "/");
		}
	}
	
	/**
	 * @category 转换远程路径
	 * @Description 
	 * @Author 何建辉
	 * @date 2015年10月20日  下午5:51:12
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unused")
	private String tranInternetPath(String path){
		return path.replace("\\", "/");
	}
	
	/**
	 * @category 切图 ①没有切图（新上传文件，原文件）②切图（新上传文件，原文件）
	 * @Description 
	 * @Author 何建辉
	 * @date 2015年10月20日  下午5:53:40
	 * @return
	 */
	public boolean cut(){
		
		try {
			//构建正确的路径
			String oldImgPath = "";
			if(beforeImgPath.indexOf(webAppPath)==-1){
				oldImgPath = webAppPath + beforeImgPath;
			}else {
				oldImgPath = beforeImgPath;
			}
			File file = new File(tranLocalPath(oldImgPath));
			//如果本地没有，则把图片从hdfs下载到本地
			file = new File(tranLocalPath(oldImgPath));
			//初始化原始图片宽高
			BufferedImage bufferedImage = ImageIO.read(file);   
			srcWidth = bufferedImage.getWidth();   
			srcHeight = bufferedImage.getHeight();
			//如果用户没有切图，则不用切图
			if(0==width && 0==height && x==0 && y==0){
				//删除下载下来的文件
				file.delete();
				//新上传的文件
				if(beforeImgPath.indexOf("/temp") > -1){
					//把文件从临时目录转到目标目录
//					ImgUtils.moveFile(webAppPath, beforeImgPath, getDistPath(afterImgPath));
				}
				
				return true;
			}
			
			//如果有切图
			//判断参数是否正确，纠正
			if((width+x)>srcWidth || (height+y)>srcHeight){
				//图片参数需要缩小
				width = (int)((double)srcWidth/scaleWidth*width);
				height = (int)((double)srcHeight/scaleHeight*height);
				x = (int)((double)srcHeight/scaleHeight*x);
				y = (int)((double)srcHeight/scaleHeight*y);
			}
			
			//获取文件路径  
			String path = beforeImgPath.substring(0,beforeImgPath.lastIndexOf("/")+1);
			//获取文件后缀
			String subfix = beforeImgPath.substring(beforeImgPath.lastIndexOf("."));
			
			String newImagePath = path+IDGenerator.uuidGenerate()+subfix;
		
			//切图并保存到本地处理后图片位置 newImagePath
			Thumbnails.of(file)
			.sourceRegion(x, y, width, height)
			.size(distWidth,distHeight)
			.keepAspectRatio(false)
			.toFile(tranLocalPath(webAppPath+newImagePath));
			
			//成功后，删除下载到本地的旧文件
			file.delete();
			
			//把处理后的图片上传到hdfs服务器，(把新图片覆盖原来的图片)
//			HdfsUtils.uploadFile(tranLocalPath(webAppPath+newImagePath), tranInternetPath(getDistPath(afterImgPath)));
			/*if(isDeleteOldImage){
				HdfsUtils.deleteFile(tranInternetPath(beforeImgPath));
			}*/
			//删除处理后的本地文件
			file = new File(tranLocalPath(webAppPath+newImagePath));
			if(file.exists())
				file.delete();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String changeTempDir(String path){
		if (!StringUtil.isNullAndBlank(path) && path.indexOf(StaticConst.UPLOAD_TEMP_DIR) > -1) {
			return path.replace(StaticConst.UPLOAD_TEMP_DIR, "");
		}
		return path;
	}
	
	public String getDistPath(String path){
		if(path==null || path.trim().equals("")){
			path = changeTempDir(beforeImgPath);
		}
		return path;
	}
	
	
	public boolean isHandle() {
		return isHandle;
	}

	public void setHandle(boolean isHandle) {
		this.isHandle = isHandle;
	}

	public int getSrcWidth() {
		return srcWidth;
	}

	public void setSrcWidth(int srcWidth) {
		this.srcWidth = srcWidth;
	}

	public int getSrcHeight() {
		return srcHeight;
	}

	public void setSrcHeight(int srcHeight) {
		this.srcHeight = srcHeight;
	}

	public int getScaleWidth() {
		return scaleWidth;
	}

	public void setScaleWidth(int scaleWidth) {
		this.scaleWidth = scaleWidth;
	}

	public int getScaleHeight() {
		return scaleHeight;
	}

	public void setScaleHeight(int scaleHeight) {
		this.scaleHeight = scaleHeight;
	}

	public String getBeforeImgPath() {
		return beforeImgPath;
	}

	public void setBeforeImgPath(String beforeImgPath) {
		this.beforeImgPath = beforeImgPath;
	}

	public boolean isDeleteOldImage() {
		return isDeleteOldImage;
	}

	public void setDeleteOldImage(boolean isDeleteOldImage) {
		this.isDeleteOldImage = isDeleteOldImage;
	}

	public String getAfterImgPath() {
		return afterImgPath;
	}

	public void setAfterImgPath(String afterImgPath) {
		this.afterImgPath = afterImgPath;
	}

	public String getWebAppPath() {
		return webAppPath;
	}

	public void setWebAppPath(String webAppPath) {
			this.webAppPath = webAppPath;
	}

	public int getDistWidth() {
		return distWidth;
	}

	public void setDistWidth(int distWidth) {
		this.distWidth = distWidth;
	}

	public int getDistHeight() {
		return distHeight;
	}

	public void setDistHeight(int distHeight) {
		this.distHeight = distHeight;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
