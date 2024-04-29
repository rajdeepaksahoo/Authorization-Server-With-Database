package com.db.authserver.security.userdetails.service;

import com.db.authserver.model.CustomRoleModel;
import com.db.authserver.model.CustomUserModel;
import com.db.authserver.model.CustomUserRoleMapper;
import com.db.authserver.repository.RoleRepository;
import com.db.authserver.repository.UserRepository;
import com.db.authserver.repository.UserRoleMapperRepository;
import com.db.authserver.security.userdetails.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private UserRoleMapperRepository userRoleMapperRepository;
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUserModel> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            List<CustomUserRoleMapper> listOfMapper =
                    userRoleMapperRepository.findByUserId(byUsername.get().getUserId());
            List<CustomRoleModel> roleList =
                    roleRepository.findByRoleId(listOfMapper.stream().map(CustomUserRoleMapper::getRoleId).toList());
            return new CustomUserDetails(byUsername.get(),roleList);
        }
        throw new UsernameNotFoundException("User is not registered...");
    }
}
