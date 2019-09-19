package com.example.demoTask1;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demoTask1.model.ToDo;
import com.example.demoTask1.repository.ToDoListRepository;
import com.example.demoTask1.service.ToDoListServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTask1ApplicationTests {

	@Autowired
	ToDoListServiceImpl toDoListServiceImpl;

	@Autowired
	ToDoListRepository toDoListRepository;

	@Before
	public void create() {

		toDoListRepository.deleteAll();

		ToDo toDoList1 = new ToDo();
		toDoList1.setId("1");
		toDoList1.setTitle("Akila");
		toDoList1.setData("Akila");

		toDoListRepository.save(toDoList1);

		ToDo toDoList2 = new ToDo();
		toDoList2.setId("2");
		toDoList2.setTitle("Akila");
		toDoList2.setData("Akila");

		toDoListRepository.save(toDoList2);
	}

	public ToDo createToDoEntity(String id, String title, String data) {
		ToDo toDoList = new ToDo();
		toDoList.setId(id);
		toDoList.setTitle(title);
		toDoList.setData(data);
		return toDoList;
	}

	@Test
	public void getToDoListsTest() {
		List<ToDo> toDoLists = toDoListRepository.findAll();
		assertEquals(toDoLists.size(), toDoListServiceImpl.getToDoList().size());
	}

	@Test
	public void getToDoListByIdTest() {
		ToDo toDoList = createToDoEntity("10", "title10", "data10");
		toDoListRepository.save(toDoList);
		assertEquals(toDoList.getId(), toDoListServiceImpl.getToDoById(toDoList.getId()).getId());
	}

	@Test
	public void createToDoListTest() {
		ToDo toDoList = createToDoEntity("11", "title11", "data11");
		toDoListRepository.save(toDoList);
		assertEquals(toDoList.getTitle(), toDoListServiceImpl.addToDo(toDoList).getTitle());
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteToDoListTest() {
		toDoListServiceImpl.removeToDo("2");
		ToDo toDoList = toDoListRepository.findById("2").get();

	}

	@Test
	public void updateToDoListTest() {
		ToDo toDoList = createToDoEntity("12", "title12", "data12");
		toDoListRepository.save(toDoList);
		assertEquals(toDoList.getTitle(), toDoListServiceImpl.updateToDo("1", toDoList).getTitle());

	}

	@After
	public void remove() {

		toDoListRepository.deleteAll();

	}

}
