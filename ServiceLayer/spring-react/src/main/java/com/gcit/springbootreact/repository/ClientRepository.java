package com.gcit.springbootreact.repository;

import com.gcit.springbootreact.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

}
