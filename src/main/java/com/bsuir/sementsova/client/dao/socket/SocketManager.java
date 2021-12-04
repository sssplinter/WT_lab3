package com.bsuir.sementsova.client.dao.socket;

import com.bsuir.sementsova.client.entity.employee.EmployeeRequest;
import com.bsuir.sementsova.client.entity.employee.EmployeeResponse;
import com.bsuir.sementsova.client.entity.request.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public record SocketManager(String ip, int port) {

    public EmployeeResponse sendRequest(Object body, RequestType type) {
        Socket client = null;
        try {
            try {
                client = new Socket(this.ip, this.port);

                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());

                EmployeeRequest request = new EmployeeRequest();
                request.setBody(body);
                request.setRequestType(type);

                outputStream.writeObject(request);
                outputStream.flush();

                return (EmployeeResponse) inputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.printf("Error client: %s%n", e.getMessage());
            } finally {
                if ((client != null) && !client.isClosed()) {
                    client.close();
                }
            }

        } catch (IOException e) {
            System.out.printf("Error client: %s%n", e.getMessage());
        }

        return null;
    }
}
