package co.com.sofka.crud.service;

import co.com.sofka.crud.entity.ToDo;
import co.com.sofka.crud.entity.ToDoList;
import co.com.sofka.crud.model.ToDoDTO;
import co.com.sofka.crud.model.ToDoListDTO;
import co.com.sofka.crud.repository.ToDoListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import co.com.sofka.crud.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
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
        log.info(toDoList.toString());
        return toDoList.orElseGet(ToDoList::new)
                .getToDos()
                .stream()
                .map(toDo -> new ToDoDTO(toDo.getId(),toDo.getName(),toDo.isCompleted(), toDoList.get().getId()))
                .collect(Collectors.toSet());
    }

    public ToDoDTO saveNewToDoByListId(Long listId, ToDoDTO toDoDTO){
        var _toDoList = repoToDoList.findById(listId);
//        if (toDoDTO.getName().isEmpty()){
//            throw new RuntimeErrorException();
//        }


//holis :3
        var toDo = Mapper.mapToDoDTOToToDo(toDoDTO);

        _toDoList.orElseGet(ToDoList::new).getToDos().add(toDo);
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
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListDTO.getId());
        toDoList.setName(toDoListDTO.getName());
        toDoList.setToDos(new HashSet<>());

        return Mapper.mapListToListDTO(repoToDoList.save(toDoList));
    }

    public Iterable<ToDo> list(){
        return repoToDo.findAll();
    }

    public ToDo save(ToDo todo){
        return repoToDo.save(todo);
    }

    public void delete(Long id){
        repoToDo.delete(get(id));
    }

    public ToDo get(Long id){
         return repoToDo.findById(id).orElseThrow();
    }

}
