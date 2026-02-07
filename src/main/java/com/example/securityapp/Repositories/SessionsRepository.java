package com.example.securityapp.Repositories;

import com.example.securityapp.Entities.Sessions;
import com.example.securityapp.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionsRepository extends JpaRepository<Sessions,Long> {

    List<Sessions> findByUser(UserEntity user);

    Sessions findByrefreshtoken(String refreshtoken);
}
