package com.lcwd.todo.todo_manager.services;

import com.lcwd.todo.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//import java.util.logging.Logger;

@Service
public class TodoService {

    Logger logger= LoggerFactory.getLogger(TodoService.class);

    List<Todo>todos=new ArrayList<>();



    public Todo createTodo(Todo todo){
        //create todo
        todos.add(todo);
        logger.info("Todos {} ",this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo=todos.stream().filter(t->todoId==t.getId()).findAny().get();
        logger.info("TODO : {}",todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo) {

        List<Todo>bewUpdatedList=todos.stream().map(t->{
            if(t.getId()==todoId){
                //perform update
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());

                return t;
            } else{
                return t;
            }
        }).collect(Collectors.toList());
        todos=bewUpdatedList;
        todo.setId(todoId);
        return todo;

    }

    public void deleteTodo(int todoId) {
        logger.info("Deleting todo");
        List<Todo>newList=todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
        todos=newList;
    }
}
