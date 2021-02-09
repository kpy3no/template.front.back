package ru.template.example.cities.controller.dto;

import lombok.NoArgsConstructor;
import ru.template.example.cities.repository.CitySpecification;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CityFilterDtoConverter {

    public static CitySpecification toSpecification(CityFilterDto dto) {
        if (dto == null) {
            return CitySpecification.builder().build();
        }

        return CitySpecification.builder()
                .name(dto.getName())
                .build();
    }
}
