package com.epam.training.library.daolayer.servicefactory;

import com.epam.training.library.daolayer.model.*;
import com.epam.training.library.daolayer.service.Service;
import com.epam.training.library.daolayer.servicefactory.impl.BookServiceCreator;
import com.epam.training.library.daolayer.servicefactory.impl.ReceiptServiceCreator;
import com.epam.training.library.daolayer.servicefactory.impl.RoleServiceCreator;
import com.epam.training.library.daolayer.servicefactory.impl.UserServiceCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private static ServiceManager serviceManager;
    private Map<Class<? extends Identified>, ServiceFactory> factoryList = new HashMap<>();

    private ServiceManager() {
        factoryList.put(Book.class, new BookServiceCreator());
        factoryList.put(Receipt.class, new ReceiptServiceCreator());
        factoryList.put(Role.class, new RoleServiceCreator());
        factoryList.put(User.class, new UserServiceCreator());
    }

    public static ServiceManager getInstance() {
        if (serviceManager == null) {
            serviceManager = new ServiceManager();
        }
        return serviceManager;
    }

    public <T extends Identified, K extends Service> K getService(Class<T> instanceClass) {
        ServiceFactory<K> serviceFactory = factoryList.get(instanceClass);
        K serviceInstance = serviceFactory.create();
        return serviceInstance;
    }
}