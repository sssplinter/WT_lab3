package com.bsuir.sementsova.client.presentation.view.options;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.view.role.AdminView;
import com.bsuir.sementsova.client.presentation.view.PresentationView;
import com.bsuir.sementsova.client.presentation.view.input.SetInputEmployee;
import com.bsuir.sementsova.client.service.ClientService;
import org.javatuples.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateView extends PresentationView {
    private final List<Pair<String, SetInputEmployee>> inputs = Arrays.asList(
            new Pair<>("Print name:", (student, input) -> {
                student.setName(input);
                return true;
            }),
            new Pair<>("Birthday(dd/MM/yyyy):", (student, input) -> {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    student.setBirthday(LocalDate.parse(input, dateTimeFormatter));
                    return true;
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid format");
                }

                return false;
            }),
            new Pair<>("Characteristic:", (student, input) -> {
                student.setCharacteristic(input);
                return true;
            })
    );

    public CreateView(ClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        String input;
        System.out.println("Enter 'quit' to exit.");
        while (i < inputs.size()) {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("quit")) {
                return;
            }

            if (inputs.get(i).getValue1().setInput(employee, input)) {
                i++;
            } else {
                System.out.println("Invalid input!");
            }
        }

        employee.setLastModification(LocalDateTime.now());
        if (!this.clientService.create(employee)) {
            System.out.println("Error creating");
        }
    }

    @Override
    public PresentationView getInput(String input) {
        return new AdminView(this.clientService, this.currentUser);
    }
}
