package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.DataProvider.Writer.CSVWriter;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.util.Hashtable;

import static java.io.File.separator;

public class Save implements Command {
    String fileName;
    public Save(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        CSVWriter CSVWriter = new CSVWriter();


        CSVWriter.write(collection, "Server" + separator + "src" + separator + "main" + separator +
                "java" + separator + "com" + separator + "ClientServerApp" + separator + "Data" + separator + this.fileName);
        return "Saved!";
    }
}
