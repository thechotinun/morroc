package com.morroc.v1.modules.example.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morroc.v1.exceptions.ExampleException;
import com.morroc.v1.models.Example;
import com.morroc.v1.models.response.PagedResponse;
import com.morroc.v1.models.response.PagedResponse.PageInfo;
import com.morroc.v1.modules.example.dto.ExampleCreateDTO;
import com.morroc.v1.modules.example.dto.ExampleUpdateDTO;
import com.morroc.v1.modules.example.resources.ExampleResource;
import com.morroc.v1.repositories.ExampleRepository;

@Service
public class ExampleService {
    @Autowired
    private ExampleRepository exampleRepository;

    public Example creatExample(ExampleCreateDTO newExample) {
        Example example = Example.builder().name(newExample.getName()).build();

        exampleRepository.save(example);

        return example;
    }

    public PagedResponse<ExampleResource> getExamples(Pageable pageable) {
        Page<Example> examplePage = exampleRepository.findAll(pageable);
        List<ExampleResource> resources = ExampleResource.fromExamples(examplePage.getContent());

        PageInfo pageInfo = new PageInfo(
                examplePage.getNumber() + 1,
                examplePage.getSize(),
                examplePage.getTotalElements(),
                examplePage.getTotalPages(),
                examplePage.getNumberOfElements());

        return new PagedResponse<>(resources, pageInfo);
    }

    public Example getExampleById(UUID id) {
        return exampleRepository.findById(id).orElseThrow(
                () -> new ExampleException("Example not found with id: " + id,
                        "The requested user could not be found",
                        ExampleException.ErrorCode.EXAMPLE_NOT_FOUND));
    }

    public Example update(UUID id, ExampleUpdateDTO updateExample) {
        Example example = exampleRepository.findById(id).orElseThrow(
                () -> new ExampleException("Example not found with id: " + id,
                        "The requested user could not be found",
                        ExampleException.ErrorCode.EXAMPLE_NOT_FOUND));

        if (updateExample.getName() != null && !updateExample.getName().trim().isEmpty()) {
            example.setName(updateExample.getName().trim());
        }

        return exampleRepository.save(example);
    }

    public Example delete(UUID id) {
        Example example = exampleRepository.findById(id).orElseThrow(
                () -> new ExampleException("Example not found with id: " + id,
                        "The requested user could not be found",
                        ExampleException.ErrorCode.EXAMPLE_NOT_FOUND));

        example.setDeletedAt(LocalDateTime.now());
        exampleRepository.save(example);
        
        return example;
    }
}
