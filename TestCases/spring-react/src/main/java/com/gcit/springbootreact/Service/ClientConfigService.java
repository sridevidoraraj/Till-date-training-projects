package com.gcit.springbootreact.Service;

import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
import com.gcit.springbootreact.repository.ClientConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientConfigService {
    @Autowired
    private ClientConfigRepository clientConfigRepository;
    @Autowired
    private ClientService clientService;


    public ClientConfig saveClientConfig(ClientConfig clientConfig) {
        ClientConfig cliConfig = clientConfigRepository.save(clientConfig);
        return cliConfig;
    }

    public List<ClientConfig> saveClientConfigs(List<ClientConfig> clientConfigs) {
        return clientConfigRepository.saveAll(clientConfigs);
    }

    public ClientConfig createClientConfig(ClientConfig clientConfig) {
        List<Client> client = clientService.findByName(clientConfig.getClient().getName());
        clientConfig.setClient(client.get(0));
        ClientConfig savedClientConfig = clientConfigRepository.save(clientConfig);
        return savedClientConfig;
    }

    public ClientConfig updateClientConfig(Long id, ClientConfig clientConfig) {
        ClientConfig currentClientConfig = clientConfigRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClientConfig.setId(id);
        if (clientConfig.getKey() != null) {
            currentClientConfig.setKey(clientConfig.getKey());
        }
        if (clientConfig.getvalue() != null) {
            currentClientConfig.setvalue(clientConfig.getvalue());
        }
        ClientConfig detached = clientConfigRepository.save(currentClientConfig);
        return detached;
    }


    public Optional<ClientConfig> findById(Long id) {
        return clientConfigRepository.findById(id);
    }

    public List<ClientConfig> getClientConfigs() {
        List<ClientConfig> clientConfigs = clientConfigRepository.findAll();
        return clientConfigs;
    }

    public void deleteClientConfig() {
        clientConfigRepository.deleteAll();
    }

    public List<ClientConfig> findAllByClientId(Long id) {
        clientConfigRepository.findAllByClientId(id);
        return null;
    }

    public ClientConfig deleteById(Long id) {
        clientConfigRepository.deleteById(id);
        return null;
    }

}
