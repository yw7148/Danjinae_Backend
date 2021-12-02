package com.capstone.danjinae.user.JWT.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.danjinae.user.JWT.service.JwtService;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        final String jwtToken = httpServletRequest.getHeader(JwtService.ACCESS_TOKEN_NAME);

        String username = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUname = null;

        try {
            if (jwtToken != null) {
                jwt = jwtToken;
                username = jwtService.getUsername(jwt);
            }
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException e) {
            String refreshToken = httpServletRequest.getHeader(JwtService.REFRESH_TOKEN_NAME);
            if (refreshToken != null) {
                refreshJwt = refreshToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (refreshJwt != null) {
                refreshUname = jwtService.getRefreshUsername(refreshJwt);

                if (refreshUname.equals(jwtService.getUsername(refreshJwt))) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUname);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    String newAccessToken = jwtService.generateToken(refreshUname);

                    httpServletResponse.addHeader(JwtService.ACCESS_TOKEN_NAME, newAccessToken);
                }
            }
        } catch (ExpiredJwtException e) {

        } catch (Exception e) {

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
