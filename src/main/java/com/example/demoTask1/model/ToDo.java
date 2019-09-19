package com.example.demoTask1.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ToDo {

	@Id
	private String id;
	private Date plannedDate = new Date();
	private String title;
	private String data;
	private String userName;

	public ToDo() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public Date getDate() {
		return plannedDate;
	}

	public void setDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
