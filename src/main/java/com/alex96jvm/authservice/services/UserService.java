package com.alex96jvm.authservice.services;

import com.alex96jvm.authservice.exceptions.RoleNotFoundException;
import com.alex96jvm.authservice.exceptions.UserAlreadyExistException;
import com.alex96jvm.authservice.exceptions.UserNotFoundOrPasswordWrongException;
import com.alex96jvm.authservice.models.dto.UserAuthorizationRequest;
import com.alex96jvm.authservice.models.dto.UserAuthorizationResponse;
import com.alex96jvm.authservice.models.dto.UserRegistrationResponse;
import com.alex96jvm.authservice.models.dto.UserRegistrationRequest;
import com.alex96jvm.authservice.models.entities.RoleEntity;
import com.alex96jvm.authservice.models.entities.RoleType;
import com.alex96jvm.authservice.models.entities.UserEntity;
import com.alex96jvm.authservice.repositories.RoleRepository;
import com.alex96jvm.authservice.repositories.UserRepository;
import com.alex96jvm.authservice.security.JwtTokenProvider;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, @Lazy JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserRegistrationResponse createUser(UserRegistrationRequest request) throws UserAlreadyExistException, RoleNotFoundException {
        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        RoleEntity roleEntity = roleRepository.findById(RoleType.USER_ROLE.getId())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(request.getLogin());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
        return new UserRegistrationResponse(userEntity.getId(), userEntity.getLogin());
    }
    public UserAuthorizationResponse authorizeUser(UserAuthorizationRequest request) throws UserNotFoundOrPasswordWrongException {
        UserEntity userEntity = userRepository.findByLogin(request.login()).orElseThrow(UserNotFoundOrPasswordWrongException::new);
        if (!passwordEncoder.matches(request.password(), userEntity.getPassword())) {
            throw new UserNotFoundOrPasswordWrongException();
        }
        String token = jwtTokenProvider.createToken(userEntity.getId(), userEntity.getLogin(), userEntity.getRole());
        return new UserAuthorizationResponse(userEntity.getId(), userEntity.getLogin(), userEntity.getRole(), token);
    }


}
