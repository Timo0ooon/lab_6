package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;

import java.util.Hashtable;

public class Clear implements Command {
    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", false);

        collection.clear();
        return new Response("Collection cleared!", true);
    }
}