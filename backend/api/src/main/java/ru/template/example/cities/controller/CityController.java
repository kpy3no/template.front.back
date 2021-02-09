package ru.template.example.cities.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.template.example.cities.controller.dto.CityDto;
import ru.template.example.cities.controller.dto.CityDtoConverter;
import ru.template.example.cities.controller.dto.CityFilterDto;
import ru.template.example.cities.service.CityService;
import ru.template.example.common.PageResponse;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static java.util.stream.Collectors.toList;

import static ru.template.example.cities.controller.dto.CityDtoConverter.toDto;
import static ru.template.example.cities.controller.dto.CityDtoConverter.fromDto;
import static ru.template.example.cities.controller.dto.CityFilterDtoConverter.toSpecification;
import static ru.template.example.common.PageConverter.toPageRequest;
import static ru.template.example.common.PageConverter.toPageResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService service;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CityDto save(@Valid @RequestBody CityDto dto) {
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

    @PostMapping(path = "filter",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<CityDto> filter(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(60) Integer size,
            @RequestParam(required = false, defaultValue = "id") String sortPropertyName,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection,
            @RequestBody(required = false) CityFilterDto filter
    ) {
        return toPageResponse(
                service.filter(toPageRequest(page, size, sortPropertyName, sortDirection), toSpecification(filter)),
                CityDtoConverter::toDto
        );
    }
}
