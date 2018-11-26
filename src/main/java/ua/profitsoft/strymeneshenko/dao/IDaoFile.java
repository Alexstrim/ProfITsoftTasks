package ua.profitsoft.strymeneshenko.dao;

import java.io.IOException;

public interface IDaoFile<T> {
    T read(long id) throws IOException;
}
