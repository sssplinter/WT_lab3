package com.bsuir.sementsova.client.presentation.viewModel;

import com.bsuir.sementsova.client.entity.employee.Employee;
import com.bsuir.sementsova.client.service.ClientService;

import java.util.List;

public abstract class PresentationModel {
    protected ClientService clientService;
    public PresentationModel(ClientService clientService) {
        this.clientService = clientService;
    }

    public abstract List<Employee> getItems();
}
