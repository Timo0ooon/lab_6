package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public interface CommandWithArgument {
    String execute(Hashtable<Integer, HumanBeing> collection, String argument);
}
