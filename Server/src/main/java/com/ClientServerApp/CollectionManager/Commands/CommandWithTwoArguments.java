package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Response.Response;

import java.util.Hashtable;

public interface CommandWithTwoArguments {
    Response execute(Hashtable<Integer, HumanBeing> collection, String argument, HumanBeing user);
}
