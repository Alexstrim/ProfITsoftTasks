package ua.profitsoft.strymeneshenko.validate;

import java.util.Map;

public interface IValidate<T> {
    Map<String, String> validate(T entity);
}
