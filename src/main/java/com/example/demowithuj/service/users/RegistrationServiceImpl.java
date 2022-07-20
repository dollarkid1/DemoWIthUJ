package com.example.demowithuj.service.users;

import com.example.demowithuj.dto.request.LoginRequest;
import com.example.demowithuj.dto.request.RoleRequest;
import com.example.demowithuj.dto.request.SignUpRequest;
import com.example.demowithuj.dto.response.MessageResponse;
import com.example.demowithuj.dto.response.UserInfoResponse;
import com.example.demowithuj.exceptions.BusinessLogicException;
import com.example.demowithuj.exceptions.DemoWithUJException;
import com.example.demowithuj.exceptions.UserAlreadyExistException;
import com.example.demowithuj.models.AppUser;
import com.example.demowithuj.models.ERole;
import com.example.demowithuj.models.Role;
import com.example.demowithuj.repository.AppUserRepository;
import com.example.demowithuj.repository.RoleRepository;
import com.example.demowithuj.security.jwt.JwtUtils;
import com.example.demowithuj.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    AppUserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) throws DemoWithUJException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @Override
    public ResponseEntity<MessageResponse> createUser(SignUpRequest signUpRequest) throws BusinessLogicException, UserAlreadyExistException {

        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        AppUser user = new AppUser(signUpRequest.getPhoneNumber(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

        @Override
    public ResponseEntity<MessageResponse> createModerator(SignUpRequest signUpRequest) throws BusinessLogicException, UserAlreadyExistException {

        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        AppUser moderator = new AppUser(signUpRequest.getPhoneNumber(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);
        moderator.setRoles(roles);

        userRepository.save(moderator);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }




    @Override
    public RoleRequest createRole(RoleRequest roleName) {
        Role userRole;
        if (roleName.getName().equalsIgnoreCase("Role_Admin")) {
            userRole = new Role(ERole.ROLE_ADMIN);
        } else if (roleName.getName().equalsIgnoreCase("Role_Moderator")) {
            userRole = new Role(ERole.ROLE_MODERATOR);
        } else {
            userRole = new Role(ERole.ROLE_USER);
        }

        roleRepository.save(userRole);

        return roleName;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
