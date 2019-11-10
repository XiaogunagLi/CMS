package com.tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UrlFilter implements Filter {
	WebApplicationContext wac;

	public void init(FilterConfig config) throws ServletException {
		wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//����request�ַ�����
        request.setCharacterEncoding("utf-8");
     	//����response�ַ�����
        response.setContentType("text/html;charset=utf-8");
        //���ݸ���һ��������
        chain.doFilter(request, response);
	}

	public void destroy() {
		
	}
}
