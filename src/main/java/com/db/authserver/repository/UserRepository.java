package com.db.authserver.repository;

import com.db.authserver.model.CustomUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUserModel,Long> {
    @Query("SELECT u FROM CustomUserModel u WHERE u.username=:username")
    public Optional<CustomUserModel> findByUsername(String username);
}
