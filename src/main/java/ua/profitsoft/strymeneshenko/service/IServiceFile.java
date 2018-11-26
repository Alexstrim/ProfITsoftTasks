package ua.profitsoft.strymeneshenko.service;


import ua.profitsoft.strymeneshenko.dao.IDaoFile;

import java.io.IOException;

public interface IServiceFile<T> {
    IDaoFile<T> getDaoFile();

    default T read(long id) throws IOException {
        return getDaoFile().read(id);
    }
}
