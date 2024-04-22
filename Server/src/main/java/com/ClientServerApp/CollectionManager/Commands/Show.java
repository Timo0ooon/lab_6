package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;

import java.util.Hashtable;

public class Show implements Command {
    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", null);

        StringBuilder info = new StringBuilder("\nUsers:\n");

        collection.forEach((key, value) -> {
            info.append("\t\t").append("User[").append(key).append("]:\n");
            StringBuilder user = new StringBuilder();
            for (String field: value.toString().split("\n")) {
                 user.append("\t\t\t\t").append(field).append("\n");
            }
            info.append(user).append("\n");
        });

        return new Response(String.valueOf(info), collection);
    }
}