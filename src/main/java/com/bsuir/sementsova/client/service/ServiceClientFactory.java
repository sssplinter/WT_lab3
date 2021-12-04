package com.bsuir.sementsova.client.service;

import com.bsuir.sementsova.client.dao.ClientDAO;
import com.bsuir.sementsova.client.service.impl.ClientServiceImpl;

public class ServiceClientFactory {
    private static final ServiceClientFactory instance = new ServiceClientFactory();

    private ServiceClientFactory() {}

    public ClientService getService(ClientDAO studentDAO) {

        return new ClientServiceImpl(studentDAO);
    }

    public static ServiceClientFactory getInstance() {
        return instance;
    }
}
