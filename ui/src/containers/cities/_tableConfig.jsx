import React from 'react';


export const dataMap = [
    {
        'label': 'ID',
        'id': 'id',
        'sortable': true,
        'numeric': true
    },
    {
        'label': 'Наименование',
        'id': 'name',
        'sortable': true,
        'isTitle': true,
        'numeric': false,
        'onClick': (id) => `#/personal-params/${id}`,
        // 'validationArray': [validation.required, validation.maxLength(100), validation.codeValidation]
    }
];
