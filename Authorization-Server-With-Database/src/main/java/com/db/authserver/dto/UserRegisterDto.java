package com.db.authserver.dto;

import com.db.authserver.model.CustomRoleModel;
import com.db.authserver.model.CustomUserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private CustomUserModel customUserModel;
    private CustomRoleModel customRoleModel;
}
