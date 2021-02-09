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

export const getFilteredList = (dispatch, module, filter, sort, pagination) => {

    const suffix = getSuffix(module);

    let params = {};

    if (pagination && pagination.size) {
        params.size = pagination.size;
        params.page = _.get(pagination, 'page', 0)
    }

    if (sort) {
        params = {...sort, ...params};
    }

    const config = {
        params
    };

    dispatch({
        type: `GET_${suffix}_LIST_START`
    });

    axios.post(`/${module}/filter`, filter || {}, config)
        .then(response => {
            dispatch({
                type: `GET_${suffix}_LIST`,
                totalPages: _.get(response, 'data.totalPages', 0),
                list: response.data.content,
                pagination: {
                    size: _.get(response, 'data.size', 5),
                    page: _.get(response, 'data.number', 0)
                }
            });
        }).catch(axiosError => showError(dispatch, axiosError));
};

export const getCount = (dispatch, module, filter, errorCallback) => {

    const suffix = getSuffix(module);

    dispatch({
        type: `GET_COUNT_${suffix}_START`
    });

    axios.post(`/${module}/count`, filter || {})
        .then(response => {
            dispatch({
                type: `GET_COUNT_${suffix}`,
                itemsCount: response.data
            })
        }).catch(axiosError => _.isFunction(errorCallback) && errorCallback() || showError(dispatch, axiosError));
};

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