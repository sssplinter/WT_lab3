package com.bsuir.sementsova.client.service;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;

import java.util.List;

public interface ClientService {

    boolean edit(Employee newValue);

    List<Employee> getAll();

    Employee get(int id);

    boolean create(Employee employee);

    User register(User user);

    User login(User user);
}
