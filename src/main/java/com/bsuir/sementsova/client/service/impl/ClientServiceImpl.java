package com.bsuir.sementsova.client.service.impl;

import com.bsuir.sementsova.client.dao.ClientDAO;
import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public boolean edit(Employee newValue) {
        return this.clientDAO.edit(newValue);
    }

    @Override
    public List<Employee> getAll() {
        return this.clientDAO.getAll();
    }

    @Override
    public Employee get(int id) {
        return this.clientDAO.get(id);
    }

    @Override
    public boolean create(Employee employee) {
        return this.clientDAO.create(employee);
    }

    @Override
    public User register(User user) {
        return this.clientDAO.register(user);
    }

    @Override
    public User login(User user) {
        return this.clientDAO.login(user);
    }
}
