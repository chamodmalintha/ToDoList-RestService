package com.example.demoTask1;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoTask1.service.ToDoListServiceImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
public class DemoTask1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoTask1Application.class, args);
	}
	
	@Autowired
	ToDoListServiceImpl toDoListServiceImpl;

	@RequestMapping(value = "/user")
	public String user(Principal principal) {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		toDoListServiceImpl.setUser(currentPrincipalName);
		return currentPrincipalName;
	}
}
