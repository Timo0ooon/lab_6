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
            Object object = objectInputStream.readObject();
            Hashtable<Integer, HumanBeing> collection = new Hashtable<>();
            if (object instanceof Hashtable<?,?>
                    && ((Hashtable<?, ?>) object).keySet().stream().allMatch(el -> el instanceof Integer)
                    && ((Hashtable<?, ?>) object).values().stream().allMatch(el -> el instanceof HumanBeing)) {
                collection = (Hashtable<Integer, HumanBeing>) object;
            }
            return collection;
        }

        catch (IOException | ClassNotFoundException e) {
            String message = e.getMessage();
            if (message == null)
                this.logger.error("[Server]: File is empty!");
            else
                this.logger.error("[Server]: " + e.getMessage());
        }
        catch (Exception e) {
            this.logger.error("[Server]: Unknown error!");
        }

        return null;
    }
}

