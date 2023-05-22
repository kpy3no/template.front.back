package ru.template.example.cities.controller.dto;

import ru.template.example.cities.model.City;

public final class CityDtoConverter {

    public static CityDto toDto(City model) {
        CityDto cityDto = new CityDto();
        cityDto.setId(model.getId());
        cityDto.setName(model.getName());
        return cityDto;
    }

    public static City fromDto(CityDto dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        return city;
    }
}
