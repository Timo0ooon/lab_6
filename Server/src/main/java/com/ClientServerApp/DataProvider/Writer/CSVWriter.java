package com.ClientServerApp.DataProvider.Writer;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.io.*;
import java.util.Hashtable;

public class CSVWriter implements FileWriter{
    @Override
    public void write(Hashtable<Integer, HumanBeing> collection, String filePath) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(collection);
            System.out.println("Collection saved!");

        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

