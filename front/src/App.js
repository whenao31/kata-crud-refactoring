import React, { useReducer, createContext } from 'react';
import List from './components/List';
import TaskForm from './components/TaskForm';
import { reducer4Task } from './reducers/task-reducer';

const HOST_API = "http://localhost:8080/api";
const initialState = {
  todo: { list: [], item: {} }
};
const Store = createContext(initialState)

const StoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer4Task, initialState);

  return <Store.Provider value={{ state, dispatch }}>
    {children}
  </Store.Provider>

}

function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <TaskForm />
    <List />
  </StoreProvider>
}

export default App;
export { HOST_API, Store  };