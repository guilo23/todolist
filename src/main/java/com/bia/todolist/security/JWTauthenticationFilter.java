package com.bia.todolist.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.util.ArrayList;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bia.todolist.Exceptions.GlobalExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTauthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTSecurity jwtUtil;

    public JWTauthenticationFilter(AuthenticationManager authenticationManager, JWTSecurity jwtUtil) {
        setAuthenticationFailureHandler(new GlobalExceptionHandler());
        setAuthenticationManager(authenticationManager);
        this.jwtUtil = jwtUtil;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      try{
        UserSpringSecurity creds = new ObjectMapper().readValue(request.getInputStream(), UserSpringSecurity.class);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>());
        Authentication auth = getAuthenticationManager().authenticate(authToken);
        return auth;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        UserSpringSecurity userSpringSecurity = (UserSpringSecurity) auth.getPrincipal();
        String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateTolken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
