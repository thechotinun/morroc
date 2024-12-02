package com.morroc.v1.modules.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExampleCreateDTO {
    
    @NotNull(message = "{example_name_not_null}")
    @NotEmpty(message = "{example_name_not_empty}")
    @Size(max = 100, message = "{example_name_too_long}")
    private String name;
}
