package co.com.sofka.crud.service;

import co.com.sofka.crud.entity.ToDo;
import co.com.sofka.crud.entity.ToDoList;
import co.com.sofka.crud.model.ToDoDTO;
import co.com.sofka.crud.model.ToDoListDTO;
import co.com.sofka.crud.repository.ToDoListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import co.com.sofka.crud.utils.Mapper;
import co.com.sofka.crud.utils.MyBussinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToDoListService {

    @Autowired
    private TodoRepository repoToDo;

    @Autowired
    private ToDoListRepository repoToDoList;

    public Set<ToDoDTO> findToDosByListId(Long listId){
        var _toDoList = repoToDoList.findById(listId);
        return getToDosFromList(_toDoList);
    }

    private Set<ToDoDTO> getToDosFromList(Optional<ToDoList> toDoList) {
        return toDoList.orElseGet(ToDoList::new)
                .getToDos()
                .stream()
                .map(toDo -> new ToDoDTO(toDo.getId(),toDo.getName(),toDo.isCompleted(), toDoList.get().getId()))
                .collect(Collectors.toSet());
    }

    public ToDoDTO saveNewToDoByListId(Long listId, ToDoDTO toDoDTO){
        var _toDoList = repoToDoList.findById(listId);
        if (toDoDTO.getName().isEmpty()){
            throw new MyBussinessException("Invalid ToDo name. Name required.");
        }

        var _toDo = Mapper.mapToDoDTOToToDo(toDoDTO);

        _toDoList.orElseGet(ToDoList::new).getToDos().add(_toDo);
        var listUpdated = repoToDoList.save(_toDoList.get());
        var insertedToDo = listUpdated.getToDos()
                                    .stream()
                                    .max(Comparator.comparing(item -> item.getId().intValue()))
                                    .orElseThrow();
        toDoDTO.setId(insertedToDo.getId());
        toDoDTO.setGroupListId(listId);
        return toDoDTO;
    }

    public ToDoListDTO createToDoList(ToDoListDTO toDoListDTO){
        if (toDoListDTO.getName().isEmpty()){
            throw new MyBussinessException("Invalid ToDo name. Name required.");
        }
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListDTO.getId());
        toDoList.setName(toDoListDTO.getName());
        toDoList.setToDos(new HashSet<>());

        return Mapper.mapListToListDTO(repoToDoList.save(toDoList));
    }

    public ToDoDTO updateToDoByListId(Long listId, ToDoDTO toDoDTO){
        var foundToDoList = repoToDoList.findById(listId);
        if (toDoDTO.getName().isEmpty()){
            throw new MyBussinessException("Invalid ToDo name. Name required.");
        }
        var updatedToDos = editToDosInList(foundToDoList.orElseGet(ToDoList::new), toDoDTO);

        ToDoList _toDoList = new ToDoList();
        _toDoList.setId(foundToDoList.orElseGet(ToDoList::new).getId());
        _toDoList.setName(foundToDoList.orElseGet(ToDoList::new).getName());
        _toDoList.setToDos(foundToDoList.orElseGet(ToDoList::new).getToDos());
        repoToDoList.save(_toDoList);
        toDoDTO.setGroupListId(listId);
        return toDoDTO;
    }

    private Set<ToDo> editToDosInList(ToDoList foundToDoList, ToDoDTO toDoDTO){
        return foundToDoList.getToDos()
                .stream()
                .map(toDo -> {
                    if (toDo.getId().equals(toDoDTO.getId())){
                        toDo.setName(toDoDTO.getName());
                        toDo.setCompleted(toDoDTO.isCompleted());
                    }
                    return toDo;
                })
                .collect(Collectors.toSet());
    }

    public void removeToDoById(Long id){
        var toDo = repoToDo.findById(id).orElseThrow();
        repoToDo.delete(toDo);
    }

    public void removeTodoListById(Long id){
        var toDoList = repoToDoList.findById(id).orElseThrow();
        repoToDoList.delete(toDoList);
    }
}
