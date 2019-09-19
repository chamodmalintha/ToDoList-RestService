package com.example.demoTask1.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demoTask1.calendar.CalendarQuickstart;
import com.example.demoTask1.model.ToDo;
import com.example.demoTask1.repository.ToDoListRepository;

@Service
public class ToDoListServiceImpl implements ToDoListService {

	@Autowired
	private ToDoListRepository toDoListRepository;
	String userName;

	public void setUser(String userName) {
		this.userName = userName;
	}

	@Override
	public ToDo addToDo(ToDo toDo) {
		toDo.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		try {
			CalendarQuickstart.calendarOperations(toDo);
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toDoListRepository.save(toDo);
		return toDo;
	}

	@Override
	public List<ToDo> getToDoList() {
		return toDoListRepository.findAll();
	}

	@Override
	public List<ToDo> getToDoByUserId() {
		return toDoListRepository.findByUserName(userName);
	}

	@Override
	public ToDo getToDoById(String toDoId) {
		return toDoListRepository.findById(toDoId).get();
	}

	@Override
	public void removeToDo(String toDoId) {
		try {
			CalendarQuickstart.deleteEvent(toDoId);
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toDoListRepository.deleteById(toDoId);
	}

	@Override
	public ToDo updateToDo(String toDoId, ToDo jsonToDo) {
		ToDo toDo = toDoListRepository.findById(toDoId).get();
		toDo.setTitle(jsonToDo.getTitle());
		toDo.setData(jsonToDo.getData());

		try {
			CalendarQuickstart.updateEvent(toDo);
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toDoListRepository.save(toDo);
		return toDo;
	}
	

	@Value("${service.url}")
	private String serviceUrl;

	public String getCountry() {
		 RestTemplate restTemplate = new RestTemplate();
		 String result = restTemplate.getForObject(serviceUrl, String.class);

		return result;
	}

}