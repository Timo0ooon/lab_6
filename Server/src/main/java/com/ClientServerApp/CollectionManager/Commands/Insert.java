package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class Insert implements CommandWithTwoArguments {

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user) {
        try {
            Integer key = Integer.parseInt(argument);

            if (collection.containsKey(key))
                return "Such a key already exists!";

            collection.put(key, user);

            return "Person[" + argument + "]" + " added!";
        }
        catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return "";

    }
}