package com.db.authserver.controller;

import com.db.authserver.dto.UserRegisterDto;
import com.db.authserver.model.CustomUserModel;
import com.db.authserver.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CustomUserModel> registerUser(@RequestBody UserRegisterDto userRegisterDto){
        return new ResponseEntity<>(userService.saveUser(userRegisterDto), HttpStatus.CREATED);
    }

    @GetMapping("/authorized")
    public String authorized(){
        return "Authorized Endpoint";
    }

    @GetMapping("/")
    public String home(){
        return "Home Page";
    }

    @GetMapping("/principal")
    public Object principal(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
    }
}
