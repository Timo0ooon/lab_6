package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.DataProvider.Writer.CSVWriter;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

public class Save implements Command {

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        CSVWriter CSVWriter = new CSVWriter();
        CSVWriter.write(collection, "Server\\src\\main\\java\\com\\ClientServerApp\\Data\\data.csv");
        return "Saved!";
    }
}
