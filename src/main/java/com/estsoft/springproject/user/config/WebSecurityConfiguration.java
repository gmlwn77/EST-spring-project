package com.estsoft.springproject.user.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.estsoft.springproject.user.repository.UserRepository;
import com.estsoft.springproject.user.service.UserDetailService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {
	// 특정 요청은 스프링 시큐리티 설정 타지 않도록 ignore 처리

	private final UserRepository userRepository;

	public WebSecurityConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public WebSecurityCustomizer ignore() {
		return webSecurity -> webSecurity.ignoring()
			.requestMatchers("/static/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
	}

	// 2) 특정 HTTP 요청에 대한 웹 기반 보안 구성
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// 람다표현식 사용해야함
		// 3) 인증, 인가 설정
		return httpSecurity.authorizeHttpRequests(
				custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll()
					//.requestMatchers("/articles/**").hasRole("user")
					//.anyRequest().authenticated()
					.anyRequest().permitAll()
			)
			// .requestMatchers("/login", "/signup", "/user").permitAll()
			// .anyRequest().authenticated()
			//.and()

			//4) 폼 기반 로그인 설정
			.formLogin(custom -> custom.loginPage("/login").defaultSuccessUrl("/articles"))
			// .formLogin()
			// .loginPage("/login")
			// .defaultSuccessUrl("/articles") // 로그인 성공했을 경우 리디렉션 URL
			//.and()

			// 5) 로그아웃 설정
			.logout(custom -> custom.logoutSuccessUrl("/login").invalidateHttpSession(true))
			// .logout()
			// .logoutSuccessUrl("/login")// 로그아웃 성공 시 리디렉션 URL
			// .invalidateHttpSession(true) // 로그아웃 했을 때 해당 세션 제거할건지
			// .and()

			// 6) csrf 비활성화 // 디폴트가 활성화
			.csrf(custom -> custom.disable())
			// .csrf().disable()
			.build();
	}

	// 패스워드 암호화 방식 (BCryptPasswordEncoder) 빈 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}