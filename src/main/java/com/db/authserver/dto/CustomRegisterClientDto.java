package com.db.authserver.dto;

import com.db.authserver.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomRegisterClientDto {
    private String clientId;
    private String clientSecret;
    private List<CustomRedirectUris> redirectUris;
    private List<CustomScopes> scopes;
    private List<CustomClientAuthenticationMethod> clientAuthenticationMethods;
    private List<CustomAuthorizationGrantType> customAuthorizationGrantTypes;


    public RegisteredClient from(CustomRegisterClientDto dto){
        return RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(dto.getClientId())
                .clientSecret(dto.getClientSecret())
                .clientAuthenticationMethods(clientAuthenticationMethods1 -> 
                        clientAuthenticationMethods1.addAll(getAuthenticationMethods(dto.getClientAuthenticationMethods())))
                .scopes(strings -> strings.addAll(getAllScopeList(dto.getScopes())))
                .authorizationGrantTypes(authorizationGrantTypes ->
                        authorizationGrantTypes.addAll(getAuthorizationGrantTypeList(dto.getCustomAuthorizationGrantTypes())))
                .redirectUris(strings -> strings.addAll(dto.getRedirectUris().stream().map(CustomRedirectUris::getRedirectUri).toList()))
                .build();
    }


    public RegisteredClient from(CustomResisterClientModel dto){
        return RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(dto.getClientId())
                .clientSecret(dto.getClientSecret())
                .clientAuthenticationMethods(clientAuthenticationMethods1 ->
                        clientAuthenticationMethods1.addAll(getAuthenticationMethods(dto.getClientAuthenticationMethods())))
                .scopes(strings -> strings.addAll(getAllScopeList(dto.getScopes())))
                .authorizationGrantTypes(authorizationGrantTypes ->
                        authorizationGrantTypes.addAll(getAuthorizationGrantTypeList(dto.getCustomAuthorizationGrantTypes())))
                .redirectUris(strings -> strings.addAll(dto.getRedirectUris().stream().map(CustomRedirectUris::getRedirectUri).toList()))
                .build();
    }


    public CustomRegisterClientDto from(RegisteredClient registeredClient){
        return CustomRegisterClientDto.builder()
                .clientId(registeredClient.getClientId())
                .clientSecret(registeredClient.getClientSecret())
                .scopes(registeredClient.getScopes().stream().map(s -> new CustomScopes(s.toUpperCase())).toList())
                .clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream().map(clientAuthenticationMethod ->
                        new CustomClientAuthenticationMethod(clientAuthenticationMethod.getValue())).toList())
                .customAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(authorizationGrantType ->
                        new CustomAuthorizationGrantType(authorizationGrantType.getValue())).toList())
                .redirectUris(registeredClient.getRedirectUris().stream().map(CustomRedirectUris::new).toList())
                .build();
    }

    /*

        Mapping from ClientAuthenticationMethod String to ClientAuthenticationMethod Type

    */
    private Collection<ClientAuthenticationMethod> getAuthenticationMethods
            (List<CustomClientAuthenticationMethod> clientAuthenticationMethods) {
        List<ClientAuthenticationMethod> clientAuthenticationMethodList = new ArrayList<>();
        clientAuthenticationMethods.forEach(customClientAuthenticationMethod -> {
            if(customClientAuthenticationMethod.getClientAuthenticationMethodName().equalsIgnoreCase("CLIENT_SECRET_BASIC")){
                clientAuthenticationMethodList.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
            }else if(customClientAuthenticationMethod.getClientAuthenticationMethodName().equalsIgnoreCase("CLIENT_SECRET_JWT")){
                clientAuthenticationMethodList.add(ClientAuthenticationMethod.CLIENT_SECRET_JWT);
            }else if(customClientAuthenticationMethod.getClientAuthenticationMethodName().equalsIgnoreCase("CLIENT_SECRET_POST")){
                clientAuthenticationMethodList.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
            }
            else if(customClientAuthenticationMethod.getClientAuthenticationMethodName().equalsIgnoreCase("NONE")){
                clientAuthenticationMethodList.add(ClientAuthenticationMethod.NONE);
            }else if(customClientAuthenticationMethod.getClientAuthenticationMethodName().equalsIgnoreCase("PRIVATE_KEY_JWT")){
                clientAuthenticationMethodList.add(ClientAuthenticationMethod.PRIVATE_KEY_JWT);
            }
        });
        return clientAuthenticationMethodList;
    }
    /*

        Mapping from scope String to Scope Type

    */
    private Collection<String> getAllScopeList(List<CustomScopes> scopes) {
        List<String> oidcScopesList = new ArrayList<>();
        scopes.forEach(customScopes -> {
            if(customScopes.getScopeName().equalsIgnoreCase("OPENID")){
                oidcScopesList.add(OidcScopes.OPENID);
            }else if(customScopes.getScopeName().equalsIgnoreCase("PROFILE")){
                oidcScopesList.add(OidcScopes.PROFILE);
            }else if(customScopes.getScopeName().equalsIgnoreCase("PHONE")){
                oidcScopesList.add(OidcScopes.PHONE);
            }
            else if(customScopes.getScopeName().equalsIgnoreCase("ADDRESS")){
                oidcScopesList.add(OidcScopes.ADDRESS);
            }else if(customScopes.getScopeName().equalsIgnoreCase("EMAIL")){
                oidcScopesList.add(OidcScopes.EMAIL);
            }
        });
        return oidcScopesList;
    }
    /*

        Mapping from AuthorizationGrantType String to AuthorizationGrantType Type

    */

    private Collection<AuthorizationGrantType> getAuthorizationGrantTypeList
            (List<CustomAuthorizationGrantType> customAuthorizationGrantTypes) {
        List<AuthorizationGrantType> authorizationGrantTypeList = new ArrayList<>();
        customAuthorizationGrantTypes.forEach(customAuthorizationGrantType -> {
            if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("REFRESH_TOKEN")){
                authorizationGrantTypeList.add(AuthorizationGrantType.REFRESH_TOKEN);
            }else if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("AUTHORIZATION_CODE")){
                authorizationGrantTypeList.add(AuthorizationGrantType.AUTHORIZATION_CODE);
            }else if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("CLIENT_CREDENTIALS")){
                authorizationGrantTypeList.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
            }else if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("DEVICE_CODE")){
                authorizationGrantTypeList.add(AuthorizationGrantType.DEVICE_CODE);
            }else if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("JWT_BEARER")){
                authorizationGrantTypeList.add(AuthorizationGrantType.JWT_BEARER);
            }else if(customAuthorizationGrantType.getAuthorizationGrantTypeName().equalsIgnoreCase("PASSWORD")){
                authorizationGrantTypeList.add(AuthorizationGrantType.PASSWORD);
            }
        });
        return authorizationGrantTypeList;
    }
}
