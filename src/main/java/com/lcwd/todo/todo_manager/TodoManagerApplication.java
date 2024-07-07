package com.lcwd.todo.todo_manager;

import com.lcwd.todo.todo_manager.controllers.dao.TodoDao;
import com.lcwd.todo.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);

	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("application started");
//
//		JdbcTemplate template =todoDao.getTemplate();
//		logger.info("Template object : {} ",template);

//		Todo todo=new Todo();
//		todo.setId(233);
//		todo.setTitle("this is testing spring jdbc");
//		todo.setContent("wow its working");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//
//		//save the data
//		todoDao.saveTodo(todo);

		//get a single todo
//		Todo todo=todoDao.getTodo(233);
//		logger.info("TODO: {}",todo);


		//get all todos
//
//		List<Todo> todos=todoDao.getAllTodos();
//		logger.info("all todos: {} ",todos);


		//DELETE todos
		todoDao.deleteTodo(id);

		logger("Deleted from the database",todo);


	}
}
