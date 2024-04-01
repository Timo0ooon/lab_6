package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class RemoveGreater implements CommandWithArgument{
    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection, String argument) {
        return null;
    }
}
