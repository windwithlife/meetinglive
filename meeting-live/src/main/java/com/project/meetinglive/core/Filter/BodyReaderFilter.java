package com.project.meetinglive.core.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.project.meetinglive.core.spring.stream.BodyReaderHttpServletRequestWrapper;

/**
 * 过滤器实现可以复用的请求体流
 * @author hejinguo
 * @version $Id: BodyReaderFilter.java, v 0.1 2019年11月17日 下午5:12:30
 */
@WebFilter(filterName = "bodyReaderFilter", urlPatterns = "/*")
public class BodyReaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                                                             throws IOException,
                                                                                             ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hreq = (HttpServletRequest) request;
            String url = hreq.getRequestURI();
            //上传头像
            if (!url.contains("uploadImage")) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(hreq);
            }
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
