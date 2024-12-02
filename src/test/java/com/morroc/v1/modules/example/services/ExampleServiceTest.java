package com.morroc.v1.modules.example.services;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.morroc.v1.models.Example;
import com.morroc.v1.models.response.PagedResponse;
import com.morroc.v1.repositories.ExampleRepository;

@ExtendWith(MockitoExtension.class)
public class ExampleServiceTest {

    @Mock
    private ExampleRepository exampleRepository;

    @InjectMocks
    private ExampleService exampleService;

    private Example example;
    private UUID exampleId;

    @BeforeEach
    void setUp() {
        exampleId = UUID.randomUUID();
        example = Example.builder()
                .id(exampleId)
                .name("Test Example")
                .build();
    }

    @Test
    void getExamples_ShouldReturnPagedResponse() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Example> page = new PageImpl<>(Arrays.asList(example));
        
        when(exampleRepository.findAll(pageable)).thenReturn(page);

        PagedResponse<?> response = exampleService.getExamples(pageable);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getPageInfo().getCurrentPage()).isEqualTo(1);
    }
}