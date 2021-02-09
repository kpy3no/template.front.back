package ru.template.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.template.example.configuration.CustomRestExceptionHandler;
import ru.template.example.configuration.JacksonConfiguration;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public abstract class AbstractWebMvcTest {

    @Autowired
    protected MockMvc mvc;

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new JacksonConfiguration().objectMapper();
    }

    protected CustomRestExceptionHandler.RestApiError validationError(String... errors) {
        return CustomRestExceptionHandler.RestApiError.builder()
                .message("Validation failed")
                .errors(List.of(errors))
                .build();
    }

    protected ResultMatcher contentJson(Object error) throws JsonProcessingException {
        return content().json(mapper.writeValueAsString(error));
    }

    protected MockHttpServletRequestBuilder postAction(String uri, Object dto) throws JsonProcessingException {
        return post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto));
    }
}
