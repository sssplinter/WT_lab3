package com.bsuir.sementsova.client.presentation.view.options;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.role.AdminView;
import com.bsuir.sementsova.client.presentation.view.role.UserView;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.List;

public class GetSelectView extends PresentationView {
    public GetSelectView(ClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        List<Employee> employeeList = this.clientService.getAll();
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + ": " + employee.getName());
        }

        System.out.println("Print 'quit' to exit");
        System.out.println("Select student id: ");
    }

    @Override
    public PresentationView getInput(String input) {
        if ("quit".equals(input)) {
            return switch (this.currentUser.getRole()) {
                case USER -> new UserView(this.clientService, this.currentUser);
                case ADMIN -> new AdminView(this.clientService, this.currentUser);
                default -> null;
            };
        }

        int id;
       try {
        id = Integer.parseInt(input);
       } catch (NumberFormatException ex) {
           throw new IllegalArgumentException();
       }

       return new GetView(this.clientService, this.currentUser, id);
    }
}
