package com.oauth2.resouece.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/resourceServer")
public class ResourceController {
    @GetMapping
    public String home(){
        return "Home Page Of Resource Server";
    }
    @GetMapping("/protected")
    public String protectedEndpoint(Authentication authentication){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList());
        return "protected Endpoint Of Resource Server";
    }
    @GetMapping("/check")
    public Map<String,String> check(){
        return Map.of("Status","Active");
    }
}
