package ru.template.example.cities.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.template.example.cities.controller.dto.DocumentDto;
import ru.template.example.cities.model.City;
import ru.template.example.cities.service.CityService;
import ru.template.example.controller.AbstractWebMvcTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CityControllerTest extends AbstractWebMvcTest {

    private static final String ROUTE = "/cities";

    @MockBean
    private CityService cityService;

    @Test
    public void shouldSuccessToPostWhenRequiredFieldsMaxLength() throws Exception {
        var name = randomAlphabetic(100);

        City city = new City();
        city.setName(name);
        when(cityService.save(any())).thenReturn(city);

        var cityDto = new DocumentDto();
        cityDto.setOrganization(name);
        mvc.perform(postAction(ROUTE, cityDto))
                .andExpect(status().isOk())
                .andExpect(contentJson(cityDto));
    }
}