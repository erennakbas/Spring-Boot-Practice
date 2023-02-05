package com.warmup.project.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String nationalID;
    private String password;
}
