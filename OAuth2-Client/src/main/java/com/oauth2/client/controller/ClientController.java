package com.oauth2.client.controller;

import com.oauth2.client.feignclient.ClientFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ClientController {
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    private ClientFeignClient feignClient;
    @GetMapping
    public Map<String, String> getToken(){
        OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("1")
                .principal("d9c66632-f127-4cf4-a491-af7caa52f18f")
                .build();
        String token =  oAuth2AuthorizedClientManager.authorize(oAuth2AuthorizeRequest).getAccessToken().getTokenValue();
        return feignClient.getPost("Bearer "+token);
    }
}
