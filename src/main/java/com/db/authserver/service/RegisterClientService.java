package com.db.authserver.service;

import com.db.authserver.dto.CustomRegisterClientDto;

public interface RegisterClientService {
    public CustomRegisterClientDto save(CustomRegisterClientDto dto);
}
