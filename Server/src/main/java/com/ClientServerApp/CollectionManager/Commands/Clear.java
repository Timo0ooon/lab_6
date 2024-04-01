package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class Clear implements Command {
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        collection.clear();
        return "Collection cleared!";
    }
}