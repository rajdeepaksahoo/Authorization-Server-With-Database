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
public class CustomClientAuthenticationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientAuthenticationId;
    private String clientAuthenticationMethodName;
    @ManyToOne
    @JoinColumn(name = "registered_client_id",referencedColumnName = "clientId")
    @JsonBackReference
    private CustomResisterClientModel registeredClient;

    public CustomClientAuthenticationMethod(String clientAuthenticationMethodName) {
        this.clientAuthenticationMethodName = clientAuthenticationMethodName;
    }
}
