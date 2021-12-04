package com.bsuir.sementsova.client.presentation.view;

import com.bsuir.sementsova.client.entity.user.User;
import com.bsuir.sementsova.client.presentation.viewModel.PresentationModel;
import com.bsuir.sementsova.client.service.ClientService;

public abstract class PresentationView {
    protected PresentationModel model;
    protected ClientService clientService;
    protected User currentUser;

    protected PresentationView(ClientService clientService, User user) {
        this.clientService = clientService;
        this.currentUser = user;
    }

    public abstract void show();
    public abstract PresentationView getInput(String input);
}
