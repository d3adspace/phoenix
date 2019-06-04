package de.d3adspace.phoenix.repository;

import org.apache.commons.lang3.Validate;

/**
 * Abstract implementation of the {@link Repository} that manages the entities class.
 *
 * @param <EntityType> The generic type of the entity.
 */
public abstract class AbstractRepository<EntityType, EntityIdType> implements Repository<EntityType, EntityIdType> {

    /**
     * The class that implements the entity.
     */
    private final Class<EntityType> entityClass;

    /**
     * Create a new repository by the class of the entity it should store.
     *
     * @param entityClass The entities class.
     */
    protected AbstractRepository(Class<EntityType> entityClass) {
        Validate.notNull(entityClass, "Entity class cannot be null.");

        this.entityClass = entityClass;
    }

    @Override
    public Class<EntityType> getEntityClass() {

        return entityClass;
    }
}
