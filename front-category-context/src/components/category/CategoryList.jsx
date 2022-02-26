import React, { useContext, useEffect } from 'react';
import { Store, HOST_API } from '../../App';
import TaskForm from '../todo/TaskForm';
import TaskList from '../todo/TaskList';

const CategoryList = () => {
  const { dispatch, state: { category, todo } } = useContext(Store);
  const currentList = category.list;

  useEffect(() => {
    fetch(HOST_API + "/todolists")
      .then(response => response.json())
      .then((list) => {
        dispatch({ type: "update-category-list", list })
      })
  }, [dispatch]);


  const onDelete = (id) => {
    fetch(HOST_API + "/todolist/" + id, {
      method: "DELETE"
    }).then((list) => {
      dispatch({ type: "delete-category-item", id })
    })
  };

  return <div>{
    currentList.map((category) => {
      return <div key={category.id} className='container mt-3'>
        <fieldset className="border rounded p-2 form-group">
          <legend className="float-none w-auto">
            {category.name.toUpperCase()}
            <button className="btn btn-outline-danger btn-sm rounded" onClick={() => onDelete(category.id)}>Eliminar</button>
          </legend>
          <TaskForm listId={category.id} todo={todo} />
          <TaskList listId={category.id} todo={todo} />
        </fieldset>
      </div>
    })
  }
  </div>
}
export default CategoryList;