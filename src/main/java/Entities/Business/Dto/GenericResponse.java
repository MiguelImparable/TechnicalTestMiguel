package Entities.Business.Dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Gmf generic response.
 *
 * @param <TEntity> The type for response.
 */
public class GenericResponse<TEntity> {

    /**
     * The object of type TEntity.
     */
    private TEntity object;
    
    /**
     * The list of exception messages.
     */
    private List<String> exceptions;

    /**
     * Default constructor.
     */
    public GenericResponse() {
        this.exceptions = new ArrayList<>();
    }

    /**
     * Gets the object.
     *
     * @return the object of type TEntity.
     */
    public TEntity getObject() {
        return object;
    }

    /**
     * Sets the object.
     *
     * @param object the object to set.
     */
    public void setObject(TEntity object) {
        this.object = object;
    }

    /**
     * Determines whether the result is successful.
     *
     * @return true if there are no exceptions, otherwise false.
     */
    public boolean isResult() {
        return exceptions == null || exceptions.isEmpty();
    }

    /**
     * Gets the list of exception messages.
     *
     * @return the list of exception messages.
     */
    public List<String> getExceptions() {
        return exceptions;
    }

    /**
     * Sets the list of exception messages.
     *
     * @param exceptions the list of exception messages to set.
     */
    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }
}