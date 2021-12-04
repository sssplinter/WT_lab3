package com.bsuir.sementsova.server.service.impl;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.server.dao.ServerDAO;
import com.bsuir.sementsova.server.service.ServerService;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServerServiceImpl implements ServerService {
    private final ServerDAO clientDAO;

    public ServerServiceImpl(ServerDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public boolean edit(Employee newValue) {
        List<Employee> employees = this.clientDAO.getAll();
        Employee toEdit = employees.stream()
                .filter(s -> s.getId() == newValue.getId())
                .findFirst().orElse(null);
        if (toEdit == null) {
            return false;
        }

        if ((toEdit.getLastModification() != null)
                && newValue.getLastModification().isBefore(toEdit.getLastModification())) {
            return false;
        }

        toEdit.setName(newValue.getName());
        toEdit.setBirthday(newValue.getBirthday());
        toEdit.setCharacteristic(newValue.getCharacteristic());
        toEdit.setLastModification(LocalDateTime.now());

        try {
            this.clientDAO.rewriteEmployees(employees);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Employee> getAll() {
        return this.clientDAO.getAll();
    }

    @Override
    public Employee get(int id) {
        return clientDAO.get(id);
    }

    @Override
    public boolean create(Employee employee) {
        List<Employee> employees = this.clientDAO.getAll();
        if (employees.isEmpty()) {
            employee.setId(1);
        } else {
            Employee maxIdEmployee = Collections.max(employees, Comparator.comparing(Employee::getId));
            employee.setId(maxIdEmployee.getId() + 1);
        }

        employees.add(employee);
        try {
            this.clientDAO.rewriteEmployees(employees);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    @Override
    public User login(User user) {
        User existedUser  = this.clientDAO.userExists(user);
        if ((existedUser != null)
                && existedUser.getPassword().equals(user.getPassword())) {
            return existedUser;
        }

        return null;
    }

    @Override
    public User register(User user) {
        List<User> users = this.clientDAO.getAllUsers();
        if (users.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()))) {
            return null;
        }

        if (users.isEmpty()) {
            user.setId(1);
        } else {
            User maxIdStudent = Collections.max(users, Comparator.comparing(User::getId));
            user.setId(maxIdStudent.getId() + 1);
        }

        users.add(user);
        try {
            this.clientDAO.rewriteUsers(users);
        } catch (FileNotFoundException e) {
            return null;
        }

        return user;
    }
}
