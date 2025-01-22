package Dal.Dal.Repositories.Abstractions;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.concurrent.CompletableFuture;

public interface IRepository<T> {

    /**
     * Adds an entity to the repository.
     * @param entity The entity to add.
     * @return A CompletableFuture representing the completion of the add operation.
     */
    CompletableFuture<Void> addAsync(T entity);

    /**
     * Updates an existing entity in the repository.
     * @param entity The entity to update.
     * @return A CompletableFuture representing the completion of the update operation.
     */
    CompletableFuture<Void> updateAsync(T entity);

    /**
     * Deletes an entity from the repository.
     * @param entity The entity to delete.
     * @return A CompletableFuture representing the completion of the delete operation.
     */
    CompletableFuture<Void> deleteAsync(T entity);

    /**
     * Retrieves an entity by its ID.
     * @param id The ID of the entity.
     * @return A CompletableFuture containing the entity if found, or an empty Optional.
     */
    CompletableFuture<Optional<T>> getByIdAsync(String id);

    /**
     * Retrieves all entities from the repository.
     * @return A CompletableFuture containing a list of all entities.
     */
    CompletableFuture<List<T>> getAllAsync();

    /**
     * Finds entities based on a given condition.
     * @param predicate The condition to filter the entities.
     * @return A CompletableFuture containing a list of entities matching the predicate.
     */
    CompletableFuture<List<T>> findAsync(Predicate<T> predicate);

    /**
     * Checks if any entity exists that matches the provided condition.
     * @param predicate The condition to check for existence.
     * @return A CompletableFuture containing a boolean indicating whether any matching entity exists.
     */
    CompletableFuture<Boolean> anyAsync(Predicate<T> predicate);
}
