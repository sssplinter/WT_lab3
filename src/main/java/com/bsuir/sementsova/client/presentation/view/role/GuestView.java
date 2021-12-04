package com.bsuir.sementsova.client.presentation.view.role;

import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.presentation.view.options.LoginView;
import com.bsuir.sementsova.client.presentation.view.options.RegisterView;
import com.bsuir.sementsova.client.service.ClientService;

public class GuestView extends PresentationView {

    public GuestView(ClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        System.out.println("1: Register\n2: Login\n3: exit");
    }

    @Override
    public PresentationView getInput(String input) {
        return switch (input) {
            case "1" -> new RegisterView(this.clientService, this.currentUser);
            case "2" -> new LoginView(this.clientService, this.currentUser);
            case "3" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
