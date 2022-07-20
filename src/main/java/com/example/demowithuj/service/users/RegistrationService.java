package com.example.demowithuj.service.users;

import com.example.demowithuj.dto.request.LoginRequest;
import com.example.demowithuj.dto.request.RoleRequest;
import com.example.demowithuj.dto.request.SignUpRequest;
import com.example.demowithuj.dto.response.MessageResponse;
import com.example.demowithuj.exceptions.BusinessLogicException;
import com.example.demowithuj.exceptions.DemoWithUJException;
import com.example.demowithuj.exceptions.UserAlreadyExistException;
import com.example.demowithuj.models.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistrationService {


    ResponseEntity<?> authenticateUser(LoginRequest loginRequest) throws DemoWithUJException;

    ResponseEntity<MessageResponse> createUser (SignUpRequest signUpRequest) throws BusinessLogicException, UserAlreadyExistException;

    ResponseEntity<MessageResponse> createModerator(SignUpRequest signUpRequest) throws BusinessLogicException, UserAlreadyExistException;

    RoleRequest createRole(RoleRequest roleName);

    List<Role> getAllRoles();
}
