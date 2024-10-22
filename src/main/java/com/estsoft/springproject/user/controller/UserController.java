package com.estsoft.springproject.user.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.service.UserService;

@Configuration
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// POST /user 요청받고 회원가입 처리
	@PostMapping("/user")
	public String save(@ModelAttribute AddUserRequest request) {
		userService.save(request);
		return "redirect:/login";
	}
}
