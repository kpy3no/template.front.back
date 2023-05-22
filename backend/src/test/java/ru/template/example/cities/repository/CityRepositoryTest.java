package ru.template.example.cities.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.template.example.cities.model.City;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
public class CityRepositoryTest {

    @Autowired
    private CityRepository sut;

    @Test
    public void shouldInsert() {
        City city = new City();
        city.setName("name1");
        sut.save(city);

        City cityFromDb = sut.getOne(city.getId());
        assertNotNull(cityFromDb.getId());
        assertEquals("name1", cityFromDb.getName());
    }
}
