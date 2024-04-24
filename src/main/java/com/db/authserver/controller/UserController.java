package com.db.authserver.controller;

import com.db.authserver.dto.UserRegisterDto;
import com.db.authserver.model.CustomUserModel;
import com.db.authserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<CustomUserModel> registerUser(@RequestBody UserRegisterDto userRegisterDto){
        return new ResponseEntity<>(userService.saveUser(userRegisterDto), HttpStatus.CREATED);
    }
}
