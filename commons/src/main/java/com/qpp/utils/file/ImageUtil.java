package com.qpp.utils.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author qipengpai
 * @Title: ImageUtil
 * @ProjectName bound
 * @Description: TODO 图片工具类
 * @date 17:40 2018/10/11
 */
public class ImageUtil {


    /**
     * @Author qipengpai
     * @Description //TODO 获取图片宽度
     * @Date 17:41 2018/10/11
     * @Param [file] file 图片文件
     * @Throws
     * @return int 宽度
     **/
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }



    /**
     * @Author qipengpai
     * @Description //TODO 获取图片高度
     * @Date 17:41 2018/10/11
     * @Param [file] 图片文件
     * @Throws
     * @return int 高度
     **/
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
