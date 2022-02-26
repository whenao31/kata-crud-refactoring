import React, { useContext, useEffect } from 'react';
import { Store, HOST_API } from '../../App';


const TaskList = ({listId, todo}) => {
    const { dispatch } = useContext(Store);
    let currentList = todo.list.filter(item =>(item.groupListId === listId));
  
    useEffect(() => {
      fetch(HOST_API + "/todos/" + listId)
        .then(response => response.json())
        .then((list) => {
          if(!list.length){ list = currentList};
          dispatch({ type: "update-todo-list", list: list });
        })
    }, [dispatch, listId]);
  
  
    const onDelete = (id) => {
      fetch(HOST_API + "/todo/" + id, {
        method: "DELETE"
      }).then((list) => {
        dispatch({ type: "delete-todo-item", id })
      })
    };
  
    const onEdit = (todo) => {
      dispatch({ type: "edit-todo-item", item: todo })
    };
  
    const onChange = (event, todo) => {
      const request = {
        name: todo.name,
        id: todo.id,
        completed: event.target.checked,
        groupListId: todo.groupListId
      };
      fetch(HOST_API + "/todo/" + todo.groupListId, {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "update-todo-item", item: todo });
        });
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div >
      <table className='table table-striped'>
        <thead>
          <tr>
            {/* <td>ID</td> */}
            <td>Tarea</td>
            <td>¿Completado?</td>
          </tr>
        </thead>
        <tbody>
          {
          currentList.filter(item => item.id !== null).map((todo) => {
            return <tr key={todo.id} style={todo.completed ? decorationDone : {}}>
              {/* <td>{todo.id}</td> */}
              <td>{todo.name}</td>
              <td><input type="checkbox" defaultChecked={todo.completed} onChange={(event) => onChange(event, todo)}></input></td>
              <td><button className="btn btn-outline-danger btn-sm rounded" onClick={() => onDelete(todo.id)}>Eliminar</button></td>
              <td><button className="btn btn-outline-warning btn-sm rounded" disabled= {todo.completed} onClick={() => onEdit(todo)}>Editar</button></td>
            </tr>
          })}
        </tbody>
      </table>
    </div>
}

export default TaskList;