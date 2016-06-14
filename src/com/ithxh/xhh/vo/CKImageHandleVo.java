package com.ithxh.xhh.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @category 处理富文本中带有图片的处理类   可以参考QuesBankManagerCtrl的doSave方法
 * @author 何建辉
 * @date 2015年10月30日 下午2:09:34
 */
public class CKImageHandleVo {

	// 处理前的内容列表 
	private String[] beforeContents;
	// 处理后的内容列表
	private String[] afterContents;
	// 内容中所有的图片列表，没有修改过的
	private List<String> originalPicList = new ArrayList<String>();
	// 内容中包含的新图片列表
	private List<String> newPicList = new ArrayList<String>();
	// 内容中包含的旧图片列表
	private List<String> oldPicList = new ArrayList<String>();
	// 需要返回的图片列表
	private List<String> needPicList = new ArrayList<String>();
	
	private String realPath;

	private String picTemp = "/pictemp";

	private String picReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
	//内容是否有图片
	private boolean isHavePic = false;

	/**
	 * @category 处理内容中的图片
	 * @Description
	 * @author 何建辉
	 * @date 2015年10月30日 上午10:08:14
	 * @param cons
	 * @return
	 */
	public CKImageHandleVo handleContent(){
		Pattern p = Pattern.compile(picReg);
		
		for(int i=0;i<beforeContents.length;i++){
			Matcher m = p.matcher(beforeContents[i]);
			while (m.find()) {
				String picPath = m.group(1);
				
				if(picPath.indexOf("http://") < 0 && picPath.indexOf("https://") < 0 && picPath.trim().length()>0){
					afterContents[i] = afterContents[i].replace(picPath, picPath.replace(picTemp, ""));
					isHavePic = true;
					originalPicList.add(picPath);
					if(picPath.indexOf(picTemp) > -1){
						newPicList.add(picPath);
					}else {
						oldPicList.add(picPath);
					}
				}
			}
		}
		
		return this;
	}
	
	/**
	 * @category 查找newImgList中不含oldImgList的图片列表 
	 * @Description 
	 * @author 何建辉
	 * @date 2015年10月30日  上午10:39:44
	 * @param oldImgList
	 * @param newImgList
	 * @return
	 */
	public static List<String> getNewDiffPic(List<String> oldImgList,List<String> newImgList){
		List<String> needList = new ArrayList<String>();
		
		if(newImgList==null || newImgList.size()==0){
			return oldImgList;
		}
		
		if(oldImgList==null || oldImgList.size()==0){
			return needList;
		}
		
		for(String o:oldImgList){
			if(!newImgList.contains(o)){
				needList.add(o);
			}
		}
		
		return needList;
	}
	
	
	/**
	 * @category 移动
	 * @Description 
	 * @author 何建辉
	 * @date 2015年10月30日  上午10:59:44
	 * @return
	 */
	public boolean copyNewPic(){
		//移动成功的图片列表
		/*List<String> successList = new ArrayList<String>();
		
		if(newPicList==null || newPicList.size()<1){
			return true;
		}*/
		
		/*for(String pic:newPicList){
			try {
				ImgUtils.copyFile(realPath, pic, pic.replace(picTemp, ""));
				successList.add(pic);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				//移动失败，需要把刚上传到服务器的图片删除
				for(String sic:successList){
					boolean b = ImgUtils.delFile(sic.replace(picTemp, ""), realPath);
					if(!b){
						//TODO 删除失败，需要添加到图片日志中
					}
				}
				return false;
			}
		}*/
		
		return true;
	}
	
	/**
	 * @category 转成正式图片路径
	 * @Description 
	 * @author 何建辉
	 * @date 2015年10月30日  上午11:05:17
	 * @param list
	 * @return
	 */
	public List<String> delPicTemp(List<String> list){
		if(list==null || list.size()<1){
			return list;
		}
		for(String picPath:list){
			picPath.replace(picTemp, picPath);
		}
		return list;
	}
	
	/**
	 * @category 删除图片
	 * @Description 
	 * @author 何建辉
	 * @date 2015年10月29日  下午5:47:54
	 * @param list
	 * @param isDelTemp 是否去掉/pictemp目录
	 * @param realPath
	 */
	public void deleteImg(List<String> list,boolean isDelTemp){
		/*if(list!=null)
		for(String p : list){
			if(isDelTemp){
				p = p.replace(picTemp, "");
			}
			boolean delFile = ImgUtils.delFile(p, realPath);
			if(!delFile){
				//TODO 添加到图片日志中
			}
		}*/
	}
	
	public boolean isHavePic() {
		return isHavePic;
	}

	public String getPicTemp() {
		return picTemp;
	}

	public CKImageHandleVo() {
		super();
	}
	
	public CKImageHandleVo(String[] beforeContents,
			String realPath) {
		super();
		this.beforeContents = beforeContents;
		this.afterContents = beforeContents;
		this.realPath = realPath;
	}
	public void setPicTemp(String picTemp) {
		this.picTemp = picTemp;
	}

	public String getPicReg() {
		return picReg;
	}

	public void setPicReg(String picReg) {
		this.picReg = picReg;
	}

	public String[] getBeforeContents() {
		return beforeContents;
	}

	public void setBeforeContents(String[] beforeContents) {
		this.beforeContents = beforeContents;
	}

	public String[] getAfterContents() {
		return afterContents;
	}

	public List<String> getOriginalPicList() {
		return originalPicList;
	}

	public List<String> getNewPicList() {
		return newPicList;
	}

	public List<String> getOldPicList() {
		return oldPicList;
	}

	public List<String> getNeedPicList() {
		return needPicList;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
}
