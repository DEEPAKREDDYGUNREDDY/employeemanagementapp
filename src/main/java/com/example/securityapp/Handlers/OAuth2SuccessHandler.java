package com.example.securityapp.Handlers;


import com.example.securityapp.Entities.UserEntity;
import com.example.securityapp.Service.AuthService;
import com.example.securityapp.Service.JWTService;
import com.example.securityapp.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    private final JWTService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;

        log.info(token.getCredentials().toString());



        DefaultOAuth2User user=(DefaultOAuth2User) token.getPrincipal();

        UserDetails userr=userService.loadUserByUsername(user.getAttribute("email").toString());

        UserEntity realuser=(UserEntity) userr;

        String access_token=jwtService.generateToken(realuser);
        String refresh_token=jwtService.generaterefreshToken(realuser);

        Cookie cookie = new Cookie("backuprefreshtoken", refresh_token);

        response.addCookie(cookie);

        String redirecturl="http://localhost:8080/success.html?token="+access_token;

        getRedirectStrategy().sendRedirect(request, response, redirecturl);




    }


}
