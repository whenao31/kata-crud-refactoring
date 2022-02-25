import React, { useContext, useEffect } from 'react';
import { Store, HOST_API } from '../App';


const CategoryList = () => {
    const { dispatch, state: { category } } = useContext(Store);
    const currentList = category.list;
  
    useEffect(() => {
      fetch(HOST_API + "/todolists")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (id) => {
      fetch(HOST_API + "/todolist/" + id, {
        method: "DELETE"
      }).then((list) => {
        dispatch({ type: "delete-item", id })
      })
    };
  
    return <div>
      <table >
        <tbody>
          {currentList.map((category) => {
            return <tr key={category.id} >
              {/* <td>{category.id}</td> */}
              <td>
                <fieldset>
                  <legend>
                    {category.name}
                    <button onClick={() => onDelete(category.id)}>Eliminar</button>
                  </legend>
                </fieldset>
              </td>
            </tr>
          })}
        </tbody>
      </table>
    </div>
}

export default CategoryList;