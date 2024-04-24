package com.db.authserver.service.impl;

import com.db.authserver.dto.CustomRegisterClientDto;
import com.db.authserver.repository.RegisteredClientRepository;
import com.db.authserver.security.registerclintrepository.CustomRegisterClientRepositoryImpl;
import com.db.authserver.service.RegisterClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterClientServiceImpl implements RegisterClientService {
    private RegisteredClientRepository registeredClientRepository;
    private CustomRegisterClientRepositoryImpl customRegisterClientRepositoryImpl;
    @Override
    public CustomRegisterClientDto save(CustomRegisterClientDto dto) {
        customRegisterClientRepositoryImpl.save(dto.from(dto));
        return dto;
    }
}
