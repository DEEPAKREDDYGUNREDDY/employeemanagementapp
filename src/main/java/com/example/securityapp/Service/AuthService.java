package com.example.securityapp.Service;

import com.example.securityapp.DTO.LoginDTO;
import com.example.securityapp.DTO.LoginResponseDTO;
import com.example.securityapp.DTO.UserDTO;
import com.example.securityapp.Entities.UserEntity;
import com.example.securityapp.Repositories.AuthRepository;
import com.example.securityapp.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;


    private final PasswordEncoder passwordEncoder;



    private final AuthRepository repo;
    private final UserRepository userRepo;

    private final JWTService jwtService;

    private final SessionsService sessionsService;


    public void signup(UserDTO userDTO){
        UserEntity userE=dTOtoEntity(userDTO);
        String pass=userE.getPassword();
        userE.setPassword(passwordEncoder.encode(pass));


        repo.save(userE);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        System.out.println(loginDTO.getUsername());
        System.out.println(loginDTO.getPassword());

        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        UserEntity user=(UserEntity) auth.getPrincipal();


        assert user != null;

        String access_token=jwtService.generateToken(user);
        String refresh_token=jwtService.generaterefreshToken(user);

        sessionsService.storingsession(user,refresh_token);



        return new LoginResponseDTO(access_token,refresh_token);




    }

    public LoginResponseDTO refreshtoken(String refreshtoken){
        Long userid=jwtService.getId(refreshtoken);
        sessionsService.validaterefreshtoken(refreshtoken);
        UserEntity user=userRepo.findById(userid).orElse(null);

        String new_access_token=jwtService.generateToken(user);


        return new LoginResponseDTO(new_access_token,refreshtoken);


    }

    public UserDTO EntitytoDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());

        return userDTO;
    }

    public UserEntity dTOtoEntity(UserDTO userDTO){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRoles(userDTO.getRoles());
        return userEntity;
    }





}
