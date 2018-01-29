package com.epam.training.library.daolayer.daofactory;

import com.epam.training.library.daolayer.dao.GenericDao;
import com.epam.training.library.daolayer.model.Identified;

import java.sql.Connection;

public interface Connectable {

    Connection getConnection();

    <T extends Identified> GenericDao<T> getDao(Class<T> dtoClass);

    interface DaoBuilder {
        GenericDao create(Connection connection);
    }
}