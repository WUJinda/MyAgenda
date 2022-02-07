package com.example.Agenda_C.registration;


import com.example.Agenda_C.config.CustomException;
import com.example.Agenda_C.config.JwtTokenProvider;
import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.domain.VerificationToken;
import com.example.Agenda_C.repository.RoleRepository;
import com.example.Agenda_C.repository.UserRepository;
import com.example.Agenda_C.repository.VerificationTokenRepository;
import com.example.Agenda_C.service.EmailService;
import com.example.Agenda_C.service.UserService;
import com.example.Agenda_C.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import com.example.Agenda_C.service.Impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class RegistrationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    //private EmailValidator emailValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired

    @Bean
    private UserService userService(){
        return new UserService();
    };
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    //private final JwtProvider jwtProvider;

    public User register(RegistrationRequest registerRequest) throws Exception {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setFullname(registerRequest.getLastname()+" "+registerRequest.getFirstname());
        user.setRole(roleRepository.findById(registerRequest.getRoleId()).get());
        user.setEnabled(false);
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
        String token = generateVerificationToken(user);
        /*
        mailService.sendMail(new NotifEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to <strong>Agenda</strong>, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8090/auth/accountVerification/" + token));


         */
        /*
        System.out.println("Token : "+token);
        String subject = "Thank you for signing up to <strong>Agenda</strong>, ";
        String body = "please click on the below url to activate your account : " +
                "http://localhost:8090/auth/accountVerification/" + token;
        emailService.sendSimpleMessage(user.getEmail(), String.valueOf(MAIL), subject, body);
         */
        System.out.println(token);
        return user;
    }

    public String generateVerificationToken(User user) {
        // TODO Auto-generated method stub
        String token = UUID.randomUUID().toString();
        //LocalDateTime createdAt =
        VerificationToken verificationToken = new VerificationToken(
                token,
                user,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10)
        );;
        tokenRepository.save(verificationToken);
        return token;
    }


    @Transactional
    public String isValid(String token) throws IllegalArgumentException{
        VerificationToken verificationToken = verificationTokenService.getToken(token);
        if(verificationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed");
        }
        LocalDateTime expiredAt = verificationToken.getExpiredAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            User user = verificationTokenService.getToken(token).getUser();
            userRepository.delete(user);
            throw new IllegalStateException("Token expired");
        }
        verificationTokenService.setConfirmedAt(verificationToken);
        Long user_id = verificationToken.getUser().getId();
        User user = userRepository.getById(user_id);
        user.setEnabled(true);
        userRepository.save(user);
        //userService().enableUser(user_id);
        return "Confirmed";
    }

    public User signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String JwtToken = jwtTokenProvider.createToken(username, userRepository.findByUsername(username).get().getRole());
            System.out.println(JwtToken);
            User loggedInUser = userRepository.findByUsername(username).get();
            if(true){//loggedInUser.getTokenSignature()==null
                loggedInUser.setTokenSignature(JwtToken);
                userRepository.save(loggedInUser);
                return loggedInUser;
            }
            System.out.println("Already Connected");
            return loggedInUser;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String logout(String token){
        User currentUser = userRepository.findByTokenSignature(token).get();
        currentUser.setTokenSignature(null);
        userRepository.save(currentUser);

        return "Bye Bye";
    }

}
