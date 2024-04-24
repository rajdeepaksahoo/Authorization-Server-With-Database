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
public class CustomRedirectUris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long redirectUriId;
    private String redirectUri;
    @ManyToOne
    @JoinColumn(name = "registered_client_id",referencedColumnName = "clientId")
    @JsonBackReference
    private CustomResisterClientModel registeredClient;

    public CustomRedirectUris(String redirectUri) {
        this.redirectUri=redirectUri;
    }
}
