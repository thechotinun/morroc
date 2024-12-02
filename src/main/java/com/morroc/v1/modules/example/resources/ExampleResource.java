package com.morroc.v1.modules.example.resources;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.morroc.v1.models.Example;

import lombok.Data;

@Data
public class ExampleResource {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;

    public static ExampleResource fromExample(Example example) {
        ExampleResource exampleResource = new ExampleResource();
        exampleResource.setId(example.getId());
        exampleResource.setName(example.getName());
        exampleResource.setCreatedAt(example.getCreatedAt());

        return exampleResource;
    }

    public static List<ExampleResource> fromExamples(List<Example> examples) {
        return examples.stream().map(ExampleResource::fromExample).collect(Collectors.toList());
    }
}
