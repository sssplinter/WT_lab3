package com.bsuir.sementsova.server.dao.impl;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.server.dao.ServerDAO;

import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServerDAOImpl implements ServerDAO {

    private static final String USERS_XML = "src/main/resources/users.xml";
    private static final String EMPLOYEES_XML = "src/main/resources/employees.xml";

    private final ReentrantReadWriteLock employeesLock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock usersLock = new ReentrantReadWriteLock(true);

    @Override
    public List<Employee> getAll() {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee;
        this.employeesLock.readLock().lock();
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(ServerDAOImpl.EMPLOYEES_XML)))) {
            do {
                employee = (Employee) decoder.readObject();
                employees.add(employee);
            } while (employee != null);
        } catch (ArrayIndexOutOfBoundsException e) {
            // End of file.
        } catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        } finally {
            this.employeesLock.readLock().unlock();
        }


        return employees;
    }

    @Override
    public Employee get(int id) {
        Employee employee;
        this.employeesLock.readLock().lock();
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(ServerDAOImpl.EMPLOYEES_XML)))) {

            do {
                employee = (Employee) decoder.readObject();
                if (employee.getId() == id) {
                    return employee;
                }

            } while (employee != null);
        } catch (ArrayIndexOutOfBoundsException e) {
            // End of file.
        } catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        } finally {
            this.employeesLock.readLock().unlock();
        }


        return null;
    }

    @Override
    public void rewriteEmployees(List<Employee> employees) throws FileNotFoundException {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(ServerDAOImpl.EMPLOYEES_XML)))) {

            encoder.setPersistenceDelegate(LocalDate.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDate, Encoder encdr) {
                            return new Expression(localDate,
                                    LocalDate.class,
                                    "parse",
                                    new Object[]{localDate.toString()});
                        }
                    });

            encoder.setPersistenceDelegate(LocalDateTime.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDateTime, Encoder encdr) {
                            return new Expression(localDateTime,
                                    LocalDateTime.class,
                                    "parse",
                                    new Object[]{localDateTime.toString()});
                        }
                    });

            try {
                this.employeesLock.writeLock().lock();
                for (Employee item : employees) {
                    encoder.writeObject(item);
                }

            } finally {
                this.employeesLock.writeLock().unlock();
            }

        } catch (ArrayIndexOutOfBoundsException ignored) {
            // End of file.
        }
    }

    @Override
    public User userExists(User user) {
        User readUser;
        this.usersLock.readLock().lock();
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(ServerDAOImpl.USERS_XML)))) {

            do {
                readUser = (User) decoder.readObject();
                if (readUser.getLogin().equals(user.getLogin())) {
                    return readUser;
                }

            } while (readUser != null);

        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException ignored) {
            // End of file.
        } finally {
            this.usersLock.readLock().unlock();
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user;
        this.usersLock.readLock().lock();
        try (XMLDecoder decoder = new XMLDecoder(
                new BufferedInputStream(
                        new FileInputStream(ServerDAOImpl.USERS_XML)))) {
            do {
                user = (User) decoder.readObject();
                users.add(user);
            } while (user != null);
        } catch (ArrayIndexOutOfBoundsException e) {
            // End of file.
        } catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        } finally {
            this.usersLock.readLock().unlock();
        }

        return users;
    }

    @Override
    public void rewriteUsers(List<User> users) throws FileNotFoundException {
        try (XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(ServerDAOImpl.USERS_XML)))) {

            try {
                this.employeesLock.writeLock().lock();
                for (User item : users) {
                    encoder.writeObject(item);
                }

            } finally {
                this.employeesLock.writeLock().unlock();
            }

        } catch (ArrayIndexOutOfBoundsException ignored) {
            // End of file.
        }
    }
}
