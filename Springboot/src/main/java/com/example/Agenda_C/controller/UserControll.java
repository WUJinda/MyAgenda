package com.example.Agenda_C.controller;

import com.example.Agenda_C.domain.Role;
import com.example.Agenda_C.domain.User;
//import com.example.Agenda_C.dto.AuthenticationResponse;
import com.example.Agenda_C.dto.LoginRequest;
import com.example.Agenda_C.dto.UpdateRequest;

import com.example.Agenda_C.repository.UserRepository;
//import com.example.Agenda_C.service.AuthentificationService;
import com.example.Agenda_C.service.MyUserDetails;
import com.example.Agenda_C.service.MyUserDetailsService;
import com.example.Agenda_C.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import com.example.Agenda_C.repository.UserRepository;
import com.example.Agenda_C.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

// bd18ff7b9c88fb177b00c1189176fdd846572092
import java.util.List;

@RestController
@RequestMapping(path = "/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserControll {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> fetchAll(){
        return userRepository.findAll();
    }

    @PatchMapping("/edit-profile/{id}")
    public User updateProfile(@PathVariable Long id, @RequestBody UpdateRequest New_user){
        User user = userRepository.getById(id);
        user.setFirstname(New_user.getFirstname());
        user.setLastname(New_user.getLastname());
        user.setEmail(New_user.getEmail());
        user.setFullname(New_user.getFullname());
        return userRepository.save(user);
    }

    @GetMapping("/loggedInUser/{jwtToken}")
    public User fetchLIU(@PathVariable String jwtToken){
        return (userService.getloggedUser(jwtToken));
    }

    @GetMapping("/loggedInUserId/{jwtToken}")
    public String fetchLIUId(@PathVariable String jwtToken){
        return String.valueOf(userService.getloggedUser(jwtToken).getId());
    }

    @GetMapping("/getRole/{id}")
    public Role fetchRole(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        return user.getRole();
    }

}
