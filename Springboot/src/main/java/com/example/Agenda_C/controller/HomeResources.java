package com.example.Agenda_C.controller;

import com.example.Agenda_C.config.JwtTokenProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/")
    public String home(){
        return ("<h1>WELCOME</h1> <a href='http://localhost:4200/'>Home</a>");
    }

    @GetMapping("/user/")
    public String user_home(){
        return ("<h1>WELCOME User</h1>");
    }

    @GetMapping("/admin/")
    public String admin(){
        return ("<h1>WELCOME Admin</h1>");
    }

    @GetMapping("/manager/")
    public String manager(){
        return ("<h1>WELCOME MANAGER</h1>");
    }

    @PostMapping("/token/{token}")
    public boolean Valid(@PathVariable String token){
        return jwtTokenProvider.validateToken(token);
    }
}
