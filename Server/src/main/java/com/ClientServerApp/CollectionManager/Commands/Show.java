package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class Show implements Command {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        if (collection.isEmpty())
            return "Collection is empty!";

        StringBuilder info = new StringBuilder("\nUsers:\n");

        collection.forEach((key, value) -> {
            info.append("\t\t").append("User[").append(key).append("]:\n");
            StringBuilder user = new StringBuilder();
            for (String field: value.toString().split("\n")) {
                 user.append("\t\t\t\t").append(field).append("\n");
            }
            info.append(user).append("\n");
        });

        return String.valueOf(info);
    }
}