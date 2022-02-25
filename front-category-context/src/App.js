import React, { useReducer, createContext } from 'react';
import CategoryList from './components/CategoryList';
import CategoryForm from './components/CategoryForm';
import { reducer4Category } from './reducers/category-reducer';

const HOST_API = "http://localhost:8080/api";

const initialState = {
  category: { list: [], item: {} }
};

const Store = createContext(initialState)

const StoreProvider = ({ children }) => {

  const [state, dispatch] = useReducer(reducer4Category, initialState);

  return <Store.Provider value={{ state, dispatch }}>
    {children}
  </Store.Provider>
}

function App() {
  return <StoreProvider>
    <h3>Category List</h3>
    <CategoryForm />
    <CategoryList />
  </StoreProvider>
}

export default App;
export { HOST_API, Store  };