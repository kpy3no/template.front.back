package ru.template.example.cities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.template.example.cities.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
