package com.jdc.clinic.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {

	public boolean isUserInRole(String role) {
		
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		for(GrantedAuthority auth : ctx.getAuthentication().getAuthorities()) {
			if(auth.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	public String getLoginId() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		return ctx.getAuthentication().getName();
	}
}
