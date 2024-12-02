package com.morroc.v1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morroc.v1.models.Example;


@Repository
public interface ExampleRepository extends JpaRepository<Example, UUID> {

}
