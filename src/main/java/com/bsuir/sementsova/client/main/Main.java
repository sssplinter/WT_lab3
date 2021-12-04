package com.bsuir.sementsova.client.main;

import com.bsuir.sementsova.client.dao.ClientDAOFactory;
import com.bsuir.sementsova.client.presentation.Presentor;
import com.bsuir.sementsova.client.service.ServiceClientFactory;

public class Main {
    public static void main(String[] args) {
        ServiceClientFactory factory = ServiceClientFactory.getInstance();
        ClientDAOFactory daoFactory = ClientDAOFactory.getInstance();
        Presentor presentor = new Presentor(factory.getService(daoFactory.getClientDAO()));
        presentor.show();
    }
}
