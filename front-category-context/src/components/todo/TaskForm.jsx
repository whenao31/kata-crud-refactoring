import React, { useContext, useRef, useState} from 'react';
import { HOST_API, Store } from '../../App';


const TaskForm = ({listId, todo}) => {
    const formRef = useRef(null);
    const { dispatch} = useContext(Store);
    const item = todo.item["groupListId"] === listId ? todo.item : {};
    const [state, setState] = useState(item);
    const groupListId = listId;
  
    const onAdd = (event) => {
      event.preventDefault();
      
      const request = {
        name: state.name,
        id: null,
        completed: false,
        groupListId: groupListId
      };
  
      if(request.name !== undefined && request.name !== "" && groupListId !== null && request.name.trim().length > 3){
        fetch(HOST_API + "/todo/" + groupListId, {
          method: "POST",
          body: JSON.stringify(request),
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(response => response.json())
          .then((todo) => {
            dispatch({ type: "add-todo-item", item: todo });
            setState({ name: "" });
            formRef.current.reset();
          });
      }else{
        formRef.current.reset();
        alert("Nombre Invalido. Requerido y debe tener mas de 3 caracteres");
      }
      
    }
  
    const onEdit = (event) => {
      event.preventDefault();
  
      const request = {
        name: state.name,
        id: item.id,
        isCompleted: item.isCompleted,
        groupListId: item.groupListId
      };
  
      if(request.name !== undefined && request.name !== "" && groupListId !== null  && request.name.trim().length > 3){
        fetch(HOST_API + "/todo/" + item.groupListId, {
          method: "PUT",
          body: JSON.stringify(request),
          headers: {
            'Content-Type': 'application/json'
          }
        })
          .then(response => response.json())
          .then((todo) => {
            dispatch({ type: "update-todo-item", item: todo });
            setState({ name: "" });
            formRef.current.reset();
          });
      }else{
        formRef.current.reset();
        alert("Nombre Invalido. Requerido y debe tener mas de 3 caracteres");
      }
    }
  
    return <form ref={formRef}>
      <input
        type="text"
        name="name"
        placeholder="¿Qué piensas hacer hoy?"
        defaultValue={item.name}
        onChange={(event) => {
          setState({ ...state, name: event.target.value })
        }}  ></input>
      {item.id && <button className="btn btn-outline-warning btn-sm rounded" onClick={onEdit}>Actualizar</button>}
      {!item.id && <button className="btn btn-outline-primary btn-sm rounded" onClick={onAdd}>Crear</button>}
    </form>
  }

  export default TaskForm;