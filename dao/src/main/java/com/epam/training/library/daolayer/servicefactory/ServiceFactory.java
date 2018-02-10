package com.epam.training.library.daolayer.servicefactory;

import com.epam.training.library.daolayer.service.Service;

public interface ServiceFactory<T extends Service> {
    T create();
}
