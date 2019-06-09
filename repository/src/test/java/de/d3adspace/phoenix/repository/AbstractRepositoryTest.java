package de.d3adspace.phoenix.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractRepositoryTest {

    private static final Class<String> ENTITY_TYPE_CLASS = String.class;
    private AbstractRepository<String, Integer> abstractRepository;

    @BeforeEach
    void setUp() {
        abstractRepository = new AbstractRepository<>(ENTITY_TYPE_CLASS) {
        };
    }

    @Test
    void getEntityClass() {

        Class<String> entityClass = abstractRepository.getEntityClass();
        assertEquals(ENTITY_TYPE_CLASS, entityClass);
    }
}
