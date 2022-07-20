package com.example.demowithuj.service.users;

import com.example.demowithuj.dto.request.UserRequestDto;
import com.example.demowithuj.dto.response.UserResponseDto;
import com.example.demowithuj.exceptions.BusinessLogicException;
import com.example.demowithuj.exceptions.UserAlreadyExistException;
import com.example.demowithuj.models.AppUser;
import com.example.demowithuj.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    AppUserRepository repository;

    private static BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<AppUser> getAllUsers() {
        log.info("Fetching all users");
        return repository.findAll();
    }

    @Override
    public Optional<AppUser> getUser(Long id) {
        log.info("Fetching user {}", id);
        return repository.findById(id);
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws BusinessLogicException, UserAlreadyExistException {

        boolean userExists = repository.findByEmail(userRequestDto.getEmail()).isPresent();
        if (userExists){
            throw new UserAlreadyExistException("Email already exist");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);

        AppUser newUser = new AppUser();

        newUser.setFirstName(userRequestDto.getFirstName());
        newUser.setLastName(userRequestDto.getLastName());
        newUser.setGender(userRequestDto.getGender());
        newUser.setPhoneNumber(userRequestDto.getPhoneNumber());
        newUser.setDob(userRequestDto.getDob());

        repository.save(newUser);

        return buildResponse(newUser);

    }

    private UserResponseDto buildResponse(AppUser appUser){
        return UserResponseDto.builder()
                .firstName(appUser.getFirstName())
                .phoneNumber(appUser.getPhoneNumber())
                .email(appUser.getEmail())
                .gender(appUser.getGender() )
                .build();
    }

    @Override
    public AppUser saveUser(AppUser appUsers) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) throws BusinessLogicException {
        Optional<AppUser> user = repository.findById(id);
        if (user.isPresent()){
            repository.delete(user.get());
            return true;
        }
        throw new BusinessLogicException("User with "+ id + " does not exist");
    }

}
