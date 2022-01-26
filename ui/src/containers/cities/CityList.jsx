import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import EnhancedTable from "../common/components/table/table";
import * as api from '../../api/cruds-api';
import Loader from "../Loader";
import TablePagination from "@material-ui/core/TablePagination";


const headCells = [
    { property: 'id', disablePadding: true, label: 'ID' },
    { property: 'name', numeric: true, disablePadding: false, label: 'Имя' }
];

// eslint-disable-next-line no-unused-vars
export default function CityList() {
    const history = useHistory();
    const dispatch = useDispatch();

    const {pagination, list, isLoading, totalElements} = useSelector(state => state.citiesState);

    const getFilteredList = newPagination => api.getFilteredList(dispatch, 'cities', null, null, newPagination);

    useEffect(() => getFilteredList({...pagination, page: 0}), []);

    if (isLoading) {
        return (
            <Loader isLoading = {isLoading}/>
        );
    }

    const handleDeleteItem = (selected) => api.deleteItems(dispatch, 'cities', selected).then(() => {
        getFilteredList({...pagination, page: 0})
    }).catch(error => {
        alert(error)
    });

    const handleChangePage = (event, newPage) => {
        getFilteredList({...pagination, page: newPage})
    };

    const handleChangeRowsPerPage = (event) => {
        const pageSize = parseInt(event.target.value, 10)
        getFilteredList({page: 0, size: pageSize})
    };

    return (
        <div>
            <EnhancedTable
                dataMap={headCells}
                toolbar={{
                    toolbarTitle: 'Города',
                    delete: (selected) => handleDeleteItem(selected),
                    edit: (selected) => history.push(`/cities/${selected}`),
                    add: () => history.push(`/cities/new`)
                }}
                rows={list}
            />
            <TablePagination
                rowsPerPageOptions={[5, 10, 25, 50]}
                component="div"
                count={totalElements}
                rowsPerPage={pagination.size}
                page={pagination.page}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
            />
        </div>
    );
}
