package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class MaxByImpactSpeed implements Command{
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        return collection.values().stream().
                max((firstHuman, secondHuman) -> firstHuman.getImpactSpeed().compareTo(secondHuman.getImpactSpeed())).
                get().toString();
    }
}
