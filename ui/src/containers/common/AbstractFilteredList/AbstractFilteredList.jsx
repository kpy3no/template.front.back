import _ from 'lodash';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';
import {useHistory} from 'react-router-dom';
import {useDispatch, useSelector} from 'react-redux';

import * as api from '../../../api/cruds-api';
import style from './style.css'
import Modal from "@material-ui/core/Modal";

export default function AbstractFilteredList(props) {

    const dispatch = useDispatch();
    const history = useHistory();

    const [filter, setFilter] = useState({});
    const [sort, setSort] = useState({});
    const [deleteItem, setDeleteItem] = useState(null);

    const {pagination, list, isLoading, totalPages} = useSelector(state => props.stateName ? state[`${props.stateName}`] : state[`${_.camelCase(props.module)}State`]);
    const {data, refresh} = useSelector(state => state.error);

    const showApiError = error => api.showError(dispatch, error);
    const getFilteredList = newPagination => api.getFilteredList(dispatch, props.module, props.staticFilter ?
        _.merge({}, props.staticFilter, filter) : filter, sort, newPagination);

    useEffect(() => getFilteredList({...pagination, page: 0}), [filter, sort]);

    const links = [];
    const linksConfig = props.table.links || {};
    _.each(linksConfig.extras || [], link => links.push(link));
    if (linksConfig.delete) {
        links.push({
            callback: (id, item) => setDeleteItem(item),
            title: 'Удалить'
        });
    }

    const handleAddItem = () => props.toolbar && history.push(props.toolbar.addRoute);

    const handleDeleteItem = () => api.deleteItem(dispatch, props.module, deleteItem.id).then(() => {
        setDeleteItem(null);
        props.table.links && props.table.links.delete
            && _.isFunction(props.table.links.delete.deleteCallback) && props.table.links.delete.deleteCallback();
        getFilteredList(pagination);
    }).catch(error => {
        setDeleteItem(null);
        showApiError(error);
    });

    // const renderDeleteConfirmationModal = () => (
    //     <SimpleModal
    //         title={linksConfig.delete.title}
    //         cancel={() => setDeleteItem(null)}
    //         success={handleDeleteItem}
    //         successText='Удалить'
    //     >
    //         <h4 className={style.deleteBody}>{linksConfig.delete.confirmationMessage(deleteItem)}</h4>
    //     </SimpleModal>
    // );

    function renderExportErrorModal() {
        return (
            <Modal
                open
                onClose={() => {
                    dispatch({type: `CLEAR_ERROR`});
                }}
            >
                <div>
                    {data && data.errors && data.errors.map((error, idx) => (<li key={idx}>{error}</li>))}
                </div>
            </Modal>
        );
    }

    return (
        <div>
            {data && data.errors && renderExportErrorModal()}
            <Table
                dataMap={props.table.dataMap}
                setFilter={setFilter}
                links={links}
                sortItems={setSort}
                items={list}
                isLoading = {isLoading}
                variableColumns
                defaultColumns={props.table.defaultColumns}
                filterOptions={props.table.filterOptions}
                validateFilters={props.table.validateFilters}
            />
            <Pagination changePagination={getFilteredList} totalPages={totalPages} pagination={pagination} />
            {linksConfig.delete && deleteItem && renderDeleteConfirmationModal()}
        </div>
    );
}

AbstractFilteredList.propTypes = {
    module: PropTypes.string.isRequired,
    stateName: PropTypes.string,
    toolbar: PropTypes.shape({
        title: PropTypes.string.isRequired,
        addRoute: PropTypes.string.isRequired,
        mode: PropTypes.string,
        extraButtons: PropTypes.arrayOf(PropTypes.shape({
            name: PropTypes.string.isRequired,
            onClick: PropTypes.func.isRequired,
            icon: PropTypes.string
        })),
    }),
    table: PropTypes.shape({
        dataMap: PropTypes.array.isRequired,
        defaultColumns: PropTypes.arrayOf(PropTypes.string),
        filterOptions: PropTypes.object,
        validateFilters: PropTypes.func,
        links: PropTypes.shape({
            delete: PropTypes.shape({
                title: PropTypes.string.isRequired,
                confirmationMessage: PropTypes.func.isRequired,
                deleteCallback: PropTypes.func
            }),
            extras: PropTypes.arrayOf(PropTypes.object),
        }),
    }).isRequired,
    texts: PropTypes.shape({
        toolbarTitle: PropTypes.string.isRequired,
        deleteModalTitle: PropTypes.string.isRequired,
        renderDeleteConfirmationMessage: PropTypes.func.isRequired,
    }),
    staticFilter: PropTypes.object
};
