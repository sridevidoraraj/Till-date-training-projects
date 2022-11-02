package com.gcit.springbootreact.Service;

import com.gcit.springbootreact.model.Client;
import com.gcit.springbootreact.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public Client saveClient(Client client) {
        Client cli = clientRepository.save(client);
        return cli;
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> getClients() {
        List<Client> client = clientRepository.findAll();
        return client;
    }

    public Client updateClient(Client client) {
        Client currentClient = clientRepository.findById(client.getId()).orElse(null);
        currentClient.setName(client.getName());
        return clientRepository.save(currentClient);

    }

    public void deleteClient() {
        clientRepository.deleteAll();
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
