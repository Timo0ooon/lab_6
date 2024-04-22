package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class Insert implements CommandWithTwoArguments {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user) {
        try {
            Integer key = Integer.parseInt(argument);

            collection.put(key, user);

            return new Response("Person[" + argument + "]" + " added!", false);
        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }
        return new Response("Error! Argument must be a number!", true);

    }
}