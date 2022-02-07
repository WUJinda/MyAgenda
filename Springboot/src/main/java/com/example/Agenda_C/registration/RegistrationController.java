package com.example.Agenda_C.registration;

import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 120, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegistrationRequest request) throws Exception {
        return registrationService.register(request);
    }

    @GetMapping(path = "/accountVerification/{token}")
    public String confirmToken(@PathVariable String token) throws Exception {
        return registrationService.isValid(token);
    }

    @PostMapping("/signin")
    public User login(@RequestBody LoginRequest loginRequest) {
        return registrationService.signin(loginRequest.username, loginRequest.password);
    }

    @PatchMapping("/logout/{token}")
    public String logout(@PathVariable String token){
        return registrationService.logout(token);
    }

}
