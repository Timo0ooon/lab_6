package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

public class Insert implements CommandWithTwoArguments {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user) {
        try {
            Integer key = Integer.parseInt(argument);

            collection.put(key, user);

            return "Person[" + argument + "]" + " added!";
        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }
        return "Error! Argument must be a number!";

    }
}