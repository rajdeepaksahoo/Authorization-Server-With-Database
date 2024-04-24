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
public class CustomAuthorizationGrantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorizationGrantTypeId;
    private String authorizationGrantTypeName;
    @ManyToOne
    @JoinColumn(name = "registered_client_id",referencedColumnName = "clientId")
    @JsonBackReference
    private CustomResisterClientModel registeredClient;

    public CustomAuthorizationGrantType(String authorizationGrantTypeName) {
        this.authorizationGrantTypeName=authorizationGrantTypeName;
    }
}
