package com.bsuir.sementsova.server.main.server.handle;

import com.bsuir.sementsova.client.entity.employee.EmployeeRequest;
import com.bsuir.sementsova.client.entity.employee.EmployeeResponse;
import com.bsuir.sementsova.server.dao.ServerDAOFactory;
import com.bsuir.sementsova.server.main.server.controller.ClientController;
import com.bsuir.sementsova.server.service.ServerService;
import com.bsuir.sementsova.server.service.impl.ServerServiceImpl;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final ClientController controller;

    public ClientHandler(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        ServerService service = new ServerServiceImpl(ServerDAOFactory.getInstance().getServerDAO());
        this.controller = new ClientController(service);
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                EmployeeRequest request = (EmployeeRequest) this.in.readObject();
                EmployeeResponse response = switch (request.getRequestType()) {
                    case CREATE -> this.controller.create(request);
                    case GET -> this.controller.get(request);
                    case GET_ALL -> this.controller.getAll();
                    case EDIT -> this.controller.edit(request);
                    case REGISTER -> this.controller.register(request);
                    case LOGIN -> this.controller.login(request);
                    default -> this.controller.notFound();
                };

                this.out.writeObject(response);
                this.out.flush();
            }

        } catch (EOFException ignored) {
            // End of socket stream.
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Failed read: %s%n", e.getMessage());
        }
    }
}
