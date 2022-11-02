package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.controller.EmployeeController;
import com.javatechie.crud.example.entity.Employee;

import com.javatechie.crud.example.model.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
    @Query("select NEW com.javatechie.crud.example.model.EmployeeDto(e.name) from Employee e where e.active = :active")
    public List<EmployeeDto> findAllActiveEmployees(@PathParam("active") Boolean active);

}
