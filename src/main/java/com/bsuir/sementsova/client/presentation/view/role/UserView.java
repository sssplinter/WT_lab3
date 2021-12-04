package com.bsuir.sementsova.client.presentation.view.role;

import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.presentation.view.options.GetSelectView;
import com.bsuir.sementsova.client.service.ClientService;

public class UserView extends PresentationView {
    public UserView(ClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        System.out.println("1: Get\n2: exit");
    }

    @Override
    public PresentationView getInput(String input) {
        return switch (input) {
            case "1" -> new GetSelectView(this.clientService, this.currentUser);
            case "2" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}