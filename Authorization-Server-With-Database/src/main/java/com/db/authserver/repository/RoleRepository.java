package com.db.authserver.repository;

import com.db.authserver.model.CustomRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<CustomRoleModel,Long> {
    @Query("SELECT r FROM CustomRoleModel r WHERE r.roleId IN :roleIds")
    List<CustomRoleModel> findByRoleId(List<Long> roleIds);
}
