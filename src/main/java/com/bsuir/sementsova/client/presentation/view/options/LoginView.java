package com.bsuir.sementsova.client.presentation.view.options;

import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.role.AdminView;
import com.bsuir.sementsova.client.presentation.view.role.GuestView;
import com.bsuir.sementsova.client.presentation.view.role.UserView;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.presentation.view.input.SetInputUser;
import com.bsuir.sementsova.client.service.ClientService;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoginView extends PresentationView {
    private final List<Pair<String, SetInputUser>> inputs = Arrays.asList(
            new Pair<>("Login:", (user, input) -> {
                user.setLogin(input);
                return true;
            }),
            new Pair<>("Password:", (user, input) -> {
                user.setPassword(input);
                return true;
            })
    );

    public LoginView(ClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        int i = 0;
        String input;
        System.out.println("Enter 'quit' to exit.");
        while (i < inputs.size()) {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("quit")) {
                return;
            }

            if (inputs.get(i).getValue1().setInput(user, input)) {
                i++;
            } else {
                System.out.println("Invalid input!");
            }
        }

        User auth = this.clientService.login(user);
        if (auth == null) {
            System.out.println("User not found");
        } else {
            this.currentUser = auth;
        }
    }

    @Override
    public PresentationView getInput(String input) {
        return switch (this.currentUser.getRole()) {
            case USER -> new UserView(this.clientService, this.currentUser);
            case ADMIN -> new AdminView(this.clientService, this.currentUser);
            case GUEST -> new GuestView(this.clientService, this.currentUser);
        };
    }
}
