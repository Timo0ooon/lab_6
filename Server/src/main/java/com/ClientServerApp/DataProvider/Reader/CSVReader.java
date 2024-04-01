package com.ClientServerApp.DataProvider.Reader;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class CSVReader implements FileReader {
    private final Logger logger = LoggerFactory.getLogger(Server.class);
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
                this.logger.error("[Server]: File is empty!");
            else
                this.logger.error("[Server]: " + e.getMessage());
        }

        return null;
    }
}

