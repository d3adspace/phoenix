package de.d3adspace.phoenix.repository.crud;

import de.d3adspace.phoenix.repository.AbstractRepository;

public abstract class AbstractCrudRepository<EntityType, EntityIdType> extends AbstractRepository<EntityType, EntityIdType> {

    /**
     * Create a new repository by the class of the entity it should store.
     *
     * @param entityClass The entities class.
     */
    protected AbstractCrudRepository(Class<EntityType> entityClass) {
        super(entityClass);
    }
}
