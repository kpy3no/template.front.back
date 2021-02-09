package ru.template.example.cities.controller.dto;

import lombok.NoArgsConstructor;
import ru.template.example.cities.model.City;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CityDtoConverter {

    public static CityDto toDto(City model) {
        return CityDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }

    public static City fromDto(CityDto dto) {
        return City.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
