package com.bsuir.sementsova.server.service;

import com.bsuir.sementsova.server.dao.ServerDAO;
import com.bsuir.sementsova.server.service.impl.ServerServiceImpl;

public class ServiceServerFactory {
    private static final ServiceServerFactory instance = new ServiceServerFactory();

    private ServiceServerFactory() {}

    public ServerService getService(ServerDAO studentDAO) {

        return new ServerServiceImpl(studentDAO);
    }

    public static ServiceServerFactory getInstance() {
        return instance;
    }
}
