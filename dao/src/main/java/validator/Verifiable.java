package validator;

public interface Verifiable<T> {
    boolean validate(T object);
}
