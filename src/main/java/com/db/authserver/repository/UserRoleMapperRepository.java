package com.db.authserver.repository;

import com.db.authserver.model.CustomUserRoleMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleMapperRepository extends JpaRepository<CustomUserRoleMapper,Long> {
    @Query("SELECT rm FROM CustomUserRoleMapper rm WHERE rm.userId = :userId")
    public List<CustomUserRoleMapper> findByUserId(Long userId);
}
