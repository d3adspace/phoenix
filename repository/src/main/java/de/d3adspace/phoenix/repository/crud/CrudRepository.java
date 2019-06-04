package de.d3adspace.phoenix.repository.crud;

import de.d3adspace.phoenix.repository.Repository;

import java.util.List;

public interface CrudRepository<EntityType, EntityIdType> extends Repository {

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

    /**
     * Delete the given entity.
     *
     * @param entity The entity.
     */
    void delete(EntityType entity);
}
