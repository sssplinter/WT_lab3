package com.bsuir.sementsova.client.presentation.view.options;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.presentation.viewModel.GetModelView;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.List;

public class GetView extends PresentationView {
    public GetView(ClientService studentService, User user, int id) {
        super(studentService, user);
        this.model = new GetModelView(studentService, id);
    }

    @Override
    public void show() {
        List<Employee> items = this.model.getItems();
        if (items.isEmpty()) {
            System.out.println("Element not found");
        } else {
            System.out.println(items.get(0));
        }
    }

    @Override
    public PresentationView getInput(String input) {
        return new GetSelectView(this.clientService, this.currentUser);
    }
}
