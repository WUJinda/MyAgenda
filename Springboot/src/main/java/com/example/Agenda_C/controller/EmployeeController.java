package com.example.Agenda_C.controller;

import com.example.Agenda_C.domain.Project;
import com.example.Agenda_C.domain.Time;
import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.dto.TimeRequest;
import com.example.Agenda_C.dto.UpdateRequest;
import com.example.Agenda_C.exporter.UserReportExporter;
import com.example.Agenda_C.repository.ProjectRepository;
import com.example.Agenda_C.repository.TimeRepository;
import com.example.Agenda_C.repository.UserRepository;
import com.example.Agenda_C.service.UserService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/user/{jwtToken}")
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TimeRepository timeRepository;


    @PatchMapping("/edit-profile/")
    public User updateProfile(@RequestBody UpdateRequest New_user,@PathVariable String jwtToken){
        User user = userService.getloggedUser(jwtToken);
        if(New_user.getFirstname()!=null){
            user.setFirstname(New_user.getFirstname());
        }
        if(New_user.getLastname()!=null){
            user.setLastname(New_user.getLastname());
        }
        if(New_user.getEmail()!=null){
            user.setEmail(New_user.getEmail());
        }
        if(New_user.getFullname()!=null){
            user.setFullname(New_user.getFullname());
        }
        if(New_user.getFirstname()!=null){
            user.setFirstname(New_user.getFirstname());
        }
        return userRepository.save(user);
    }

    @PostMapping("/choose-proj")
    public Time contributePro(@RequestBody TimeRequest timeRequest,@PathVariable String jwtToken){
        User currentUser = userService.getloggedUser(jwtToken);
        Project currentProject = projectRepository.findById(timeRequest.getProjectId()).get();
        User manager = currentProject.getManager();
        currentUser.setManager(manager);
        userRepository.save(currentUser);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        Time time = new Time(
                timeRequest.getDate_start(),
                timeRequest.getDate_end(),
                String.valueOf(timeRequest.getDate_start().format(formatter)),
                currentUser,
                currentProject
        );
        return timeRepository.save(time);
    }
/* */

    @GetMapping("/times")
    public List<Time> fetchAllTimesInDate(@RequestBody String date,@PathVariable String jwtToken){
        Long currentUser_id = userService.getloggedUser(jwtToken).getId();
        return timeRepository.findAllByUserIdAndDateOfProject(currentUser_id,date);
    }



    @GetMapping("/times/all")
    public List<Time> fetchAllTimes(@PathVariable String jwtToken){
        Long currentUser_id = userService.getloggedUser(jwtToken).getId();
        return timeRepository.findAllByUserId(currentUser_id);
    }

    @GetMapping("/projects")
    public List<Project> fetchAllProjects(){
        return projectRepository.findAll();
    }

    @GetMapping("/date/export/{date}")
    public void exportToPDF(HttpServletResponse response, @PathVariable String date,@PathVariable String jwtToken) throws DocumentException, IOException, DocumentException, IOException {
        User user = userRepository.findByTokenSignature(jwtToken).get();
        response.setContentType("application/pdf");
        List<Time> timesOfUser = timeRepository.findAllByUserIdAndDateOfProject(user.getId(), date);
        UserReportExporter exporter = new UserReportExporter(timesOfUser,user, date);
        exporter.export(response);

    }

}
