function reducer4Category(state, action) {
    switch (action.type) {
      case 'delete-item':
        const categoryUpDelete = state.category;
        const listUpdate = categoryUpDelete.list.filter((item) => {
          return item.id !== action.id;
        });
        categoryUpDelete.list = listUpdate;
        return { ...state, category: categoryUpDelete }
      case 'update-list':
        const categoryUpList = state.category;
        categoryUpList.list = action.list;
        return { ...state, category: categoryUpList }
      case 'add-item':
        const categoryUp = state.category.list;
        categoryUp.push(action.item);
        return { ...state, category: {list: categoryUp, item: {}} }
      default:
        return state;
    }
  }

  export { reducer4Category };