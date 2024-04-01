package com.ClientServerApp.Server;

import com.ClientServerApp.CollectionManager.CollectionManager;
import com.ClientServerApp.Request.Request;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final CollectionManager collectionManager = new CollectionManager("data.csv");
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(8052)
        ) {
            logger.info("[Server]: port is " + serverSocket.getLocalPort());

            while (true) {
                Socket userSocket = serverSocket.accept();
                try(
                        ObjectInputStream objectInputStream = new ObjectInputStream(userSocket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
                ) {

                    while (true) {
                        logger.info("[Server]: Waiting request...");
                        Request request = (Request) objectInputStream.readObject();
                        String response = collectionManager.findCommand(request);

                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                        logger.info("[Server]: Response sent!");
                    }
                }
                catch (IOException e) {
                    logger.error("[Server]: " + e.getMessage(), e);
                }
            }
        }

        catch (IOException | ClassNotFoundException e) {
            logger.error("[Server]: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("Server\\src\\main\\resources\\log4j.properties");
        Server server = new Server();
        server.run();
    }
}