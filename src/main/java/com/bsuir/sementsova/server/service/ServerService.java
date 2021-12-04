package com.bsuir.sementsova.server.service;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;

import java.util.List;

public interface ServerService {

    boolean edit(Employee newValue);

    List<Employee> getAll();

    Employee get(int id);

    boolean create(Employee employee);

    User login(User user);

    User register(User user);
}
