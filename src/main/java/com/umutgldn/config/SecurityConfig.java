package com.umutgldn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.umutgldn.jwt.AuthEntryPoint;
import com.umutgldn.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public static final String AUTHENTICATE ="/authenticate";
	public static final String REGISTER="/register";
	public static final String REFRESH_TOKEN="/refreshToken";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(request ->
		request.requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN)
		.permitAll()
		.anyRequest()
		.authenticated())
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
