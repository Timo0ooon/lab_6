package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class UpdateByID implements CommandWithTwoArguments {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", false);

        try {
            Integer id = Integer.parseInt(argument);
            if (collection.values().stream().filter( el -> el.getId().equals(id)).toList().isEmpty())
                return new Response("ID not found", false);

            Integer userKey = collection.keySet().stream().filter(key -> collection.get(key).getId().equals(id)).toList().get(0);
            Identifiers.delete(id);
            collection.put(userKey, user);
            return new Response("Updated!", true);

        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }

        return new Response("Error!", false);

    }
}
