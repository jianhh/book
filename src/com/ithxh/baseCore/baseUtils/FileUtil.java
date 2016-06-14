package com.ithxh.baseCore.baseUtils;

 

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
   
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }
   
   
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
   
   
    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try{
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建临时文件失败！" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!FileUtil.createDir(dirName)) {
                    System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }
   
    
    /**
     * @category 获取文件的大小
     * @param realPath
     * @return  单位 M
     */
    public static double getFileSize(String realPath)
    {
        File file= new File(realPath);  
        double fa = file.length()/(double)(1024*1024);
        return fa;
    }
    
	/**
	 * @category 验证文件格式
	 * @author xsh
	 * @param file
	 * @return   0 文档   1视频  2图片   3flash  4表示未知类型
	 */
	public static int validateFile(MultipartFile file) {

        // 根据类型识别是 什么类型
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
		
		// 图片类型
		 int  r=0;
		 if(extName.equals("pdf")  ||extName.equalsIgnoreCase("doc") || extName.equals("xls")|| extName.equals("docx") || extName.equals("xlsx") || extName.equals("ppt") || extName.equals("pptx") || extName.equals("txt"))
		{
			 			 
		    r= 0;
		// 视频文件支持的类型：  asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv
		}else if(extName.equals("asx")  ||extName.equalsIgnoreCase("asf") || extName.equals("mpg")|| extName.equals("wmv")
				|| extName.equals("3gp") || extName.equals("mp4") || extName.equals("mov")|| extName.equals("avi")|| extName.equals("flv"))
		{
			 r= 1;
		// flash 格式
		}else if(extName.equals("jpg") || extName.equals("png")   || extName.equals("jpeg") )
		{
			 r= 2;
		// 文档类型	
		}else if(extName.equals("swf")){
			 r= 3;
		}else
		{
			 r= 4;
		}
        return r;		 
	}

    
    
    public static void main(String[] args) {
//        //创建目录
//        String dirName = "D:/work/temp/temp0/temp1";
//        CreateFileUtil.createDir(dirName);
//        //创建文件
//        String fileName = dirName + "/temp2/tempFile.txt";
//        CreateFileUtil.createFile(fileName);
//        //创建临时文件
//        String prefix = "temp";
//        String suffix = ".txt";
//        for (int i = 0; i < 10; i++) {
//            System.out.println("创建了临时文件："
//                    + CreateFileUtil.createTempFile(prefix, suffix, dirName));
//        }
//        //在默认目录下创建临时文件
//        for (int i = 0; i < 10; i++) {
//            System.out.println("在默认目录下创建了临时文件："
//                    + CreateFileUtil.createTempFile(prefix, suffix, null));
//        }
    	

    	
    }

}
 

