package com.estsoft.springproject.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class SecondFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecondFilter.init()");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		System.out.println("SecondFilter.doFilter() request");

		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("SecondFilter.doFilter() response");

	}

	@Override
	public void destroy() {
		System.out.println("SecondFilter.destroy()");
	}
}
