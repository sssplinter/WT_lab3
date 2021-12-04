package com.bsuir.sementsova.client.presentation;

import com.bsuir.sementsova.client.entity.role.UserRole;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.role.GuestView;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.Scanner;

public class Presentor {
    private PresentationView view;
    private User currentUser = null;

    public Presentor(ClientService studentService) {
        this.currentUser = new User();
        this.currentUser.setRole(UserRole.GUEST);
        this.view = new GuestView(studentService, this.currentUser);
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (this.view != null) {
            this.view.show();
            while (!getInput(scanner.nextLine())) {
                System.out.println("Invalid input");
            }
        }
    }

    private boolean getInput(String input) {
        try {
            this.view = this.view.getInput(input);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
