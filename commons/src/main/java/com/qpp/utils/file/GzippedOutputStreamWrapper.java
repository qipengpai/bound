package com.qpp.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author qipengpai
 * @Title: GzippedOutputStreamWrapper
 * @ProjectName bound
 * @Description: TODO
 * @date 17:08 2018/10/11
 */
public class GzippedOutputStreamWrapper  extends HttpServletResponseWrapper {

    // 日志
    private final static Logger logger = LoggerFactory.getLogger(GzippedOutputStreamWrapper.class);

    ByteArrayOutputStream output;
    FilterServletOutputStream filterOutput;
    HttpServletResponse httpServletResponse;

    public GzippedOutputStreamWrapper(HttpServletResponse response) {
        super(response);
        this.httpServletResponse= response;
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStream(output);
        }
        return filterOutput;
    }

    public byte[] getDataStream() {
        return output.toByteArray();
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    /**
     * @Author qipengpai
     * @Description //TODO gzip压缩响应
     * @Date 17:10 2018/10/11
     * @Param [response, msg]
     * @Throws
     * @return void
     **/
    public static void sendMessage(HttpServletResponse response, String msg) throws IOException {
        GZIPOutputStream gOut = null;
        try {
            gOut = new GZIPOutputStream(response.getOutputStream());
            gOut.write(msg.getBytes("UTF-8"));
        } catch (Exception e) {
            logger.error("gzip压缩失败");
        } finally {
            try {
                if (gOut != null) {
                    gOut.flush();
                    gOut.finish();
                    gOut.close();
                }
            } catch (Exception e) {
                logger.error("gzip压缩失败");
            }
        }
    }
}
