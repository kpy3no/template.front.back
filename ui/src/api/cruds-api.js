import _ from "lodash";
import axios from './axios';

export const showError = (dispatch, axiosError) => {
    dispatch({
        type: `ERROR`,
        payload: _.get(axiosError, 'response.data',
            {errors: [_.get(axiosError, 'message', 'Что-то пошло не так')]}
        )
    });

    return Promise.reject(axiosError);
};

const getSuffix = (module) => module.toUpperCase().split('/')[0];

export const getList = (dispatch, module) => {

    const suffix = getSuffix(module);

    dispatch({
        type: `GET_${suffix}_LIST_START`
    });

    axios.get(`/${module}`).then(response => {
        dispatch({
            type: `GET_${suffix}_LIST`,
            list: response.data
        });
    }).catch(axiosError => showError(dispatch, axiosError));
};

export const getItem = (dispatch, module, id) => {

    dispatch({
        type: `GET_${module.toUpperCase()}_START`
    });

    axios.get(`/${module}/${id}`).then(response => dispatch({
        type: `GET_${module.toUpperCase()}`,
        item: response.data
    })).catch(axiosError => showError(dispatch, axiosError));
};

export const send = (dispatch, module, id) => {
    dispatch({
        type: `GET_${module.toUpperCase()}_START`
    });

    return axios.post(`/${module}/send`, {id: id})
};

export const save = (dispatch, module, data, successCallback, errorCallback) => {
    dispatch({
        type: `SAVE_${module.toUpperCase()}_START`
    });

    return axios.post(`/${module}`, data)
        .then(res => {
            dispatch({
                type: `SAVE_${module.toUpperCase()}`,
                item: res.data
            });
            _.isFunction(successCallback) && successCallback(res.data.id);
        })
        .catch(axiosError => {
            dispatch({type: `SAVE_${module.toUpperCase()}_ERROR`});
            _.isFunction(errorCallback) && errorCallback() || showError(dispatch, axiosError);
        });
};

export const deleteItem = (dispatch, module, id) => axios.delete(`/${module}/${id}`);
export const deleteItems = (dispatch, module, ids) => axios.delete(`/${module}`, {
    data: {
        ids: ids
    }
});