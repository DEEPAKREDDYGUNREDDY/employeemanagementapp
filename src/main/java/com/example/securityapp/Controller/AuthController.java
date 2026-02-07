package com.example.securityapp.Controller;

import com.example.securityapp.DTO.LoginDTO;
import com.example.securityapp.DTO.LoginResponseDTO;
import com.example.securityapp.DTO.UserDTO;
import com.example.securityapp.Service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO userDTO){



        authService.signup(userDTO);

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDTO loginresponse=authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshtoken", loginresponse.getRefresh_token());
        cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(loginresponse);



    }

    // This neeeds to be hitt if the accesstoken validation expires then this api hit first this validates refreshtoken
    //if valid then generate new accesstoken
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        String refreshtoken= Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("refreshtoken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow();


       LoginResponseDTO neLoginresponseDTO=authService.refreshtoken(refreshtoken);
       return ResponseEntity.ok(neLoginresponseDTO);

    }

}
