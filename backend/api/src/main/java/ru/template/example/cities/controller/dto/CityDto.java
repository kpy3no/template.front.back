package ru.template.example.cities.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Long id;

    @NotBlank
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid code, only latin symbols!")
    private String name;
}
