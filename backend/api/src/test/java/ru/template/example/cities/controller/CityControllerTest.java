package ru.template.example.cities.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.template.example.cities.controller.dto.CityDto;
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
    public void shouldFailToPostWhenRequiredFieldsNull() throws Exception {
        mvc.perform(postAction(ROUTE, new CityDto()))
                .andExpect(status().isBadRequest())
                .andExpect(contentJson(validationError(
                        "name: must not be blank"
                )));
    }

    @Test
    public void shouldFailToPostWhenRequiredFieldsSpaceOnly() throws Exception {
        mvc.perform(postAction(ROUTE, CityDto.builder()
                .name(" ")
                .build()))
                .andExpect(status().isBadRequest())
                .andExpect(contentJson(validationError(
                        "name: must not be blank",
                        "name: Invalid code, only latin symbols!"
                )));
    }

    @Test
    public void shouldFailToPostWhenRequiredFieldsMaxLengthPlusOneAndUnknownEnums() throws Exception {
        mvc.perform(postAction(ROUTE, CityDto.builder()
                .name(randomAlphabetic(101))
                .build()))
                .andExpect(status().isBadRequest())
                .andExpect(contentJson(validationError(
                        "name: length must be between 0 and 100"
                )));
    }

    @Test
    public void shouldSuccessToPostWhenRequiredFieldsMaxLength() throws Exception {
        var name = randomAlphabetic(100);
        when(cityService.save(any())).thenReturn(City.builder()
                .name(name)
                .build());
        var cityDto = CityDto.builder()
                .name(name)
                .build();
        mvc.perform(postAction(ROUTE, cityDto))
                .andExpect(status().isOk())
                .andExpect(contentJson(cityDto));
    }
}