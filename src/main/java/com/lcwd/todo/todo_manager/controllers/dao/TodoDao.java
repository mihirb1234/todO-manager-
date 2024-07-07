package com.lcwd.todo.todo_manager.controllers.dao;

import com.lcwd.todo.todo_manager.helper.Helper;
import com.lcwd.todo.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    @Autowired
    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        //create table if not exists
        String createTable = "create table IF NOT EXISTS todos (id int PRIMARY KEY, title varchar(100) not null, content varchar(500), status varchar(10) not null, addedDate datetime, todoDate datetime)";
        int update = template.update(createTable);
        logger.info("TODO TABLE CREATED {}", update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save in the db
    public Todo saveTodo(Todo todo) {
        String insertQuery = "insert into todos(id, title, content, status, addedDate, todoDate) values(?,?,?,?,?,?)";
        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getToDoDate());
        logger.info("JDBC operations: {} inserted", rows);
        return todo;
    }

    //get single todo from the database
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos WHERE id = ?";
        Map<String, Object> todoData = template.queryForMap(query, id);

        Todo todo = new Todo(); //create a new object
        //put map values into the object
        todo.setId((int) todoData.get("id")); //typecast because it returns an object so we cast it to int
        todo.setTitle((String) todoData.get("title"));
        todo.setContent((String) todoData.get("content"));
        todo.setStatus((String) todoData.get("status"));
        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));
        return todo;
    }

    //get all todos from the database
    public List<Todo> getAllTodos() {
        String query = "select * from todos";
        List<Map<String, Object>> maps = template.queryForList(query);
        List<Todo> todos = maps.stream().map((map) -> {
            Todo todo = new Todo();
            todo.setId((int) map.get("id")); //typecast because it returns an object so we cast it to int
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return todo;
        }).collect(Collectors.toList());
        return todos;
    }


    //update todo
    public Todo updateTodo(int id,Todo newTodo){
        String query="update todos set title =? , content=? ,status=?, addedDate=?, todoDate=?";

        int update=template.update(query,newTodo.getId(),newTodo.getTitle(),newTodo.getContent(),newTodo.getAddedDate(),newTodo.getToDoDate());
        logger.info("updated value: {}",update);
        newTodo.setId(id);
        return newTodo;
    }

    //delete from database
    public void deleteTodo(int id){
        String query="delete from todos WHERE id=?";
        int update=template.update(query,id);
        logger.info("DELETED {} ",update);

    }

}
