package com.example.securityapp.Controller;


import com.example.securityapp.DTO.EmployeeDTO;
import com.example.securityapp.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //creating an employee
    @PostMapping
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employeeService.createEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {

        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeebyId(@PathVariable long id) {
        return employeeService.getemployeebyid(id);
    }








}
