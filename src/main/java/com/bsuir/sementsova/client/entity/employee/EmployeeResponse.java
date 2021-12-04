package com.bsuir.sementsova.client.entity.employee;

import com.bsuir.sementsova.client.entity.request.ResponseType;

import java.io.Serializable;

public class EmployeeResponse implements Serializable {
    private ResponseType responseType;
    private Object body;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
