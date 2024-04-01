package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class UpdateByID implements CommandWithTwoArguments {

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user) {
        if (collection.isEmpty())
            return "Collection is empty!";

        try {
            Integer id = Integer.parseInt(argument);
            if (collection.values().stream().filter( el -> el.getId().equals(id)).toList().isEmpty())
                return "ID not found";

            Integer userKey = collection.keySet().stream().filter(key -> collection.get(key).getId().equals(id)).toList().get(0);
            Identifiers.delete(id);
            collection.put(userKey, user);
            return "Updated!";

        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return "Error!";

    }
}
