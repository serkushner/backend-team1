package com.exadel.project.administrator.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AdministratorDto {

    private Long id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private String skype;
    private Set<String> roles = new HashSet<>();

}
