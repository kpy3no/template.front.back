import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import EnhancedTable from "../common/components/table/table";
import * as api from '../../api/cruds-api';
import Loader from "../Loader";


const headCells = [
    { property: 'id', disablePadding: true, label: 'ID' },
    { property: 'name', numeric: true, disablePadding: false, label: 'Имя' }
];

// eslint-disable-next-line no-unused-vars
export default function CityList() {
    const history = useHistory();
    const dispatch = useDispatch();

    const {list, isLoading} = useSelector(state => state.citiesState);

    useEffect(() => {
        api.getList(dispatch, 'cities')
    }, []);

    if (isLoading) {
        return (
            <Loader isLoading = {isLoading}/>
        );
    }

    const handleDeleteItem = (selected) => api.deleteItems(dispatch, 'cities', selected).then(() => {
        api.getList(dispatch, 'cities')
    }).catch(error => {
        alert(error)
    });

    return (
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
    );
}
