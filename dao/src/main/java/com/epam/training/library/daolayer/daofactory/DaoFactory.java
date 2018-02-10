package com.epam.training.library.daolayer.daofactory;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.dao.GenericDao;

public interface DaoFactory<T extends GenericDao> {

    T create(ConnectionManager connectionManager);
}
