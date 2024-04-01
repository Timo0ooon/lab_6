package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class RemoveKey implements CommandWithArgument {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        if (collection.isEmpty())
            return "Collection is empty!";

        try {
            Integer key = Integer.parseInt(argument);
            if (!collection.containsKey(key))
                return "Key is not in collection!";

            Identifiers.delete(collection.get(key).getId());
            collection.remove(key);

            return "Deleted!";

        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }

        return "Error!";
    }
}