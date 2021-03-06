package com.qpp.utils.file;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author qipengpai
 * @Title: GzippedInputStreamWrapper
 * @ProjectName bound
 * @Description: TODO
 * @date 17:06 2018/10/11
 */
@Slf4j
public class GzippedInputStreamWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public GzippedInputStreamWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream stream = request.getInputStream();
        // 如果对内容进行了压缩，则解压
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(stream);

            ServletInputStream newStream = new ServletInputStream() {
                @Override
                public int read() throws IOException {
                    return gzipInputStream.read();
                }

                @Override
                public boolean isFinished() {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public boolean isReady() {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {
                    // TODO Auto-generated method stub

                }
            };
            return newStream;
        } catch (Exception e) {
            log.error("ungzip content fail.", e);
        }
        return stream;
    }
}
