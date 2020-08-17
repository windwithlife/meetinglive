package com.project.meetinglive.core.spring.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

/**
 * 由于request.getInputStream()与request.getReader()只能调用一次之前在拦截器中已经读取了
 * 会导致在方法中无法获取到请求参数,故对HttpServletRequestWrapper重写
 * @author hejinguo
 * @version $Id: HandlerAuthLoginContextInterceptor.java, v 0.1 2019年11月17日 下午5:37:11
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] requestBody = null; //用于将流保存下来

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}
