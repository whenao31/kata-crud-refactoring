import React, { useReducer, createContext } from 'react';
import CategoryList from './components/category/CategoryList';
import CategoryForm from './components/category/CategoryForm';
// import { reducer4Category } from './reducers/category-reducer';
import { globalReducer } from './reducers/global-reducers';

const HOST_API = "http://localhost:8080/api";

const initialState = {
  category: { list: [], item: {} },
  todo: { list: [], item: {} }
};

const Store = createContext(initialState)

const StoreProvider = ({ children }) => {

  const [state, dispatch] = useReducer(globalReducer, initialState);

  return <Store.Provider value={{ state, dispatch }}>
    {children}
  </Store.Provider>
}

function App() {
  return <StoreProvider>
    <div className='container'>
      <h3>Tareas por Categorias</h3>
    </div>
    <CategoryForm />
    <CategoryList />
  </StoreProvider>
}

export default App;
export { HOST_API, Store  };