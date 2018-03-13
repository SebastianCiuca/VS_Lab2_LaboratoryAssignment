package validator;

public interface Validator<T> {

    /**
     * Validate an item of type T.
     * @param item - object of type T to be validated.
     */
    boolean validate(T item);
}
