package com.javatechie.crud.example.service;


import com.javatechie.crud.example.controller.EmployeeController;
import com.javatechie.crud.example.entity.Employee;
import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.model.EmployeeDto;
import com.javatechie.crud.example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee employee){
         Employee emp = repository.save(employee);
         return emp;
    }

    public List<Employee> saveEmployees(List<Employee> employees){
        return repository.saveAll(employees);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        //System.out.println("getting data from db : " +employees);
        return employees;
    }

    public List<EmployeeDto> getActiveEmployees(Boolean active) {
        logger.debug("Entering into getActiveEmployees()" + "active employees are " +active);
        return repository.findAllActiveEmployees(active);
    }

    public List<Employee> getEmployeesBySortOrder(String sortOrder) {
        logger.debug("Entering into getEmployeesBySortOrder()");
        logger.debug("Param -> sortOrder : " + sortOrder);
        if ("desc".equalsIgnoreCase(sortOrder)) {
            logger.debug("Sorting Order is Descending");
            return repository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        }
        logger.debug("Sorting Order is Ascending");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
