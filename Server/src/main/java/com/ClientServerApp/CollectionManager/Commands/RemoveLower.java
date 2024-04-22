package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Hashtable;


public class RemoveLower implements CommandWithArgument {
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", false);

        try {
            Integer key = Integer.parseInt(argument);
            if (!collection.containsKey(key))
                return new Response("Key is not in collection!", false);

            ArrayList<Integer> idList = new ArrayList<>();
            ArrayList<Integer> keys = new ArrayList<>();

            for (Integer keyValue: collection.keySet()) {
                HumanBeing human = collection.get(keyValue);
                if (human.getId()  < collection.get(key).getId()) {
                    idList.add(human.getId());
                    keys.add(keyValue);
                }
            }

            for (Integer keyValue: keys) {
                collection.remove(keyValue);
            }

            for (Integer id: idList) {
                Identifiers.delete(id);
            }

            return new Response("Deleted!", true);

        }
        catch (NumberFormatException e) {
            this.logger.error("[Server]: " + e.getMessage());
        }

        return new Response("Error!", false);
    }
}
