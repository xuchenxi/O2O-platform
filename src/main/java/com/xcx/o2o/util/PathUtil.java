package com.xcx.o2o.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");
	public static String getImgbasePath() {
		String os = System.getProperty("os.name");
		String basePath ="";
		if (os.toLowerCase().startsWith("win")) {
			basePath="D:/o2oimage/";
		}else {
			basePath="/home/xu/o2oimage/";
		}
		basePath=basePath.replace("/", seperator);
		return basePath;
	}
	public static String getShopImagePath(long shopId) {
		String imagePath = "upload/item/shop/" +shopId +"/";
		return imagePath.replace("/", seperator);
	}
}
