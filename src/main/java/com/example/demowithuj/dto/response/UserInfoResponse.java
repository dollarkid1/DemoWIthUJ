package com.example.demowithuj.dto.response;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String phoneNumber;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Long id, String phoneNumber, String email, List<String> roles) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getRoles() {
        return roles;
    }
}
