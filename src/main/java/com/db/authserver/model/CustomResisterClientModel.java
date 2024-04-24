package com.db.authserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class CustomResisterClientModel {
    @Id
    private String clientId;
    private String clientSecret;
    @OneToMany(mappedBy = "registeredClient", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CustomRedirectUris> redirectUris;
    @OneToMany(mappedBy = "registeredClient", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CustomScopes> scopes;
    @OneToMany(mappedBy = "registeredClient", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CustomClientAuthenticationMethod> clientAuthenticationMethods;
    @OneToMany(mappedBy = "registeredClient", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CustomAuthorizationGrantType> customAuthorizationGrantTypes;
}
