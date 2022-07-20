package com.example.demowithuj.dto.request;

import com.example.demowithuj.models.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequestDto {

    private String firstName;

    private String middleName;

    private String lastName;

    private String dob;

    private String phoneNumber;

    private String email;

    private String password;

    private Gender gender;

    private String maritalStatus;

    private String religion;

    private String occupation;

    private LocalDateTime dateCreated;
}
