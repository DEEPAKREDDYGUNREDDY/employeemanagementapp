package com.example.securityapp.Service;

import com.example.securityapp.DTO.EmployeeDTO;
import com.example.securityapp.Entities.EmployeeEntity;
import com.example.securityapp.Repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Method for creating employee
    public void createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity newentity=new EmployeeEntity();
        newentity.setName(employeeDTO.getName());
        newentity.setAge(employeeDTO.getAge());
        employeeRepository.save(newentity);
    }

    //Method for getting by id
    public EmployeeDTO getemployeebyid(Long id){
        EmployeeEntity entity=employeeRepository.findById(id).orElse(null);
        return toDTO(entity);


    }

    //Getting all employees
    public List<EmployeeDTO> getEmployees(){
        List<EmployeeEntity> allentities=employeeRepository.findAll();
        List<EmployeeDTO> employees=new ArrayList<>();
        for(EmployeeEntity entity:allentities){
            employees.add(toDTO(entity));
        }
        return employees;
    }




    //Mapper Methods
    //entity to dto
    public EmployeeDTO toDTO(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setAge(employeeEntity.getAge());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setId(employeeEntity.getId());
        return employeeDTO;
    }


}
