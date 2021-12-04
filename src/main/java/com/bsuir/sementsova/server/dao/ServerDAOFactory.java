package com.bsuir.sementsova.server.dao;

import com.bsuir.sementsova.server.dao.impl.ServerDAOImpl;

public class ServerDAOFactory {
    private static final ServerDAOFactory instance = new ServerDAOFactory();

    private ServerDAOFactory() {}

    public ServerDAO getServerDAO() {
        return new ServerDAOImpl();
    }

    public static ServerDAOFactory getInstance() {
        return instance;
    }
}
