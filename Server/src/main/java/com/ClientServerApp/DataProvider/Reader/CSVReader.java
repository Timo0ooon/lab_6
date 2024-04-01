package com.ClientServerApp.DataProvider.Reader;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.io.*;
import java.util.*;

public class CSVReader implements FileReader {
    @Override
    public Hashtable<Integer, HumanBeing> read(String filePath) {
        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            return (Hashtable<Integer, HumanBeing>) objectInputStream.readObject();
        }

        catch (IOException | ClassNotFoundException e) {
            String message = e.getMessage();
            if (message == null)
                System.out.println("File is empty");
            else
                System.out.println(message);
        }

        return null;
    }
}

