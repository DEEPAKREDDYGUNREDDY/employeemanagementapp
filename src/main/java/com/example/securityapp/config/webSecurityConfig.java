package com.example.securityapp.config;


import com.example.securityapp.Filter.JwtAuth;
import com.example.securityapp.Handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.securityapp.Entities.enums.Role.DIRECTOR;
import static com.example.securityapp.Entities.enums.Role.MANAGER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class webSecurityConfig {

    private final JwtAuth jwtAuth;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authreq->authreq

                        .requestMatchers("/auth/**","/success.html").permitAll()
                        .requestMatchers("/apis/employees").hasRole("DIRECTOR")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
               .csrf(csrf-> csrf.disable())

             // .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2Login(oauth2config->oauth2config
//                        .failureUrl("/login?error=true")
//                        .successHandler(oAuth2SuccessHandler));
//                .formLogin(Customizer.withDefaults())


        ;

        return http.build();


    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }









}
