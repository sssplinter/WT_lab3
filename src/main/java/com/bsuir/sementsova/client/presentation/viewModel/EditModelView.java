package com.bsuir.sementsova.client.presentation.viewModel;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditModelView extends PresentationModel {
    private final int id;

    public EditModelView(ClientService studentService, int id) {
        super(studentService);
        this.id = id;
    }

    @Override
    public List<Employee> getItems() {
        Employee result = this.clientService.get(this.id);
        return result == null ? new ArrayList<>() : Collections.singletonList(result);
    }
}
