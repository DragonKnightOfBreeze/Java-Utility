package com.windea.java.utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * 路径的工具类。
 */
public class PathUtils {
	private PathUtils() {
	}


	/**
	 * 组成完整路径，允许混合两种分隔符。
	 */
	@NotNull
	public static String join(@NotNull String... path) {
		return (String.join("\\", path).replace("/", "\\"));
	}

	/**
	 * 得到文件名。
	 */
	@NotNull
	public static String getFileName(@NotNull String filePath) {
		int index = filePath.lastIndexOf("\\");
		if(index == -1)
			return filePath;
		return filePath.substring(index + 1);
	}

	/**
	 * 得到切去扩展名的文件路径。
	 */
	@NotNull
	public static String subFileExt(@NotNull String fileName) {
		int index = fileName.lastIndexOf(".");
		if(index == -1)
			return fileName;
		return fileName.substring(0, index);
	}

	/**
	 * 得到带有"."的文件扩展名。如果没有，则返回空字符串。
	 */
	@NotNull
	public static String getFileExt(@NotNull String filePath) {
		int index = filePath.lastIndexOf(".");
		if(index == -1)
			return "";
		return filePath.substring(index);
	}

	/**
	 * 更改带有"."的文件扩展名。
	 */
	@NotNull
	public static String changeFileExt(@NotNull String filePath, @NotNull String newFileExt) {
		return subFileExt(filePath) + newFileExt;
	}

	/**
	 * 生成随机的文件名（保留拓展名）。
	 */
	@NotNull
	public static String getRandomFileName(@NotNull String fileName) {
		String uuid = UUID.randomUUID().toString();
		//尝试得到扩展名
		int index = fileName.lastIndexOf(".");
		if(index != -1)
			return uuid + fileName.substring(index);
		return uuid;
	}

	/**
	 * 生成随机的文件目录名（一级+二级）。
	 */
	@NotNull
	public static String getRandomDirName(@NotNull String fileName) {
		int hashCode = fileName.hashCode();
		//一级目录
		int d1 = hashCode & 0xf;
		//二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
