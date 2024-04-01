package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.HashMap;
import java.util.Hashtable;

public interface Command {
    public String execute(Hashtable<Integer, HumanBeing> collection);
}
