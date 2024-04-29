package com.db.authserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CustomScopes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scopeId;
    private String scopeName;
    @ManyToOne
    @JoinColumn(name = "registered_client_id",referencedColumnName = "clientId")
    @JsonBackReference
    private CustomResisterClientModel registeredClient;

    public CustomScopes(String scopeName) {
        this.scopeName=scopeName;
    }
}
