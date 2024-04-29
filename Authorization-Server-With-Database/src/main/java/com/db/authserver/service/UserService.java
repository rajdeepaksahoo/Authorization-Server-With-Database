package com.db.authserver.service;

import com.db.authserver.dto.UserRegisterDto;
import com.db.authserver.model.CustomRoleModel;
import com.db.authserver.model.CustomUserModel;

public interface UserService {
    public CustomUserModel saveUser(UserRegisterDto userRegisterDto);
}
