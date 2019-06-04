package de.d3adspace.phoenix.repository.map;

import de.d3adspace.phoenix.repository.crud.AbstractCrudRepository;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A map based crud repository that can store entities in an arbitrary {@link Map} implementation.
 *
 * @param <EntityType> The generic type of the entity.
 * @param <EntityIdType> The generic type of the entity id.
 */
public abstract class AbstractMapCrudRepository<EntityType, EntityIdType> extends AbstractCrudRepository<EntityType, EntityIdType> {

    /**
     * The actual storage map.
     */
    private final Map<EntityIdType, EntityType> storage;

    /**
     * Create a new repository by the class of the entity it should store and the actual storage.
     *
     * @param entityClass The entities class.
     * @param storage The map storage.
     */
    protected AbstractMapCrudRepository(Class<EntityType> entityClass, Map<EntityIdType, EntityType> storage) {
        super(entityClass);

        Validate.notNull(storage, "Storage map cannot be null.");

        this.storage = storage;
    }

    /**
     * Get the underlying storage map.
     *
     * @return The underlying storage map.
     */
    protected Map<EntityIdType, EntityType> getStorage() {
        return storage;
    }

    @Override
    public EntityType find(EntityIdType id) {

        Validate.notNull(id, "Entity id cannot be null.");

        return storage.get(id);
    }

    @Override
    public List<EntityType> findAll() {

        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteAll() {

        storage.clear();
    }

    @Override
    public void delete(EntityType entity) {

        Validate.notNull(entity, "Entity cannot be null.");

        storage.values().remove(entity);
    }

    @Override
    public long count() {

        return storage.size();
    }
}
