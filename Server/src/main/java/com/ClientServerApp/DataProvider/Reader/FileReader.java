package com.ClientServerApp.DataProvider.Reader;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public interface FileReader {
    public Hashtable<Integer, HumanBeing> read(String filePath);
}
