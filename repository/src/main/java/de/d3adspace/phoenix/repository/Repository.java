package de.d3adspace.phoenix.repository;

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
}
