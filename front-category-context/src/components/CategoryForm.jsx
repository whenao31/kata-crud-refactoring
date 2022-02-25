import React, { useContext, useRef, useState} from 'react';
import { HOST_API, Store } from '../App';


const CategoryForm = () => {
    const formRef = useRef(null);
    const { dispatch, state: { category } } = useContext(Store);
    const item = category.item;
    const [state, setState] = useState(item);
  
    const onAdd = (event) => {
      event.preventDefault();
  
      const request = {
        name: state.name,
        id: null
      };
  
  
      fetch(HOST_API + "/todolist", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((category) => {
          dispatch({ type: "add-item", item: category });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    return <form ref={formRef}>
      <input
        type="text"
        name="name"
        placeholder="¿Qué tipo de tareas para hoy?"
        defaultValue={item.name}
        onChange={(event) => {
          setState({ ...state, name: event.target.value })
        }}  ></input>
        <button onClick={onAdd}>Crear</button>
    </form>
  }

  export default CategoryForm;