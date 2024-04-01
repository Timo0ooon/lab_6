package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.ArrayList;
import java.util.Hashtable;

public class RemoveLower implements CommandWithArgument {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        if (collection.isEmpty())
            return "Collection is empty!";

        try {
            Integer key = Integer.parseInt(argument);
            if (!collection.containsKey(key))
                return "Key is not in collection!";

            ArrayList<Integer> idList = new ArrayList<>();

            for (HumanBeing human: collection.values()) {
                if (human.getId() < collection.get(key).getId()) {
                    Identifiers.delete(collection.get(key).getId());
                    collection.remove(key);
                }
            }

            return "Deleted!";

        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return "Error!";
    }
}
