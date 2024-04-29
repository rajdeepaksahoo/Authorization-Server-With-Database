package com.oauth2.resouece.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${jwksUri}")
    private String jwksUri;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
        Enable JWT
         */
        http.oauth2ResourceServer(resource->resource.jwt(
                jwt-> jwt.jwkSetUri(jwksUri)
                        .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
        ));

        http.authorizeHttpRequests(req->req
                .requestMatchers("/resourceServer").permitAll()
//                .requestMatchers("/resourceServer/protected").hasRole("SCOPE_openid")
                .requestMatchers("/resourceServer/protected").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        return http.build();
    }
}
