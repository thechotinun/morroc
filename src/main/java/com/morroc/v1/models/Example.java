package com.morroc.v1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="examples")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Example extends Base {
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;

}
