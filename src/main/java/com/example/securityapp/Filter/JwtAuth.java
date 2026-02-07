package com.example.securityapp.Filter;

import com.example.securityapp.Entities.UserEntity;
import com.example.securityapp.Service.JWTService;
import com.example.securityapp.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuth extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserService userService;


    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requesttokenHeader=request.getHeader("Authorization");
        if(requesttokenHeader==null || !requesttokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token=requesttokenHeader.split("Bearer ")[1];

        String username=jwtService.getUsername(token);


        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails user=userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken t=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(t);

        }
        filterChain.doFilter(request, response);

    }
}
