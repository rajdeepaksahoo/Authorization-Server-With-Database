package com.db.authserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CustomUserRoleMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapperId;
    private Long roleId;
    private Long userId;
}
