package com.example.securityapp.DTO;


import com.example.securityapp.Entities.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class UserDTO {



    private String username;

    private String password;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
