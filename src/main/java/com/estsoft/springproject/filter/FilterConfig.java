package com.estsoft.springproject.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<Filter> firstFilter(){
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

		filter.setFilter(new FirstFilter());
		filter.addUrlPatterns("/books");
		return filter;
	}

	@Bean
	public FilterRegistrationBean<Filter> secondFilter(){
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();

		filter.setFilter(new SecondFilter());
		filter.addUrlPatterns("/books");
		filter.setOrder(2); // doFilter() 순서 보장

		return filter;
	}

	// @Bean
	// public FilterRegistrationBean<Filter> thirdFilter(){
	// 	FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
	//
	// 	filter.setFilter(new ThirdFilter());
	// 	filter.addUrlPatterns("/books");
	// 	filter.setOrder(3);
	//
	// 	return filter;
	// }
}
