package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Model.DataBase.Identifiers;

import java.util.Hashtable;

public class RemoveKey implements CommandWithArgument {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        try {
            Integer key = Integer.parseInt(argument);
            if (!collection.containsKey(key))
                return "Key is not in collection!";

            Identifiers.delete(collection.get(key).getId());
            collection.remove(key);

            return "Deleted!";

        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return "Error!";
    }
}