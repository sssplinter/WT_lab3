package com.bsuir.sementsova.client.dao;

import com.bsuir.sementsova.client.dao.impl.ClientDAOImpl;

public final class ClientDAOFactory {
    private static final ClientDAOFactory instance = new ClientDAOFactory();

    private ClientDAOFactory() {}

    public ClientDAO getClientDAO() {
        return new ClientDAOImpl();
    }

    public static ClientDAOFactory getInstance() {
        return instance;
    }
}
