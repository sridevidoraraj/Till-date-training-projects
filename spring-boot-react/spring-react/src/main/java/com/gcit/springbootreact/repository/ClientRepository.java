package com.gcit.springbootreact.repository;

import com.gcit.springbootreact.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);
}
