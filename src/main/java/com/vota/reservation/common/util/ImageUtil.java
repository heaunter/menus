package com.vota.reservation.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {

	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/** JPG图片后缀 */
	public static final String JPG = ".jpg";

	/** PNG图片后缀. */
	public static final String PNG = ".png";

	/** 默认前缀. */
	private static String DEFAULT_PREVFIX = "thumb_";

	/** 是否强制. */
	private static Boolean DEFAULT_FORCE = false;

	/**
	 * 将字符串转为图片.
	 * 
	 * @param imgStr
	 *            图片字符串
	 * @param imgFile
	 *            图片文件地址
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean generateImage(String imgStr, String imgFile) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decodeBase64(imgStr.getBytes("UTF-8"));
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpg图片
			String imgFilePath = imgFile;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取图片字符串<br>
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理.
	 * 
	 * @param imgFile
	 *            图片文件地址
	 * @return the image str
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String generateImageStr(String imgFile) throws IOException {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		// 返回Base64编码过的字节数组字符串
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * <p>
	 * Title: thumbnailImage
	 * </p>
	 * <p>
	 * Description: 根据图片路径生成缩略图
	 * </p>
	 * 
	 * @param imagePath
	 *            原图片路径
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 * @param prevfix
	 *            生成缩略图的前缀
	 * @param force
	 *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public static String thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force)
			throws Exception {
		if (imgFile.exists()) {
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames());
				String suffix = null;
				// 获取图片后缀
				if (imgFile.getName().indexOf(".") > -1) {
					suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
				}// 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
					logger.error("Sorry, the image suffix is illegal. the standard image suffix is {}."
							+ types);
					throw new Exception("图片后缀名无效");
				}
				logger.debug("target image's size, width:{}, height:{}.", w, h);
				Image img = ImageIO.read(imgFile);
				if (!force) {
					// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
					int width = img.getWidth(null);
					int height = img.getHeight(null);
					if ((width * 1.0) / w < (height * 1.0) / h) {
						if (width > w) {
							h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w
									/ (width * 1.0)));
							logger.debug("change image's height, width:{}, height:{}.", w, h);
						}
					} else {
						if (height > h) {
							w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h
									/ (height * 1.0)));
							logger.debug("change image's width, width:{}, height:{}.", w, h);
						}
					}
				}
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
				g.dispose();
				String p = imgFile.getPath();
				// 将图片保存在原目录并加上前缀
				String finalName = prevfix + imgFile.getName();
				ImageIO.write(bi, suffix, new File(p.substring(0, p.lastIndexOf(File.separator))
						+ File.separator + finalName));
				return finalName;
			} catch (IOException e) {
				logger.error("generate thumbnail image failed.", e);
				throw e;
			}
		} else {
			logger.warn("the image is not exist.");
			throw new FileNotFoundException("图片不存在");
		}
	}

	public static String thumbnailImage(String imagePath, int w, int h, String prevfix,
			boolean force) throws Exception {
		File imgFile = new File(imagePath);
		return thumbnailImage(imgFile, w, h, prevfix, force);
	}

	public static String thumbnailImage(String imagePath, int w, int h, boolean force)
			throws Exception {
		return thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
	}

	public static String thumbnailImage(String imagePath, int w, int h) throws Exception {
		return thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
	}

}
