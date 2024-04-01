package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class Show implements Command {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        StringBuilder info = new StringBuilder("Users:\n");

        collection.forEach((key, value) -> {
            info.append("User[").append(key).append("]:\n");
            info.append(value.toString()).append("\n");
        });

        return String.valueOf(info);
    }
}