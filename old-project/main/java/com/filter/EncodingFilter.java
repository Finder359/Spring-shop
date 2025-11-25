package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 可以从 web.xml 获取编码参数（可选）
        String paramEncoding = filterConfig.getInitParameter("encoding");
        if (paramEncoding != null) {
            encoding = paramEncoding;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 设置请求与响应的编码
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);

        // 放行，让请求继续走后面的 Servlet/JSP
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 释放资源（这里不用写）
    }
}
