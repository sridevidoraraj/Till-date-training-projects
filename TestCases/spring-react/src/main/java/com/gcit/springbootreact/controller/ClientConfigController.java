package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.Service.ClientConfigService;
import com.gcit.springbootreact.Service.ClientService;
import com.gcit.springbootreact.model.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@Component
@RequestMapping("/config")
@CrossOrigin(origins = "http://localhost:8081")
public class ClientConfigController {
    @Autowired
    private ClientConfigService clientConfigService;
    @Autowired
    private ClientService clientService;


    @GetMapping
    public List<ClientConfig> getClientConfigs() {
        return clientConfigService.getClientConfigs();
    }

    @GetMapping("/{id}")
    public ClientConfig getById(@PathVariable Long id) {
        return clientConfigService.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ClientConfig createClientConfig(@RequestBody ClientConfig clientConfig) throws URISyntaxException {

        return clientConfigService.createClientConfig(clientConfig);
    }

    @PutMapping("/{id}")
    public ClientConfig updateClientConfig(@PathVariable(value = "id") Long id, @RequestBody ClientConfig clientConfig) {

        return clientConfigService.updateClientConfig(id, clientConfig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientConfigService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByClientId/{client}")
    public List<ClientConfig> getByClientId(@PathVariable Long id) {
        return clientConfigService.findAllByClientId(id);
    }

    @DeleteMapping
    public ResponseEntity deleteClientConfig() {
        clientConfigService.deleteClientConfig();
        return ResponseEntity.ok().build();
    }
}
