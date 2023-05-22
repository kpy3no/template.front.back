import React from 'react';
import PropTypes from 'prop-types';
import AbstractEditForm from "../common/AbstractEditForm/AbstractEditForm";
import {FieldInputText} from "../common/components/fieldInputText";

export default function CityDetails(props) {
    const {id} = props.match.params;

    const newItem = {
        name: ''
    };

    const fields = () => (
        <div>
            <FieldInputText name='name' label='Наименование' required/>
        </div>
    )

    return (
        <AbstractEditForm
            module='cities'
            stateName='citiesState'
            id={id}
            newItem={newItem}
            renderFields={fields}
            onCloseRoute={`/`}
        />
    );
}

CityDetails.propTypes = {
    match: PropTypes.object.isRequired,
};