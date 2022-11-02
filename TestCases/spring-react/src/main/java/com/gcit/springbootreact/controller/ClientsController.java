package com.gcit.springbootreact.controller;

import com.gcit.springbootreact.Service.ClientService;
import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@Component
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:8081")
public class ClientsController {


    @Autowired
    private ClientService clientService;


    @CrossOrigin(origins = "http://localhost:8081")

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/addClient")
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.created(new URI("/clients/addClient" + savedClient.getId())).body(savedClient);
    }

    @PutMapping("/")
    public Client updateClient(@RequestBody Client client) {

        return clientService.updateClient(client);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteClient() {
        clientService.deleteClient();
        return ResponseEntity.ok().build();
    }

}
