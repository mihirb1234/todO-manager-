package com.lcwd.todo.todo_manager.controllers;


import com.lcwd.todo.todo_manager.models.Todo;
import com.lcwd.todo.todo_manager.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;
import java.util.List;
import java.util.Random;

//import java.util.logging.Logger;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger=LoggerFactory.getLogger(TodoController.class);
    //create

    @Autowired
    private TodoService todoService;

    Random random=new Random();


    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        String str=null;
        logger.info("{}",str.length());
        //create todo
        int id=random.nextInt(9999999);
        todo.setId(id);


        //create date with system defaullt current date
        Date currentDate=new Date();

        logger.info("current date: {}",currentDate);

        todo.setAddedDate(currentDate);



        logger.info("create todo");

        //call service to create todo
        Todo todo1=todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    //get all todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo>allTodos=todoService.getAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo>getSingleTodoHandler(@PathVariable int todoId){
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> getUpdatedTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId){
        Todo todo=todoService.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    //delete from db
    @DeleteMapping("{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo succesfully deleted");


    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("null pointer exception generated");
////        return "null pointer exception generated"+ ex.getMessage();
//
//        return new ResponseEntity<>("null pointer exception generated"+ex.getMessage(), HttpServerErrorException.InternalServerError);
//    }


}
