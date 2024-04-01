package com.ClientServerApp.Server;

import com.ClientServerApp.CollectionManager.CollectionManager;
import com.ClientServerApp.Request.Request;
import org.w3c.dom.ls.LSOutput;

import java.net.*;
import java.io.*;


public class Server {
    private static final CollectionManager collectionManager = new CollectionManager();

    public static void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(0)
        ) {
            System.out.println("[Server]: port is " + serverSocket.getLocalPort());

            while (true) {
                Socket userSocket = serverSocket.accept();
                try(
                        ObjectInputStream objectInputStream = new ObjectInputStream(userSocket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
                ) {

                    while (true) {
                        System.out.println("[Server]: Waiting request...");
                        Request request = (Request) objectInputStream.readObject();
                        String response = collectionManager.findCommand(request);

                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                        System.out.println("[Server]: Response sent!");
                    }
                }
                catch (IOException e) {
                    System.out.println("[Server]: " + e.getMessage());
                }
            }
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println("[Server]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("userCommand");
        processBuilder.environment().put("pathData", "data.csv");
        Server.run();
    }

}