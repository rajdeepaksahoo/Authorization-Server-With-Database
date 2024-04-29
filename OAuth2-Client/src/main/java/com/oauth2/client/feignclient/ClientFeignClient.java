package com.oauth2.client.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "client", url = "http://localhost:9001")
public interface ClientFeignClient {
    @GetMapping("/resourceServer/check")
    Map<String,String> getPost(@RequestHeader("Authorization") String authorizationHeader);
}
