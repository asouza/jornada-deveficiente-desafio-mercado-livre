package com.deveficiente.desafiomercadolivre.compartilhado.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private TokenManager tokenManager;
	private UsersService usersService;
	
	public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
		this.tokenManager = tokenManager;
		this.usersService = usersService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String jwt = getTokenFromRequest(request);
		
        if (tokenManager.isValid(jwt)) {
            
        	String userName = tokenManager.getUserName(jwt);
            UserDetails userDetails = usersService.loadUserByUsername(userName);
            
            UsernamePasswordAuthenticationToken authentication = 
            			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))			
			return bearerToken.substring(7);
		
		return null;
	}

}
