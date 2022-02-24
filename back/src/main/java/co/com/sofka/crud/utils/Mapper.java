package co.com.sofka.crud.utils;

import co.com.sofka.crud.entity.ToDo;
import co.com.sofka.crud.entity.ToDoList;
import co.com.sofka.crud.model.ToDoDTO;
import co.com.sofka.crud.model.ToDoListDTO;

public class Mapper {
    public static ToDoListDTO mapListToListDTO(ToDoList toDoList){
        ToDoListDTO toDoListDTO = new ToDoListDTO();
        toDoListDTO.setToDos(toDoList.getToDos());
        toDoListDTO.setName(toDoList.getName());
        toDoListDTO.setId(toDoList.getId());
        return toDoListDTO;
    }

    public static ToDoList mapListDTOToList(ToDoListDTO toDoListDTO){
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListDTO.getId());
        toDoList.setName(toDoListDTO.getName());
        toDoList.setToDos(toDoListDTO.getToDos());
        return toDoList;
    }

    public static ToDoDTO mapToDoToToDoDTO(ToDo toDo){
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setName(toDo.getName());
        toDoDTO.setId(toDo.getId());
        toDoDTO.setCompleted(toDo.isCompleted());
        return toDoDTO;
    }

    public static ToDo mapToDoDTOToToDo(ToDoDTO toDoDTO){
        ToDo toDo = new ToDo();
        toDo.setId(toDoDTO.getId());
        toDo.setName(toDoDTO.getName());
        toDo.setCompleted(toDoDTO.isCompleted());
        return toDo;
    }
}
