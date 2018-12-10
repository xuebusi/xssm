package com.xuebusi.xssm.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * BASE64算法实现加解密
 *
 * @author xzb
 */
public class Base64Util {

    /**
     * base64算法加密
     *
     * @param data
     * @return
     */
    public static String base64Encrypt(byte[] data) {
        String result = new BASE64Encoder().encode(data);
        return result;
    }

    /**
     * base64算法解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String base64Decrypt(String data) throws Exception {
        byte[] resultBytes = new BASE64Decoder().decodeBuffer(data);
        return new String(resultBytes);
    }

    /**
     * 二值图片处理
     *
     * @param base64img  待处理的图片Base64编码
     * @param realWidth  图片宽度
     * @param realHeight 图片高度
     * @return 处理后的二值图片的Base64编码
     */
    public static String imgToBase64(String base64img, int realWidth, int realHeight) {
        try {

            byte[] bytes = Base64.getDecoder().decode(base64img);
            InputStream is = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(is);
            BufferedImage newImage = resize(image, realWidth, realHeight);
            BufferedImage grayImage = new BufferedImage(realWidth, realHeight, BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < realWidth; i++) {
                for (int j = 0; j < realHeight; j++) {
                    int rgb = newImage.getRGB(i, j);
                    grayImage.setRGB(i, j, rgb * 255);  //将像素存入缓冲区
                }
            }
            //将图片写入到本地文件
            //File newFile = new File("gray.jpg");
            //ImageIO.write(grayImage, "jpg", newFile);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(grayImage, "jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Img = encoder.encode(outputStream.toByteArray()).trim();
            base64Img = base64Img.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            return base64Img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}