package ru.template.example.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.template.example.cities.controller.dto.CityDto;
import ru.template.example.cities.controller.dto.CityDtoConverter;
import ru.template.example.cities.controller.dto.IdsDto;
import ru.template.example.cities.service.CityService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.template.example.cities.controller.dto.CityDtoConverter.fromDto;
import static ru.template.example.cities.controller.dto.CityDtoConverter.toDto;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CityDto save(@RequestBody CityDto dto) {
        return toDto(service.save(fromDto(dto)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityDto> get() {
        return service.findAll().stream()
                .map(CityDtoConverter::toDto)
                .collect(toList());
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @DeleteMapping
    public void deleteAll(@RequestBody IdsDto idsDto) {
        service.deleteAll(idsDto.getIds());
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CityDto get(@PathVariable Long id) {
        return toDto(service.get(id));
    }

}