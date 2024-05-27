package com.streamingapp.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.streamingapp.services.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

 @Autowired
 private CustomUserDetailsService userDetailsService;

 @Autowired
 private JwtUtil jwtUtil;

 @Override
 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
         throws ServletException, IOException {

     final String authorizationHeader = request.getHeader("Authorization");

     String username = null;
     String jwt = null;

     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
         jwt = authorizationHeader.substring(7);
         username = jwtUtil.extractUsername(jwt);
     }

     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
         if (jwtUtil.validateToken(jwt, userDetails)) {
             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                     userDetails, null, userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
     }
     chain.doFilter(request, response);
 }

 @Override
 protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
     String path = request.getRequestURI();
     return path.startsWith("/auth/register") || path.startsWith("/auth/authenticate");
 }
}
