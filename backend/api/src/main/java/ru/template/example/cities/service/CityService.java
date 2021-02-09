package ru.template.example.cities.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.template.example.cities.model.City;
import ru.template.example.cities.repository.CityRepository;
import ru.template.example.cities.repository.CitySpecification;
import ru.template.example.common.EntityAlreadyExistsException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    @NonNull
    private final CityRepository repo;

    public City save(City entity) {
        checkNameUniqueness(entity);

        return repo.save(entity);
    }

    public void delete(Long id) {
        repo.delete(City.builder().id(id).build());
    }

    public List<City> findAll() {
        return repo.findAll();
    }

    public Page<City> filter(PageRequest pageRequest, CitySpecification specification) {
        return repo.findAll(specification, pageRequest);
    }

    private void checkNameUniqueness(City entity) {
        var id = entity.getId();

        var entityExists = id != null ?
                repo.existsByNameAndIdNot(entity.getName(), id) :
                repo.existsByName(entity.getName());
        if (entityExists) {
            throw new EntityAlreadyExistsException(String.format("City is already existed with this name %s", entity.getName()));
        }
    }
}
