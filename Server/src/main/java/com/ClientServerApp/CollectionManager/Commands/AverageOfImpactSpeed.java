package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;
import java.util.List;

public class AverageOfImpactSpeed implements Command {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        if (collection.isEmpty())
            return "Collection is empty!";

        List<Integer> values = collection.values().stream().map(HumanBeing::getImpactSpeed).toList();

        if (!values.isEmpty())
            return "Average of impact speed: " + values.stream().reduce(Integer::sum).get() / values.size();

        return "Collection is empty!";
    }
}