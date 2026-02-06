package com.example.securityapp.Repositories;

import com.example.securityapp.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {


    List<EmployeeEntity> findAll();

    EmployeeEntity findById(long id);

}
