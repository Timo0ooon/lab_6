package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;

import java.util.Hashtable;
import java.util.List;

public class AverageOfImpactSpeed implements Command {

    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection) {

        if (collection.isEmpty())
            return new Response("Collection is empty!", null);

        List<Integer> values = collection.values().stream().map(HumanBeing::getImpactSpeed).toList();

        if (!values.isEmpty())
            return new Response("Average of impact speed: ", values.stream().reduce(Integer::sum).get() / values.size());

        return new Response("Collection is empty!", null);
    }
}