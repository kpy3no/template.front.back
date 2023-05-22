import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import {Form} from 'react-final-form';

import * as api from '../../../api/cruds-api';
import style from './style.css'
import Loader from "../../Loader";
import Button from '@material-ui/core/Button';
import Modal from "@material-ui/core/Modal";

export default function AbstractEditForm({id, module, stateName, newItem, renderFields, onCloseRoute}) {

    const isNew = id === 'new';
    const {data} = useSelector(state => state.error);

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {
        if (id && !isNew) {
            api.getItem(dispatch, module, id);
        }
    }, []);

    const {isLoadingItem, item} = useSelector(state => stateName ? state[`${stateName}`] : state[`${module}State`]);

    if (isLoadingItem) {
        return <Loader size="sm"/>;
    }

    const onClose = () => {
        dispatch({type: `CLEAR_${module.toUpperCase()}`});
        history.push(onCloseRoute || `/${module}`);
    }

    const onSubmit = (values) => {
        api.save(dispatch, module, values, onClose);
    };

    function renderErrorModal() {
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

    const initialValues = isNew ? newItem : {...item};

    const FieldsComponent = renderFields;
    return (
        <div className={style.fixedWrapper}>
            {data && data.errors && renderErrorModal()}
            <Form
                initialValues={initialValues}
                onSubmit={onSubmit}
                render={({handleSubmit, form, values}) => (
                    <form onSubmit={handleSubmit}>
                        <FieldsComponent item={values} form={form}/>
                        <Button onClick={handleSubmit}>{isNew ? 'Создать' : 'Сохранить'}</Button>
                        <Button onClick={onClose}>Отменить</Button>
                    </form>
                )}
            />
        </div>
    )
}

AbstractEditForm.propTypes = {
    id: PropTypes.string.isRequired,
    module: PropTypes.string.isRequired,
    stateName: PropTypes.string,
    newItem: PropTypes.object.isRequired,
    renderFields: PropTypes.func.isRequired,
    onCloseRoute: PropTypes.string,
    validateItems: PropTypes.func
};
