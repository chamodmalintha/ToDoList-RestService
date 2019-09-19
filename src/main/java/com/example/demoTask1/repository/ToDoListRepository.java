package com.example.demoTask1.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demoTask1.model.ToDo;


@Repository
public interface ToDoListRepository extends MongoRepository<ToDo, String> {
	List<ToDo> findByUserName(String userName);
}