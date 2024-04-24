package com.db.authserver.controller;

import com.db.authserver.dto.CustomRegisterClientDto;
import com.db.authserver.service.RegisterClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/registerClient")
public class RegisterClientController {
    private RegisterClientService registerClientService;

    @PostMapping
    public CustomRegisterClientDto save(@RequestBody CustomRegisterClientDto customRegisterClientDto){
        return registerClientService.save(customRegisterClientDto);
    }
}
