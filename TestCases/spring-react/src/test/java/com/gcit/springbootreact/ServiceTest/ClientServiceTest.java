package com.gcit.springbootreact.ServiceTest;

import com.gcit.springbootreact.Utils.TestUtils;
import com.gcit.springbootreact.Service.ClientService;
import com.gcit.springbootreact.model.Client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    @Transactional
    public void saveClientTest() {
        clientService.deleteClient();
        Client client = TestUtils.getClient(1L, "sam");
        Client actual = clientService.saveClient(client);
        assertEquals(actual.getName(), "sam");
    }

    @Test
    @Transactional
    public void findByIdTest() {
        clientService.deleteClient();
        Client client = TestUtils.getClient(2L, "ram");
        Client actual = clientService.saveClient(client);
        Optional<Client> savedClient = clientService.findById(actual.getId());
        assertEquals(savedClient.get().getName(), actual.getName());
    }

    @Test
    @Transactional
    public void findByNameTest() {
        clientService.deleteClient();
        List<Client> clients = TestUtils.getClients();
        List<Client> actual = clientService.saveClients(clients);
        List<Client> savedClient = clientService.findByName(actual.get(0).getName());
        List<Client> savedClient1 = clientService.saveClients(savedClient);
        assertEquals(savedClient1.get(0).getName(), actual.get(0).getName());
    }

    @Test
    @Transactional
    public void getClientsTest() {

        clientService.deleteClient();
        List<Client> clientList = TestUtils.getClients();
        List<Client> client = clientService.saveClients(clientList);
        List<Client> actual = clientService.getClients();
        assertEquals(actual.size(), 2);

    }

    @Test
    @Transactional
    public void updateClientTest() {
        clientService.deleteClient();
        Client client = TestUtils.getClient(3L, "ravi");
        Client actual = clientService.saveClient(client);
        String actualName = actual.getName();
        assertEquals(actualName, "ravi");
        Client actual1 = clientService.updateClient(actual);
        Client client1 = TestUtils.getClient(4L, "joe");
        Client actualClient = clientService.saveClient(client1);
        String actualClientName = actualClient.getName();
        assertEquals(actualClientName, "joe");
    }

    @Test
    @Transactional
    public void deleteClientTest() {
        clientService.deleteClient();
        Client client = TestUtils.getClient(1l, "david");
        Client actual = clientService.saveClient(client);
        String actualName = actual.getName();
        assertEquals(actualName, "david");
        Client delete = clientService.deleteById(actual.getId());
        assertNull(delete);
    }

}
