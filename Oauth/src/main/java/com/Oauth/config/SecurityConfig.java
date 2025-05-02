package com.Oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Oauth.service.JwtTokenFilter;
import com.Oauth.service.JwtTokenProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()).sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth->auth.requestMatchers("/api/public/**").permitAll()
											.requestMatchers("/api/secured/**").authenticated()
											.anyRequest().authenticated())
											.oauth2Login(oauth2 -> oauth2.successHandler((request, response, authentication)->{
												String token=jwtTokenProvider.generateToken(authentication);
												response.getWriter().write(token);
												response.getWriter().flush();
											})).addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}