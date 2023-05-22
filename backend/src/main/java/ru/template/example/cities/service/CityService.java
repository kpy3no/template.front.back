package ru.template.example.cities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.template.example.cities.model.City;
import ru.template.example.cities.repository.CityRepository;
import ru.template.example.common.EntityAlreadyExistsException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public City save(City entity) {
        checkNameUniqueness(entity);

        return repo.save(entity);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll(Set<Long> ids) {
        Set<City> cities = ids.stream().map(id -> {
            City city = new City();
            city.setId(id);
            return city;
        }).collect(Collectors.toSet());
        repo.deleteAll(cities);
    }

    public List<City> findAll() {
        return repo.findAll();
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

    public City get(Long id) {
        return repo.getOne(id);
    }
}
