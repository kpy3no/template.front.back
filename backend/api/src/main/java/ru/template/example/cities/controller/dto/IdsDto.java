package ru.template.example.cities.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdsDto {

    @Valid
    @NotEmpty(message = "Нужно выбрать хотя бы один элемент")
    private Set<Long> ids;
}