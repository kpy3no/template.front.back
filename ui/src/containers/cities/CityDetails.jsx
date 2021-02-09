import React from 'react';
import PropTypes from 'prop-types';
// import {FieldInputText} from "../common/components/fieldInputText";
import AbstractEditForm from "../common/AbstractEditForm/AbstractEditForm";
import TextField from "@material-ui/core/TextField";
import {Field} from "react-final-form";
// import {InputText} from "../common/components/inputText";
import {FieldInputText} from "../common/components/fieldInputText";

export default function CityDetails(props) {
    const {id} = props.match.params;

    const newItem = {
        name: 'Novosibirsk'
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
            onCloseRoute={`/cities`}
        />
    );
}

CityDetails.propTypes = {
    match: PropTypes.object.isRequired,
};