package com.example.Agenda_C.controller;

import com.example.Agenda_C.domain.Project;
import com.example.Agenda_C.domain.Time;
import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.dto.Affectation;
import com.example.Agenda_C.dto.UpdateGlobal;
import com.example.Agenda_C.dto.UpdateRequest;
import com.example.Agenda_C.exporter.UserReportExporter;
import com.example.Agenda_C.registration.RegistrationRequest;
import com.example.Agenda_C.registration.RegistrationService;
import com.example.Agenda_C.repository.*;
import com.example.Agenda_C.service.UserService;
import com.example.Agenda_C.utils.RolesU;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.example.Agenda_C.utils.RolesU.*;

@RestController
@RequestMapping(path = "/admin/{jwtToken}")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
public class AdminControll {
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


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

    @GetMapping("/all-users")
    public List<User> seeAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/user/details/{id}")
    public  User getDetails(@PathVariable Long id){
        return userRepository.findById(id).get();
    }
    @GetMapping("/users-manager/{manager_id}")
    public List<User> seeSome(@PathVariable Long manager_id){
        List<User> users = userRepository.findByManagerId(manager_id);
        return users;
    }
    /*
    @PatchMapping("/role/{id}")
    public User editRole(@PathVariable Long id,@RequestBody String role){
        User user = userRepository.findById(id).get();
        RolesU rolesU = new RolesU();
        if(user.getRole().getLabel()==EMPLOYEE_LABEL && (role==MANAGER_LABEL || role==ADMIN_LABEL)){
            user.setManager(null);
            user.setRole(roleRepository.findByLabel(role));
            return userRepository.save(user);
        }else if (user.getRole().getLabel()==MANAGER_LABEL && role==ADMIN_LABEL){
            user.setRole(roleRepository.findByLabel(role));
            return userRepository.save(user);
        }else{
            System.out.println("You can not Be Employee Or Manager");
        }
        return user;
    }

    @PostMapping("/disable-user/{id}")
    public User disableUser(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        if(user.getRole().getLabel()==ADMIN_LABEL){
            throw new IllegalStateException("You cannot disable an Admin");
        }
        user.setEnabled(false);
        return userRepository.save(user);
    }

    @PostMapping("/enable-user/{id}")
    public User enableUser(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        user.setEnabled(true);
        return userRepository.save(user);
    }


     */
    @PostMapping("/affectation")
    public Project changeAffectation(@RequestBody Affectation affectation){
        User manager = userRepository.findById(affectation.getManagerId()).get();
        System.out.println("II\n");
        Project project = projectRepository.getById(affectation.getProjectId());
        System.out.println(project);
        // Affect All affected users to new Manger
        System.out.println("III\n");
        List<User> affectedUsers = userRepository.findByManagerId(project.getManager().getId());
        System.out.println("IIII\n");
        for(User u:affectedUsers){
            u.setManager(manager);
            System.out.println("IIIII\n");
            userRepository.save(u);
        }
        //Project to new Manager
        project.setManager(manager);
        System.out.println(project);
        System.out.println("IIIIII\n");
        System.out.println(project);
        return projectRepository.save(project);
    }

    @PatchMapping("/update/user/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody UpdateGlobal updateGlobal){
        User user = userRepository.findById(id).get();
        // Enable/Disable
        user.setEnabled(updateGlobal.isEnabled());
        //Role
        if(updateGlobal.getRoleId()!=null){
            if(user.getRole().getLabel().equals("MANAGER") && updateGlobal.getRoleId()!=2L){
                List<User> usersAff = userRepository.findByManagerId(user.getId());
                for(User u:usersAff){
                    if(u!=null){
                        u.setManager(null);
                        userRepository.save(u);
                    }
                    List<Project> projects = projectRepository.findByManagerId(user.getId());
                    if(projects.size()>0 && projects!=null){
                        projects.forEach(project -> {project.setManager(null);});
                        projectRepository.saveAll(projects);
                    }
                }

            }
            user.setRole(roleRepository.getById(updateGlobal.getRoleId()));
        }
        if(updateGlobal.getFirstname()!=null){
            user.setFirstname(updateGlobal.getFirstname());
        }
        if(updateGlobal.getLastname()!=null){
            user.setLastname(updateGlobal.getLastname());
        }
        if(updateGlobal.getEmail()!=null){
            user.setEmail(updateGlobal.getEmail());
        }
        if(updateGlobal.getManagerId()!=null && user.getRole().getId()==1L){
            user.setManager(userRepository.getById(updateGlobal.getManagerId()));
        }
        userRepository.save(user);
        return user;
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody RegistrationRequest registerRequest) throws Exception {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setFullname(registerRequest.getLastname()+" "+registerRequest.getFirstname());
        user.setRole(roleRepository.findById(registerRequest.getRoleId()).get());
        if(registerRequest.getManagerId()!=null){
            System.out.println(registerRequest.getManagerId());
            User manager = userRepository.getById(registerRequest.getManagerId());
            user.setManager(manager);
        }else{
            System.out.println("Null\n\n");
        }
        user.setEnabled(true);
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    }

    @GetMapping("/managers")
    public List<User> getMangers(){
        List<User> managers = userRepository.findByRoleId(2L);
        return managers;
    }
    @GetMapping("/date/export/{userId}/{date}")
    public void exportToPDF(HttpServletResponse response, @PathVariable Long userId, @PathVariable String date ) throws DocumentException, IOException, DocumentException, IOException {
        response.setContentType("application/pdf");
        List<Time> timesOfUser = timeRepository.findAllByUserIdAndDateOfProject(userId,date);
        User user = userRepository.findById(userId).get();

        UserReportExporter exporter = new UserReportExporter(timesOfUser,user, date);
        exporter.export(response);

    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username){
        User user = userRepository.findByUsername(username).get();
        System.out.println(user);
        userService.deleteUser(user);
    }

    @DeleteMapping("/delete-multi")
    public void deleteUsers(@RequestBody List<String> usernames){
        for(String username:usernames){
            User user = userRepository.findByUsername(username).get();
            userService.deleteUser(user);
        }
        System.out.println("Selected Users have been deleted!!");
    }
}
