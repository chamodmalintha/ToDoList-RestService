package com.example.demoTask1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoTask1.model.ToDo;
import com.example.demoTask1.service.ToDoListService;

@RestController
@RequestMapping(value = "/toDoList")
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;
	SecurityContext security;

	@PostMapping("/post")
	public ToDo postToDoList(@RequestBody ToDo data) {
		return toDoListService.addToDo(data);
	}

	@GetMapping("/")
	public List<ToDo> getToDoList() {
		return toDoListService.getToDoList();
	}

	@GetMapping("/user/getByUsername")
	public List<ToDo> getToDoListByUserId() {
		return toDoListService.getToDoByUserId();
	}

	@GetMapping("/{id}")
	public ToDo getSpecificToDoList(@PathVariable String id) {
		return toDoListService.getToDoById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteToDoList(@PathVariable String id) {
		toDoListService.removeToDo(id);
	}

	@PutMapping("/{index}")
	public ToDo putToDoList(@PathVariable String index, @RequestBody ToDo data) {
		return toDoListService.updateToDo(index, data);
	}

	@GetMapping("/country")
	public String getCountryList() {

		return toDoListService.getCountry();
	}

}