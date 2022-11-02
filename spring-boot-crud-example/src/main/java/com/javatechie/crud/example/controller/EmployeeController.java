package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Employee;
import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.model.EmployeeDto;
import com.javatechie.crud.example.model.Response;
import com.javatechie.crud.example.repository.EmployeeRepository;
import com.javatechie.crud.example.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/EmployeeService")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody Employee employee){
        Employee newEmployee = service.saveEmployee(employee);
        return new Response(newEmployee.getId() + " inserted ", Boolean.TRUE);
    }

    @PostMapping("/addEmployees")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees){
        return service.saveEmployees(employees);
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return service.getEmployees();
    }

//    @GetMapping("/getEmployees")
//    public Response getAllEmployees(){
//        List<Employee> employees = repository.findAll();
//        return new Response("record counts : " +employees.size(),Boolean.TRUE);
//    }

    @GetMapping("/getEmployees")
    public Response getAllEmployees(){
        List<Employee> employees = service.getEmployees();
        return new Response("record counts : " +employees.size(),Boolean.TRUE);
    }

    /*@PostMapping("/addEmp")
    public Response addEmp(@RequestBody Employee employee){
        repository.save(employee);
        return new Response(employee.getId() + " inserted " , Boolean.TRUE);
    }*/

    @GetMapping("/employeeByActive/{active}")
    public List<EmployeeDto> getEmployeeByActive(@PathVariable Boolean active) {
        logger.info("Entering into getEmployeeByActive()");
        return service.getActiveEmployees(active);
    }

    @GetMapping("/employeesBySortOrder/{sortOrder}")
    public List<Employee> getEmployeeBySortOrder(@PathVariable String sortOrder) {
        logger.info("entering into getEmployeeBySortOrder()");
        return service.getEmployeesBySortOrder(sortOrder);
    }
}
