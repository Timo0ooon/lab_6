package com.ClientServerApp.DataProvider.Writer;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Hashtable;

public class CSVWriter implements FileWriter{
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    @Override
    public boolean write(Hashtable<Integer, HumanBeing> collection, String filePath) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(collection);
            this.logger.info("[Server]: Collection saved!");
            return true;

        }

        catch (IOException e) {
            this.logger.error("[Server]: " + e.getMessage());
            return false;
        }
    }
}

