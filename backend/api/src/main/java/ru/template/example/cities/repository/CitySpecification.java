package ru.template.example.cities.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.template.example.cities.model.City;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@RequiredArgsConstructor
@Builder
public class CitySpecification implements Specification<City> {

    private final String name;

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(
                withName(root, cb)
        );
    }

    private Predicate withName(Root<City> root, CriteriaBuilder cb) {
        return isNotEmpty(name) ? cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%") : cb.conjunction();
    }

}
