package com.db.authserver.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Collection;
import java.util.UUID;

@Configuration
public class SecurityConfig {
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainForOAuth2(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint("/login")
        ));
        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/login","/register","/registerClient").permitAll()
                                .requestMatchers("/user/authorized").hasRole("ADMIN")
                                .requestMatchers("/user").hasRole("USER")
                                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    /*
        Password Encoder bean. You can change the instance on password encoder as per your need.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    /*
        Add Authorization server settings here
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().build();
    }

    /*
        Customize claims in JWT Token
     */

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> customizer(){
        return context -> {
            context.getClaims().claim("key","value");

            /*
                Add Authorities in the token
             */

            //Retrieve authorities of the user

            Collection<? extends GrantedAuthority> authorities = context.getPrincipal().getAuthorities();

            //Add Authorities to the token

            context.getClaims().claim("authorities",authorities.stream().map(grantedAuthority ->
                    grantedAuthority.getAuthority()).toList());
        };
    }
    @Bean
    public TokenSettings tokenSettings(){
        return TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(1)).build();
    }

    /*
        We need to create JWKSet i.e public and private key pair to let the client use it to verify the token
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var keyPair = keyPairGenerator.generateKeyPair();
        var aPublic = keyPair.getPublic();
        var aPrivate = keyPair.getPrivate();
        var rsaKey = new RSAKey.Builder((RSAPublicKey) aPublic)
                .privateKey(aPrivate)
                .keyID(UUID.randomUUID().toString())
                .build();
        var jwkSet = new JWKSet(rsaKey);

        return new ImmutableJWKSet<>(jwkSet);
    }

    /*
        JWTDecoder bean
     */

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
}
