package com.morroc.v1.modules.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morroc.v1.models.Example;
import com.morroc.v1.models.response.PagedResponse;
import com.morroc.v1.models.response.SuccessResponse;
import com.morroc.v1.modules.example.dto.ExampleCreateDTO;
import com.morroc.v1.modules.example.dto.ExampleUpdateDTO;
import com.morroc.v1.modules.example.resources.ExampleResource;
import com.morroc.v1.modules.example.services.ExampleService;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/v1/examples")
@Tag(name = "Example", description = "Example management APIs")
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @Operation(
        summary = "Create a new example",
        description = "Creates a new example with the provided data"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Example created successfully",
        content = @Content(schema = @Schema(implementation = SuccessResponse.class))
    )
    @PostMapping
    public ResponseEntity<SuccessResponse<ExampleResource>> create(
            @Valid @RequestBody ExampleCreateDTO exampleCreateDTO) {
        Example createdExample = exampleService.creatExample(exampleCreateDTO);
        ExampleResource exampleResource = ExampleResource.fromExample(createdExample);

        SuccessResponse<ExampleResource> response = new SuccessResponse<>(exampleResource,
                HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get all examples",
        description = "Returns a paginated list of examples"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved examples"
    )
    @GetMapping
    public ResponseEntity<SuccessResponse<PagedResponse<ExampleResource>>> examples(
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        PagedResponse<ExampleResource> pagedResponse = exampleService.getExamples(pageable);

        SuccessResponse<PagedResponse<ExampleResource>> response = new SuccessResponse<>(
                pagedResponse,
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());

        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Find example by ID",
        description = "Returns a single example by its ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Example found successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Example not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ExampleResource>> find(@PathVariable UUID id) {
        Example example = exampleService.getExampleById(id);
        ExampleResource exampleResource = ExampleResource.fromExample(example);

        SuccessResponse<ExampleResource> response = new SuccessResponse<>(exampleResource, HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Update example",
        description = "Updates an existing example by its ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Example updated successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Example not found"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<ExampleResource>> update(
            @PathVariable UUID id,
            @Valid @RequestBody ExampleUpdateDTO userUpdateRequest) {
        Example updateExample = exampleService.update(id, userUpdateRequest);
        ExampleResource exampleResource = ExampleResource.fromExample(updateExample);

        SuccessResponse<ExampleResource> response = new SuccessResponse<>(exampleResource,
                HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Delete example",
        description = "Deletes an example by its ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Example deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Example not found"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<ExampleResource>> delete(@PathVariable UUID id) {
        Example deletedExample = exampleService.delete(id);
        ExampleResource exampleResource = ExampleResource.fromExample(deletedExample);

        SuccessResponse<ExampleResource> response = new SuccessResponse<>(exampleResource,
                HttpStatus.OK.value(), "User successfully deleted");
        return ResponseEntity.ok(response);
    }
}
