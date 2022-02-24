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

    @GetMapping(value = "api/todos")
    public Iterable<ToDo> list(){
        return serviceListToDo.list();
    }
    
    @PostMapping(value = "api/todo")
    public ToDo save(@RequestBody ToDo todo){
        return serviceListToDo.save(todo);
    }

    @PutMapping(value = "api/todo")
    public ToDo update(@RequestBody ToDo todo){
        if(todo.getId() != null){
            return serviceListToDo.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        serviceListToDo.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public ToDo get(@PathVariable("id") Long id){
        return serviceListToDo.get(id);
    }

}
