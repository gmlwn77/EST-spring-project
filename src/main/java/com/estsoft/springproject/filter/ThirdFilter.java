package com.estsoft.springproject.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class ThirdFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("ThirdFilter.init()");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		System.out.println("ThirdFilter.doFilter() request");

		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("ThirdFilter.doFilter() response");

	}

	@Override
	public void destroy() {
		System.out.println("ThirdFilter.destroy()");
	}
}
