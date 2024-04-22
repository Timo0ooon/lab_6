package com.ClientServerApp.Response;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 112L;

    private Object object;
    private String message;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public Response(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

