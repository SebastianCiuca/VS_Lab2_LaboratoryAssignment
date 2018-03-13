package repository;

import java.io.IOException;
import java.text.ParseException;

public interface Repository<ID,T> {
    /**
     * Saves an item into the repository.
     * @param item - object of type T to be saved
     */
    void save(T item);

    /**
     * Removes item with given ID from the repository.
     * @param id - object of type Long
     */
    void remove(ID id);

    /**
     * Updates an item
     * @param id - id of object to be updated, item - object whose fields will be used for the update
     */
    void update(ID id, T item);

    /**
     * Gets all items from the repository.
     * @return an iterable with objects of type T.
     */
    Iterable findAll() throws Exception;

}
