package com.deveficiente.desafiomercadolivre.compartilhado.seguranca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	@Autowired
	private UsersService usersService;

	@Autowired
	private TokenManager tokenManager;

	
	private static final Logger log = LoggerFactory
			.getLogger(SecurityConfiguration.class);

	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/produtos/{id:[0-9]+}").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.antMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.cors()
			.and()
				.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(new JwtAuthenticationFilter(tokenManager, usersService), 
						UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
				.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
				
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usersService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html",  "/v2/api-docs", "/webjars/**", 
				"/configuration/**", "/swagger-resources/**", "/css/**", "/**.ico", "/js/**");
	}
	
	private static class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

		private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
		
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			
			logger.error("Um acesso não autorizado foi verificado. Mensagem: {}", authException.getMessage());
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esse recurso.");
		}
	}
}
