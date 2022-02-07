package com.example.Agenda_C.service;

import com.example.Agenda_C.domain.*;

import java.util.List;
import java.util.Optional;

import com.example.Agenda_C.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.Agenda_C.domain.User;

import java.util.List;

import com.example.Agenda_C.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;


public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


    public List<User> fetchAll(){
        return userRepository.findAll();
    }

    public void enableUser(Long id){
        User user = userRepository.getById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public User editProfile(User user) {
        User userOld = userRepository.findById(user.getId()).get();
        Role roleUser = user.getRole();
        // Infos
        userOld.setFirstname(user.getFirstname());
        userOld.setLastname(user.getLastname());
        userOld.setEmail(user.getEmail());
        userOld.setFullname(user.getFullname());
        return userRepository.save(user);
    }

    public User getloggedUser(String jwtToken){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        /*
        MyUserDetails currentUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

         */
        User currentuser = userRepository.findByTokenSignature(jwtToken).get();
        return currentuser;
    }

    public MyUserDetails getloggedUserDetails(){
        MyUserDetails currentUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return currentUserDetails;
    }

    public void deleteUser(User user){
        VerificationToken token = verificationTokenRepository.findByUser(user);
        if(token != null){
            verificationTokenRepository.delete(token);
        }
        if(user.getRole().getId()==1){
            List<Time> times = timeRepository.findAllByUserId(user.getId());
            if(!times.isEmpty()){
                timeRepository.deleteAll(times);
            }
        }
        else if(user.getRole().getId()==2){
            List<Project> projects = projectRepository.findByManagerId(user.getId());
            if(!projects.isEmpty()){
            for(Project p:projects) {
                List<Time> times = timeRepository.findAllByProjectId(p.getId());
                if(!times.isEmpty()){
                    timeRepository.deleteAll(times);
                }
            }
            projectRepository.deleteAll(projects);
            }
            List<User> users = userRepository.findByManagerId(user.getId());
            for(User u:users){
                u.setManager(null);
                userRepository.save(u);
            }
        }
        userRepository.delete(user);
        System.out.println("User Deleted");
    }
}
