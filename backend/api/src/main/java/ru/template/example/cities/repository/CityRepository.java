package ru.template.example.cities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.template.example.cities.model.City;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
