package com.ClientServerApp.DataProvider.Writer;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public interface FileWriter {
    public boolean write(Hashtable<Integer, HumanBeing> collection, String filePath);
}
