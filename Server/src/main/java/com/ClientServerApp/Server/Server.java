package com.ClientServerApp.Server;

import com.ClientServerApp.CollectionManager.CollectionManager;
import com.ClientServerApp.Request.Request;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final String fileName;
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server(String fileName) { this.fileName = fileName; }

    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(0)
        ) {

            String ANSI_BOLD = "\u001B[1m";
            String ANSI_RESET = "\u001B[0m";

            this.logger.info("[Server]: port is " + ANSI_BOLD + serverSocket.getLocalPort() + ANSI_RESET);

            while (true) {
                CollectionManager collectionManager = new CollectionManager(this.fileName);
                Socket userSocket = serverSocket.accept();
                try(
                        ObjectInputStream objectInputStream = new ObjectInputStream(userSocket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
                ) {
                    objectOutputStream.writeObject(collectionManager.getIdList());
                    objectOutputStream.flush();

                    while (true) {
                        this.logger.info("[Server]: waiting request...");
                        Request request = (Request) objectInputStream.readObject();
                        String response = collectionManager.findCommand(request);

                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                        this.logger.info("[Server]: response sent!");
                    }
                }

                catch (IOException e) {
                    this.logger.error("[Server]: " + e.getMessage());
                }
            }
        }

        catch (IOException | ClassNotFoundException  e) {
            this.logger.error("[Server]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final String separator = File.separator;
        PropertyConfigurator.configure("Server" + separator + "src"  + separator +  "main"
                + separator + "resources"  + separator + "log4j.properties");
        Server server = new Server("data.csv");
        server.run();
    }
}