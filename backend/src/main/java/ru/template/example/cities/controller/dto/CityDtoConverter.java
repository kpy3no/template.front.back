package ru.template.example.cities.controller.dto;

import ru.template.example.cities.model.City;
import ru.template.example.cities.model.Status;

public final class CityDtoConverter {

    public static DocumentDto toDto(City model) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(model.getId());
        documentDto.setOrganization(model.getName());
        documentDto.setName(model.getName());
        documentDto.setStatus(CodeName.of(model.getStatus().name(), model.getStatus().getName()));
        return documentDto;
    }

    public static City fromDto(DocumentDto dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getOrganization());
        city.setStatus(Status.valueOf(dto.getStatus().getCode()));
        city.setOrganization(dto.getOrganization());
        return city;
    }
}
