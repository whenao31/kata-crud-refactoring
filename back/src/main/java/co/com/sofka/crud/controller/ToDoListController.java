package co.com.sofka.crud.controller;

import co.com.sofka.crud.entity.ToDo;
import co.com.sofka.crud.model.ToDoDTO;
import co.com.sofka.crud.model.ToDoListDTO;
import co.com.sofka.crud.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ToDoListController {

    @Autowired
    private ToDoListService serviceListToDo;

    @PostMapping("/todolist")
    public ToDoListDTO saveNewTodoList(@RequestBody ToDoListDTO toDoListDTO){
        return serviceListToDo.createToDoList(toDoListDTO);
    }

    @GetMapping("/todos/{listId}")
    public Set<ToDoDTO> findToDoListById(@PathVariable("listId") Long listId){
        return serviceListToDo.findToDosByListId(listId);
    }

    @PostMapping("/todo/{listId}")
    public ToDoDTO saveNewTodoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoDTO toDoDTO){
        return serviceListToDo.saveNewToDoByListId(listId, toDoDTO);
    }

    @PutMapping("/todo/{listId}")
    public ToDoDTO updateTodoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoDTO toDoDTO){
        return serviceListToDo.updateToDoByListId(listId, toDoDTO);
    }

    @DeleteMapping("/todo/{todoId}")
    public void removeToDoById(@PathVariable("todoId") Long toDoId){
        serviceListToDo.removeToDoById(toDoId);
    }

    @DeleteMapping("/todos/{listId}")
    public void removeToDoListById(@PathVariable("listId") Long listId){
        serviceListToDo.removeTodoListById(listId);
    }

}
