package com.example.demoTask1.service;

import java.util.List;

import com.example.demoTask1.model.ToDo;

public interface ToDoListService {

	ToDo addToDo(ToDo toDo);

	List<ToDo> getToDoList();

	ToDo getToDoById(String id);

	void removeToDo(String id);

	ToDo updateToDo(String index, ToDo jsonToDo);

	List<ToDo> getToDoByUserId();

	String getCountry();

}