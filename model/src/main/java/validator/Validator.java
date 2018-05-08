package validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grigo on 11/14/16.
 */
public interface Validator<E> {
    void validate(E entity) throws ValidationException;
    public void exist(ArrayList<E> list, E t) throws ValidationException;
}
