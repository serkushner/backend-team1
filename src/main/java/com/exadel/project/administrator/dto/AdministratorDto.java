package com.exadel.project.administrator.dto;

import com.exadel.project.administrator.entity.Role;
import com.exadel.project.trainee.dto.TraineeDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private Role role;
    private List<TraineeDTO> traineesDTO = new ArrayList<>();

}
