package com.bsuir.sementsova.server.dao;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface ServerDAO {

    List<Employee> getAll();

    Employee get(int id);

    User userExists(User user);

    List<User> getAllUsers ();

    void rewriteUsers(List<User> users) throws FileNotFoundException;

    void rewriteEmployees(List<Employee> employees) throws FileNotFoundException;
}
