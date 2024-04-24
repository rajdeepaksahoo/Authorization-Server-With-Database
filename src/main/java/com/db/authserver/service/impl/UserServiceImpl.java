package com.db.authserver.service.impl;

import com.db.authserver.dto.UserRegisterDto;
import com.db.authserver.exception.UserIsAlreadyRegisteredException;
import com.db.authserver.model.CustomRoleModel;
import com.db.authserver.model.CustomUserModel;
import com.db.authserver.model.CustomUserRoleMapper;
import com.db.authserver.repository.RoleRepository;
import com.db.authserver.repository.UserRepository;
import com.db.authserver.repository.UserRoleMapperRepository;
import com.db.authserver.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleMapperRepository userRoleMapperRepository;
    @Override
    @Transactional
    public CustomUserModel saveUser(UserRegisterDto userRegisterDto) {
        var customUserModel = userRegisterDto.getCustomUserModel();
        var customRoleModel = userRegisterDto.getCustomRoleModel();
        if(userRepository.findByUsername(customUserModel.getUsername()).isEmpty()){
            CustomUserRoleMapper customUserRoleMapper = new CustomUserRoleMapper();
            customUserRoleMapper.setUserId(userRepository.save(customUserModel).getUserId());
            customUserRoleMapper.setRoleId(roleRepository.save(customRoleModel).getRoleId());
            userRoleMapperRepository.save(customUserRoleMapper);
            return customUserModel;
        }
        throw new UserIsAlreadyRegisteredException("User is already registered...");
    }
}
