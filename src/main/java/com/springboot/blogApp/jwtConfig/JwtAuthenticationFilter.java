package com.springboot.blogApp.jwtConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    final JwtService jwtService;
    final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        final String authHeader = "Authorization";
        final String jwtToken;
        final String userEmail;
        if(authHeader == null && !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUsernameFromToken(jwtToken);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwtToken,userDetails))
            {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(

                        userDetails,
                        null,
                        userDetails.getAuthorities()

                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }


    }
}