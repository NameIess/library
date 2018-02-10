package com.epam.training.library.daolayer.daofactory;

import com.epam.training.library.daolayer.connection.ConnectionManager;
import com.epam.training.library.daolayer.connection.DbConnectionPool;
import com.epam.training.library.daolayer.connection.DbConnectionPoolFactory;
import com.epam.training.library.daolayer.connection.PoolFactory;
import com.epam.training.library.daolayer.dao.*;
import com.epam.training.library.daolayer.daofactory.impl.*;
import com.epam.training.library.daolayer.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private static final Logger Log = LogManager.getLogger(DaoManager.class.getSimpleName());

    private static DaoManager daoManager;
    private Map<Class<? extends Identified>, DaoFactory> factoryList = new HashMap<>();
    private ConnectionManager connectionManager;

    private DaoManager() {
        factoryList.put(Book.class, new BookDaoCreator());
        factoryList.put(Receipt.class, new ReceiptDaoCreator());
        factoryList.put(Role.class, new RoleDaoCreator());
        factoryList.put(User.class, new UserDaoCreator());
        factoryList.put(Status.class, new StatusDaoCreator());
    }

    public static DaoManager getInstance() {
        if (daoManager == null) {
            daoManager = new DaoManager();
            daoManager.initPool();
        }
        return daoManager;
    }

    private void initPool() {
        PoolFactory poolFactory = new DbConnectionPoolFactory();
        DbConnectionPool connectionPool = poolFactory.createPool();
        connectionManager = new ConnectionManager(connectionPool);
        Log.debug("Pool and connection manager have been initialized. \n Pool : " + connectionPool + ".\n Connection manager : " + connectionManager);
    }

    public <T extends Identified, K extends GenericDao<T>> K getDao(Class<T> dtoClass) {
        DaoFactory<K> daoFactory = factoryList.get(dtoClass);
        K daoInstance = daoFactory.create(connectionManager);
        return daoInstance;
    }
}
