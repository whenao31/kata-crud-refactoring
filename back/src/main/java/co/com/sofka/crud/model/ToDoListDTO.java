package co.com.sofka.crud.model;

import co.com.sofka.crud.entity.ToDo;

import java.util.Set;

public class ToDoListDTO {
    private Long id;
    private String name;
    private Set<ToDo> toDos;

    public ToDoListDTO() {
    }

    public ToDoListDTO(Long id, String name, Set<ToDo> toDos) {
        this.id = id;
        this.name = name;
        this.toDos = toDos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ToDo> getToDos() {
        return toDos;
    }

    public void setToDos(Set<ToDo> toDos) {
        this.toDos = toDos;
    }
}
