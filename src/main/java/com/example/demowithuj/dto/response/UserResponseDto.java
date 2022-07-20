package com.example.demowithuj.dto.response;

import com.example.demowithuj.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserResponseDto {
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


}
