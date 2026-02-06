package com.example.securityapp.Repositories;


import com.example.securityapp.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {


}
