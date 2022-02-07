package com.example.Agenda_C;

import com.example.Agenda_C.domain.Project;
import com.example.Agenda_C.domain.Role;
import com.example.Agenda_C.domain.User;
import com.example.Agenda_C.repository.ProjectRepository;
import com.example.Agenda_C.repository.RoleRepository;
import com.example.Agenda_C.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
@OpenAPIDefinition
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AgendaCApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaCApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("*"));//("http://localhost:4200"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

/*
	@Bean
	public ApplicationRunner initializer(RoleRepository repository,
										 UserRepository userRepository,
										 ProjectRepository projectRepository) {
		return args -> {repository.saveAll(Arrays.asList(
				new Role(1L,"EMPLOYEE"),
				new Role(2L,"MANAGER"),
				new Role(3L,"ADMIN")
		));
			userRepository.saveAll(Arrays.asList(
					new User("usernameOne",
							"firstnameOne",
							"lastnameOne",
							"password",
							"userOne@gmail.com",
							repository.getById(1L)),
					new User("usernameTwo",
							"firstnameTwo",
							"lastnameTwo",
							"password",
							"userTwo@gmail.com",
							repository.getById(2L)),
			        new User("usernameThree",
					         "firstnameThree",
							 "lastnameThree",
					         "password",
					          "userThree@gmail.com",
					         repository.getById(3L))
					));
			projectRepository.save(
					new Project("ProjectOne",
							"DescriptionOne",
							userRepository.findByUsername("usernameTwo").get()));
		};
	}

 */
}
