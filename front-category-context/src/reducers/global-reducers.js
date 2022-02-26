function globalReducer(state, action) {
    switch (action.type) {
        case 'delete-category-item':
            const categoryUpDelete = state.category;
            const catListUpdate = categoryUpDelete.list.filter((item) => {
                return item.id !== action.id;
            });
            categoryUpDelete.list = catListUpdate;
            return { ...state, category: categoryUpDelete }
        case 'update-category-list':
            const categoryUpList = state.category;
            categoryUpList.list = action.list;
            return { ...state, category: categoryUpList }
        case 'add-category-item':
            const categoryUp = state.category.list;
            categoryUp.push(action.item);
            return { ...state, category: { list: categoryUp, item: {} } }
        case 'update-todo-item':
            const todoUpItem = state.todo;
            const listUpdateEdit = todoUpItem.list.map((item) => {
                if (item.id === action.item.id) {
                    return action.item;
                }
                return item;
            });
            todoUpItem.list = listUpdateEdit;
            todoUpItem.item = {};
            return { ...state, todo: todoUpItem }
        case 'delete-todo-item':
            const todoUpDelete = state.todo;
            const listUpdate = todoUpDelete.list.filter((item) => {
                return item.id !== action.id;
            });
            todoUpDelete.list = listUpdate;
            return { ...state, todo: todoUpDelete }
        case 'update-todo-list':
            const todoUpList = state.todo.list;
            action.list.forEach(element => {
                todoUpList.push(element);
            });
            return { ...state, todo: {list: todoUpList, item:{}} }
        case 'edit-todo-item':
            const todoUpEdit = state.todo;
            todoUpEdit.item = action.item;
            return { ...state, todo: todoUpEdit }
        case 'add-todo-item':
            const todoUp = state.todo.list;
            todoUp.push(action.item);
            return { ...state, todo: { list: todoUp, item: {} } }
        default:
            return state;
    }
};

export { globalReducer };