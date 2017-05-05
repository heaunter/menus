package com.vota.reservation.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成器.
 * 
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 14-5-10 下午10:04
 */
public class CodeBuilder {
	/**
	 * 验证码在session中默认的ID
	 */
	public static final String VERITY_CODE_KEY = "verityCode";

	private CodeBuilder() {
	}

	/**
	 * 生成验证码图片文件
	 * 
	 * @param length
	 *            验证码数字长度
	 * @param request
	 * @param key
	 *            数字字符串放入session中的key
	 * @return
	 */
	public static BufferedImage buildCode(int length, HttpServletRequest request, String key) {

		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '瓶', '盖' };

		length = (length <= 0 ? 4 : length);
		// 内存中创建图像
		int width = length * 17, height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图像上下文
		Graphics graphics = image.getGraphics();

		// 随机类
		Random random = new Random();

		// 设置背景色
		// graphics.setColor(getRandomColor(200, 250));
		graphics.fillRect(0, 0, width, height);

		// 设置字体
		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		// 随机产生干扰线
		// graphics.setColor(getRandomColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < length; i++) {
			String code = String.valueOf(codeSequence[random.nextInt(34)]);
			sRand += code;
			// 将认证码显示到图象中
			graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			graphics.drawString(code, 15 * i + 5, 20);
		}

		// 图象生效
		graphics.dispose();

		// 将认证码存入SESSION
		// 默认的session key
		if (request != null) {
			key = (StringUtil.isEmpty(key) ? VERITY_CODE_KEY : key);
			request.getSession().setAttribute(key, sRand);
		}

		return image;
	}

	@SuppressWarnings("unused")
	private static Color getRandomColor(int fontColor, int backColor) {
		Random random = new Random();
		if (fontColor > 255)
			fontColor = 255;
		if (backColor > 255)
			backColor = 255;

		int red = fontColor + random.nextInt(backColor - fontColor);
		int green = fontColor + random.nextInt(backColor - fontColor);
		int blue = fontColor + random.nextInt(backColor - fontColor);

		return new Color(red, green, blue);
	}
}
