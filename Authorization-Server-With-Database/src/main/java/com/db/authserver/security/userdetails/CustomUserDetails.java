package com.db.authserver.security.userdetails;

import com.db.authserver.model.CustomRoleModel;
import com.db.authserver.model.CustomUserModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private CustomUserModel customUserModel;
    private List<CustomRoleModel> customRoleModel;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customRoleModel.stream().map(customRoleModel1 ->
                new SimpleGrantedAuthority(customRoleModel1.getRoleName())).toList();
    }

    @Override
    public String getPassword() {
        return customUserModel.getPassword();
    }

    @Override
    public String getUsername() {
        return customUserModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return customUserModel.getIsUserActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return customUserModel.getIsUserActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return customUserModel.getIsUserActive();
    }

    @Override
    public boolean isEnabled() {
        return customUserModel.getIsUserActive();
    }
}
