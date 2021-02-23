const initialState = {
    isLoading: false,
    isLoadingItem: false,
    isLoadingCount: false,
    list: [],
    item: {},
    totalPages: 0,
    totalElements: 0,
    pagination: {
        page: 0,
        size: 5
    },
    itemsCount: 0
};

export default entityType =>
    (state = initialState, action) => {
        const entity = entityType.toUpperCase();
        switch (action.type) {
            case `GET_${entity}_LIST_START`:
                return {
                    ...state,
                    list: null,
                    isLoading: true
                };

            case `GET_${entity}_LIST`:
                return {
                    ...state,
                    list: action.list,
                    isLoading: false,
                    totalPages: action.totalPages,
                    totalElements: action.totalElements,
                    pagination: action.pagination
                };

            case `GET_${entity}_START`:
                return {
                    ...state,
                    isLoadingItem: true
                };

            case `GET_${entity}`:
                return {
                    ...state,
                    isLoadingItem: false,
                    item: action.item
                };

            case `GET_COUNT_${entity}_START`:
                return {
                    ...state,
                    isLoadingCount: true
                };

            case `GET_COUNT_${entity}`:
                return {
                    ...state,
                    isLoadingCount: false,
                    itemsCount: action.itemsCount
                };

            case `SAVE_${entity}`:
                return {
                    ...state,
                    isLoadingItem: false,
                    item: action.item
                };

            case `SAVE_${entity}_ERROR`:
                return {
                    ...state,
                    isLoadingItem: false
                };

            case `CLEAR_${entity}`:
                return {
                    ...state,
                    item: {}
                };

            case `ITEM_ACTION_${entity}`:
                return {
                    ...state,
                    list: state.list.map(item =>
                        item.id === action.item.id ? action.item : item)
                };

            default:
                return state;
        }
    }
