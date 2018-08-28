package com.xcx.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;

import com.xcx.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	public static String generateThumbnail(ImageHolder thumbnail,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirpath(targetAddr);
		String relativeAddr = targetAddr +realFileName +extension;
		File dest = new File(PathUtil.getImgbasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+ "/watermark.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
			
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return relativeAddr;
	}
	private static void makeDirpath(String targetAddr) {
		// TODO Auto-generated method stub
		String  realFileParentPath = PathUtil.getImgbasePath()+targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	private static String getFileExtension(String fileName) {
		// TODO Auto-generated method stub
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	public static String getRandomFileName() {
		// 获取随机得五位数
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sDateFormat.format(new Date());
		
		return nowTimeStr+rannum;
	}
	public static void main(String[] args) {
		
	}
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgbasePath()+storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// TODO Auto-generated method stub
		//获取随机名
		String realFileName = getRandomFileName();
		//获取扩展名
		String extension = getFileExtension(thumbnail.getImageName());
		//如果不存在自动创建
		makeDirpath(targetAddr);
		//获取文件存储的相对路径(带文件名)
		String relativeAddr = targetAddr +realFileName +extension;
		//获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgbasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+ "/watermark.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
			
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new RuntimeException("创建详情图失败"+extension.toString());
		}
		return relativeAddr;
	
	}
}
