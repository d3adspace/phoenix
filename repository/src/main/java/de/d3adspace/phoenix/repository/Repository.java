package de.d3adspace.phoenix.repository;

import java.util.List;
import java.util.Optional;

/**
 * A repository defines the entry point for managing an entity.
 */
public interface Repository<EntityType> {

    /**
     * The class of the entity.
     *
     * @return The class of the entity.
     */
    Class<EntityType> getEntityClass();

    /**
     * Find all entities.
     */
    List<EntityType> findAll();

    /**
     * Save the given entity.
     *
     * @param entity The entity.
     * @return The saved entity.
     */
    EntityType save(EntityType entity);
}
