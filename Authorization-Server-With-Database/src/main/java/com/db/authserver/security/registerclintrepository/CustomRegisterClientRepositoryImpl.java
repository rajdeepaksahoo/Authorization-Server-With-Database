package com.db.authserver.security.registerclintrepository;

import com.db.authserver.dto.CustomRegisterClientDto;
import com.db.authserver.model.CustomResisterClientModel;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class CustomRegisterClientRepositoryImpl implements RegisteredClientRepository {
    private com.db.authserver.repository.RegisteredClientRepository registeredClientRepository;
    @Override
    public void save(RegisteredClient registeredClient) {
        CustomRegisterClientDto customRegisterClientDto= new CustomRegisterClientDto().from(registeredClient);
        CustomResisterClientModel customResisterClientModel = mapToEntity(customRegisterClientDto);
        setId(customResisterClientModel);
        customResisterClientModel.setClientId(UUID.randomUUID().toString());
        registeredClientRepository.save(customResisterClientModel);
    }

    private void setId(CustomResisterClientModel customResisterClientModel) {
        customResisterClientModel.getScopes().forEach(customScopes -> customScopes.setRegisteredClient(customResisterClientModel));
        customResisterClientModel.getRedirectUris().forEach(customScopes -> customScopes.setRegisteredClient(customResisterClientModel));
        customResisterClientModel.getClientAuthenticationMethods().forEach(customScopes -> customScopes.setRegisteredClient(customResisterClientModel));
        customResisterClientModel.getCustomAuthorizationGrantTypes().forEach(customScopes -> customScopes.setRegisteredClient(customResisterClientModel));
    }

    @Override
    public RegisteredClient findById(String id) {
        Optional<CustomResisterClientModel> byClientId = registeredClientRepository.findById(id);
        if(byClientId.isPresent()){
            CustomRegisterClientDto customRegisterClientDto = new CustomRegisterClientDto();
            RegisteredClient registeredClient = customRegisterClientDto.from(byClientId.get());
            return registeredClient;
        }
        throw new RuntimeException("Client Not Registered");
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<CustomResisterClientModel> byClientId = registeredClientRepository.findById(clientId);
        if(byClientId.isPresent()){
            CustomRegisterClientDto customRegisterClientDto = new CustomRegisterClientDto();
            RegisteredClient registeredClient = customRegisterClientDto.from(byClientId.get());
            return registeredClient;
        }
        throw new RuntimeException("Client Not Registered");
    }

    private CustomResisterClientModel mapToEntity(CustomRegisterClientDto dto){
        return CustomResisterClientModel.builder()
                .clientId(dto.getClientId())
                .clientSecret(dto.getClientSecret())
                .clientAuthenticationMethods(dto.getClientAuthenticationMethods())
                .scopes(dto.getScopes())
                .redirectUris(dto.getRedirectUris())
                .customAuthorizationGrantTypes(dto.getCustomAuthorizationGrantTypes())
                .build();
    }
}
