package ru.template.example.cities.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import ru.template.example.cities.model.City;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
@ContextConfiguration(
        initializers = CityRepositoryTest.PropertyOverrideContextInitializer.class)
public class CityRepositoryTest {

    @Autowired
    private CityRepository sut;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldFilterBySpecification() {
        sut.save(City.builder()
                .name("name1")
                .build());

        sut.save(City.builder()
                .name("name2")
                .build());

        entityManager.flush();
        entityManager.clear();

        assertEquals(2, sut.count());

        var result = sut.findAll(CitySpecification.builder().build());
        assertEquals(2, result.size());

        result = sut.findAll(CitySpecification.builder()
                .name("name1")
                .build());
        assertEquals(1, result.size());
        assertEquals("name1", result.get(0).getName());

        result = sut.findAll(CitySpecification.builder().name("wrong").build());
        assertEquals(0, result.size());
    }

    @Test
    public void shouldFailWhenInsertWithSameName() {
        var cityBuilder = City.builder().name("name1");

        sut.save(cityBuilder.build());
        try {
            sut.save(cityBuilder.build());
            fail("Should fail due to integrity violation");
        } catch (Exception e) {
            assertTrue(e instanceof DataIntegrityViolationException);
            var cause = e.getCause();
            assertNotNull(cause);
            cause = cause.getCause();
            assertNotNull(cause);
        }
    }


    /**
     * To run tests in real docker postgres database (not in embedded)
     */
    static class PropertyOverrideContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                    "spring.datasource.url=jdbc:tc:postgresql:///?TC_REUSABLE=true");
        }
    }
}
