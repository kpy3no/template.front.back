import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import EnhancedTable from "../common/components/table/table";
import * as api from '../../api/cruds-api';
import Loader from "../Loader";
import {send} from "../../api/cruds-api";


const headCells = [
    { property: 'id', disablePadding: true, label: 'ID' },
    { property: 'organization', numeric: true, disablePadding: false, label: 'Организация' },
    { property: 'status', numeric: true, disablePadding: false, label: 'Статус' }
];

// eslint-disable-next-line no-unused-vars
export default function CityList() {
    const history = useHistory();
    const dispatch = useDispatch();

    const {list, isLoading} = useSelector(state => state.citiesState);

    const getList = () => api.getList(dispatch, 'cities');

    useEffect(() => getList(), []);

    if (isLoading) {
        return (
            <Loader isLoading = {isLoading}/>
        );
    }

    const handleDeleteItem = (selected) => api.deleteItems(dispatch, 'cities', selected).then(() => {
        getList()
    }).catch(error => {
        alert(error)
    });

    const handleSendItem = (selected) => api.send(dispatch, 'cities', selected[0]).then(() => {
        getList()
    }).catch(error => {
        getList()
    });

    return (
        <div>
            <EnhancedTable
                dataMap={headCells}
                toolbar={{
                    toolbarTitle: 'Города.',
                    delete: (selected) => handleDeleteItem(selected),
                    send: (selected) => handleSendItem(selected),
                    add: () => history.push(`/cities/new`)
                }}
                rows={list}
            />
        </div>
    );
}
