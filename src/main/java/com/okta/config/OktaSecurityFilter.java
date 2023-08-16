package com.okta.config;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class OktaSecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String jwtString = request.getHeader("Authorization");   
        AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
            .setAudience("api://default") 
            .setIssuer("https://dev-55287942.okta.com/oauth2/default")               // defaults to 'api://default'
            .setConnectionTimeout(Duration.ofSeconds(1)) // defaults to 1
            .build(); 
        try {
            Jwt jwt = jwtVerifier.decode(jwtString);
            filterChain.doFilter(request, response);
        } catch (JwtVerificationException e) {
            e.printStackTrace();;
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getOutputStream().println(new ObjectMapper().
            writeValueAsString("Invalid Okta token"));
        }

    }


}

