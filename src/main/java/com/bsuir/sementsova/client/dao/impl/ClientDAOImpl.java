package com.bsuir.sementsova.client.dao.impl;

import com.bsuir.sementsova.client.dao.ClientDAO;
import com.bsuir.sementsova.client.dao.socket.SocketManager;
import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.employee.EmployeeResponse;
import com.bsuir.sementsova.client.entity.request.RequestType;
import com.bsuir.sementsova.client.entity.request.ResponseType;
import com.bsuir.sementsova.client.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final SocketManager socketManager = new SocketManager("localhost", 5555);

    @Override
    public boolean edit(Employee newValue) {
        EmployeeResponse response = socketManager.sendRequest(newValue, RequestType.EDIT);

        return (response != null) && (response.getResponseType() == ResponseType.OK);
    }

    @Override
    public List<Employee> getAll() {
        EmployeeResponse response = socketManager.sendRequest(null, RequestType.GET_ALL);

        if ((response != null)
                && (response.getResponseType() == ResponseType.OK)
                && (response.getBody() instanceof ArrayList<?>)) {
            try {
                return (ArrayList<Employee>) response.getBody();
            } catch (ClassCastException e) {
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    @Override
    public Employee get(int id) {
        EmployeeResponse response = socketManager
                .sendRequest(id, RequestType.GET);

        if ((response != null)
                && (response.getResponseType() == ResponseType.OK)
                && (response.getBody() instanceof Employee employee)) {
            return employee;
        }

        return null;
    }

    @Override
    public boolean create(Employee item) {
        EmployeeResponse response = socketManager
                .sendRequest(item, RequestType.CREATE);
        return (response != null)
                && (response.getResponseType() == ResponseType.OK);
    }

    @Override
    public User register(User user) {
        EmployeeResponse response = socketManager
                .sendRequest(user, RequestType.REGISTER);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }

    @Override
    public User login(User user) {
        EmployeeResponse response = socketManager
                .sendRequest(user, RequestType.LOGIN);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }
}
