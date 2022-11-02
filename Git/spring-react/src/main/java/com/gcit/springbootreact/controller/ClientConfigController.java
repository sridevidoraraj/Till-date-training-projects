package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.model.ClientConfig;
import com.gcit.springbootreact.repository.ClientConfigRepository;
import com.gcit.springbootreact.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/config")
@CrossOrigin(origins = "http://localhost:8081")
public class ClientConfigController {

    private final ClientConfigRepository clientConfigRepository;
    private final ClientRepository clientRepository;

    public ClientConfigController(ClientConfigRepository clientConfigRepository, ClientRepository clientRepository) {
        this.clientConfigRepository = clientConfigRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<ClientConfig> getClientConfigs() {
        return clientConfigRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClientConfig getById(@PathVariable Long id) {
        return clientConfigRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClientConfig(@RequestBody ClientConfig clientConfig) throws URISyntaxException {
        ClientConfig savedClientConfig = clientConfigRepository.save(clientConfig);
        Optional<Client> client = clientRepository.findById(clientConfig.getClient().getId());
        savedClientConfig.setClient(client.get());
        return ResponseEntity.created(new URI("/config" + savedClientConfig.getId())).body(savedClientConfig);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientConfig> updateClientConfig(@PathVariable(value = "id") Long id, @RequestBody ClientConfig clientConfig) {
        ClientConfig currentClientConfig = clientConfigRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClientConfig.setId(id);
        if(clientConfig.getKey() != null) {
            currentClientConfig.setKey(clientConfig.getKey());
        }
        if(clientConfig.getvalue() != null) {
            currentClientConfig.setvalue(clientConfig.getvalue());
        }
        ClientConfig detached = clientConfigRepository.save(currentClientConfig);
        return ResponseEntity.ok(detached);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientConfig(@PathVariable Long id) {
        clientConfigRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByClientId/{client}")
    public List<ClientConfig> getByClientId(@PathVariable Long client){
        return clientConfigRepository.findAllByClientId(client);
    }
}
