package com.bsuir.sementsova.server.main.server.controller;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.employee.EmployeeRequest;
import com.bsuir.sementsova.client.entity.employee.EmployeeResponse;
import com.bsuir.sementsova.client.entity.request.ResponseType;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.server.service.ServerService;

import java.util.List;

public record ClientController(ServerService service) {

    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee;
        EmployeeResponse response = new EmployeeResponse();
        if (request.getBody() instanceof Employee body) {
            employee = body;
        } else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        if (this.service.create(employee)) {
            response.setResponseType(ResponseType.OK);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    public EmployeeResponse edit(EmployeeRequest request) {
        Employee employee;
        EmployeeResponse response = new EmployeeResponse();
        if (request.getBody() instanceof Employee body) {
            employee = body;
        } else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        if (this.service.edit(employee)) {
            response.setResponseType(ResponseType.OK);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    public EmployeeResponse getAll() {
        List<Employee> employees = this.service.getAll();
        EmployeeResponse response = new EmployeeResponse();
        if (employees == null) {
            response.setResponseType(ResponseType.ERROR);
            response.setBody(null);
        } else {
            response.setResponseType(ResponseType.OK);
            response.setBody(employees);
        }

        return response;
    }

    public EmployeeResponse get(EmployeeRequest request) {
        int id;
        EmployeeResponse response = new EmployeeResponse();
        if (request.getBody() instanceof Integer) {
            id = (int) request.getBody();
        } else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        Employee employeeToSend = this.service.get(id);
        if (employeeToSend != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(employeeToSend);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    public EmployeeResponse register(EmployeeRequest request) {
        User user;
        EmployeeResponse response = new EmployeeResponse();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        User userResult = this.service.register(user);
        if (userResult != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    public EmployeeResponse login(EmployeeRequest request) {
        User user;
        EmployeeResponse response = new EmployeeResponse();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        User userResult = this.service.login(user);
        if (userResult != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    public EmployeeResponse notFound() {
        EmployeeResponse response = new EmployeeResponse();
        response.setResponseType(ResponseType.NOTFOUND);
        return response;
    }
}
