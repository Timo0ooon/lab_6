package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public interface CommandWithTwoArguments {
    String execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user);
}
