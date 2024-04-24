package com.db.authserver.repository;

import com.db.authserver.model.CustomResisterClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredClientRepository extends JpaRepository<CustomResisterClientModel,String> {
}
