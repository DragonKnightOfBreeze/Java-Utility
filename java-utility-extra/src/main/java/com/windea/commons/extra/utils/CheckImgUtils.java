package com.windea.commons.extra.utils;

import com.windea.commons.base.utils.RandomUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

/**
 * 验证图片的工具类
 * @noinspection unused, WeakerAccess
 */
public class CheckImgUtils {
	private static int width = 60;
	private static int height = 20;

	private CheckImgUtils() {}

	/**
	 * 得到一张默认的字符验证码图片。
	 */
	@NotNull
	public static BufferedImage getCheckImg(@NotNull char[] checkCode) {
		Color fontColor = new Color(0x1932C0);
		Font font = new Font("宋体", Font.ITALIC | Font.BOLD, 18);
		Color bgColor = Color.lightGray;
		return getCheckImg(checkCode, fontColor, font, bgColor);
	}

	/**
	 * 得到一张字符验证码图片。
	 */
	@NotNull
	public static BufferedImage getCheckImg(@NotNull char[] checkCode, @NotNull Color fontColor, @NotNull Font font,
		@NotNull Color bgColor) {
		width = 15 * checkCode.length;
		//创建内存图象并获得其图形上下文
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//绘制一张字符验证码图片
		Graphics2D graphics2D = image.createGraphics();
		drawBackground(graphics2D, bgColor);
		drawCheckCode(graphics2D, checkCode, fontColor, font);
		//结束图像的绘制过程，完成图像
		graphics2D.dispose();
		return image;
	}


	/**
	 * 生成指定位数的随机字符验证码。
	 * @param length 随机字符验证码的长度
	 */
	@NotNull
	public static char[] getCheckCode(int length) {
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
		return getCheckCode(length, chars);
	}

	/**
	 * 生成指定位数的随机字符验证码。
	 * @param length 随机字符验证码的长度
	 * @param chars 字符范围
	 */
	@NotNull
	public static char[] getCheckCode(int length, @NotNull char[] chars) {
		char[] checkCode = new char[length];
		IntStream.range(0, length).forEach(i -> {
			int rand = RandomUtils.range(chars.length);
			checkCode[i] = chars[rand];
		});

		return checkCode;
	}

	/**
	 * 将随机字符验证码画到图片上。
	 * @param graphics2D 图片
	 * @param checkCode 随机字符验证码
	 * @param fontColor 字符验证码的颜色
	 * @param font 字符验证码的样式
	 */
	private static void drawCheckCode(@NotNull Graphics2D graphics2D, @NotNull char[] checkCode, Color fontColor,
		Font font) {
		//设置字符验证码的颜色和样式
		graphics2D.setColor(fontColor);
		graphics2D.setFont(font);
		//在不同的高度上输出验证码的每个字符
		for(int i = 0; i < checkCode.length; i++) {
			float theta = RandomUtils.range(-30, 30) * 3.14f / 180;
			int x = 15 * i + 1;
			int y = RandomUtils.range(15, 20);
			graphics2D.rotate(theta, x, y);
			graphics2D.drawString("" + checkCode[i], x, y);
			graphics2D.rotate(-theta, x, y);
		}
	}

	/**
	 * 为图片绘制干扰背景。
	 * @param graphics 图片
	 * @param bgColor 背景颜色
	 */
	private static void drawBackground(@NotNull Graphics graphics, @NotNull Color bgColor) {
		//绘制背景
		graphics.setColor(bgColor);
		graphics.fillRect(0, 0, width, height);
		//随机产生干扰点
		IntStream.range(0, width * 4).forEach(i -> {
			int x = RandomUtils.range(width);
			int y = RandomUtils.range(height);
			int red = RandomUtils.range(255);
			int green = RandomUtils.range(255);
			int blue = RandomUtils.range(255);
			graphics.setColor(new Color(red, green, blue));
			graphics.drawOval(x, y, 1, 0);
		});
	}
}


