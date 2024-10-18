package com.estsoft.springproject.filter;

import java.io.IOException;

import org.springframework.boot.autoconfigure.web.ServerProperties;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class FirstFilter implements Filter {

	// private final ServerProperties serverProperties;
	//
	// public FirstFilter(ServerProperties serverProperties) {
	// 	this.serverProperties = serverProperties;
	// }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("FirstFilter.init()");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException, ServletException {
		System.out.println("FirstFilter.doFilter() request");

		// requestURI
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		System.out.println("getRequestURI: " + request.getRequestURI());

		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("FirstFilter.doFilter() response");
	}

	@Override
	public void destroy() {
		System.out.println("FirstFilter.destroy()");
	}
}
