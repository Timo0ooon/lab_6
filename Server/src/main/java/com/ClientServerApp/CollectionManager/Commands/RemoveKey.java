package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class RemoveKey implements CommandWithArgument {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", false);

        try {
            Integer key = Integer.parseInt(argument);
            if (!collection.containsKey(key))
                return new Response("Key is not in collection!", false);

            Identifiers.delete(collection.get(key).getId());
            collection.remove(key);

            return new Response("Deleted!", true);

        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }

        return new Response("Error!", false);
    }
}