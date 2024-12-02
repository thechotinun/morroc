package com.morroc.v1.modules.example.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.morroc.v1.models.Example;
import com.morroc.v1.repositories.ExampleRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ExampleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    void getExamples_ShouldReturnPagedResponse() throws Exception {
        // Create test data
        Example example = Example.builder().name("Test Example").build();
        exampleRepository.save(example);

        mockMvc.perform(get("/v1/examples")
                .param("page", "1")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.data.pageInfo.currentPage").value(1))
                .andExpect(jsonPath("$.data.pageInfo.size").value(10));
    }
}