package com.dreamteam.TestingSystemNew.controller;

import com.dreamteam.TestingSystemNew.enumeration.RoleName;
import com.dreamteam.TestingSystemNew.exception.AppException;
import com.dreamteam.TestingSystemNew.model.Groups;
import com.dreamteam.TestingSystemNew.model.Role;
import com.dreamteam.TestingSystemNew.model.User;
import com.dreamteam.TestingSystemNew.payload.*;
import com.dreamteam.TestingSystemNew.repository.GroupsRepository;
import com.dreamteam.TestingSystemNew.repository.RoleRepository;
import com.dreamteam.TestingSystemNew.repository.UserRepository;
import com.dreamteam.TestingSystemNew.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Constructor;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    RandomNumber randomNumber;

    @GetMapping ("/get/constructor")
    public Object randNumber()
    {
        return randomNumber.number();
    }


    @PostMapping("/get/constructor")
    public Object CheckResult(@RequestParam(defaultValue = "+") String sign,@RequestParam int NumeratorD,
                              @RequestParam int Denominator, @RequestParam int Numerator )
    {
        return randomNumber.checkResult(sign, NumeratorD, Denominator, Numerator);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
        } catch (AuthenticationException e) {
            logger.error("Неверное предоставленное имя пользователя/пароль");
            throw new BadCredentialsException("Неверное предоставленное имя пользователя/пароль");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Пользователь не найден с именем пользователя: " + username)
                );

        String jwt = tokenProvider.generateToken(authentication);
        UserProfile userProfile = new UserProfile(user.getId(), user.getUserName(), user.getName(), user.getLastName(), user.getMiddleName());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user.getRoles().iterator().next(), userProfile));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Пользователь с таким логином занято!"),
                    HttpStatus.BAD_REQUEST);
        }

        // создание аккаунта
        Role userRole = roleRepository.findByRole(String.valueOf(RoleName.ROLE_STUDENT))
                .orElseThrow(() -> new AppException("User Role not set."));
        Groups group = groupsRepository.getByName(signUpRequest.getNumberGroup());

        User user = new User(signUpRequest.getUserName(), signUpRequest.getPassword(),
                signUpRequest.getName(), signUpRequest.getLastName(), signUpRequest.getMiddleName(), Collections.singleton(userRole));

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        user.setRoles(Collections.singleton(userRole));
        user.setGroups(Collections.singletonList(group));
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "Вы успешно зарегистрировались"));
    }
}
