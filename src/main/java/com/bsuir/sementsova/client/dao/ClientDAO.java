package com.bsuir.sementsova.client.dao;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;

import java.util.List;

public interface ClientDAO {

    boolean create(Employee item);

    boolean edit(Employee newValue);

    List<Employee> getAll();

    Employee get(int id);

    User login(User user);

    User register(User user);

}
