package com.example.demowithuj.service.users;

import com.example.demowithuj.dto.request.UserRequestDto;
import com.example.demowithuj.dto.response.UserResponseDto;
import com.example.demowithuj.exceptions.BusinessLogicException;
import com.example.demowithuj.exceptions.UserAlreadyExistException;
import com.example.demowithuj.models.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> getAllUsers();

    Optional<AppUser> getUser(Long id);

    UserResponseDto createUser (UserRequestDto userRequestDto) throws BusinessLogicException, UserAlreadyExistException;

    AppUser saveUser(AppUser appUsers);

    Boolean deleteUser (Long id) throws BusinessLogicException;



}
