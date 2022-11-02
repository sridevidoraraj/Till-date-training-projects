package com.gcit.springbootreact.repository;

import com.gcit.springbootreact.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
