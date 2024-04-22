package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;

import java.util.Hashtable;

public class MaxByImpactSpeed implements Command{
    @Override
    public Response execute(Hashtable<Integer, HumanBeing> collection) {
        if (collection.isEmpty())
            return new Response("Collection is empty!", null);

        HumanBeing human = collection.values().stream().
                max((firstHuman, secondHuman) -> firstHuman.getImpactSpeed().compareTo(secondHuman.getImpactSpeed())).
                get();

        StringBuilder stringBuilder = new StringBuilder("\nPerson:\n");
        for (String field: human.toString().split("\n")) {
            stringBuilder.append("\t\t").append(field).append("\n");
        }
        return new Response(String.valueOf(stringBuilder), human);
    }
}
