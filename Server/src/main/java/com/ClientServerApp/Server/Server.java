package com.ClientServerApp.Server;

import com.ClientServerApp.CollectionManager.CollectionManager;
import com.ClientServerApp.Request.Request;
import com.ClientServerApp.Response.Response;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.io.File.separator;

/**
 * Server class processes requests. After the client connects it does:
 * 1) sends a list of files that you can work with.
 * 2) sends a list of busy identifiers.
 * 3) waits for commands from the client to execute them
 */
public class Server {
    private final Logger logger = LoggerFactory.getLogger(Server.class);


    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(0)
        ) {

            String ANSI_BOLD = "\u001B[1m";
            String ANSI_RESET = "\u001B[0m";

            while (true) {
                this.logger.info("[Server]: port is " + ANSI_BOLD + serverSocket.getLocalPort() + ANSI_RESET);

                Socket userSocket = serverSocket.accept();
                this.logger.info("[Server]: user connected!");

                try(
                        ObjectInputStream objectInputStream = new ObjectInputStream(userSocket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
                ) {
                    Response choice = FileSelection.execute();
                    objectOutputStream.writeObject(choice);
                    objectOutputStream.flush();

                    String fileName = (String) objectInputStream.readObject();


                    CollectionManager collectionManager = new CollectionManager(fileName);


                    objectOutputStream.writeObject( new Response(collectionManager.getIdList()) );
                    objectOutputStream.flush();

                    while (true) {
                        this.logger.info("[Server]: waiting request...");
                        Request request = (Request) objectInputStream.readObject();

                        Response response = collectionManager.findCommand(request);

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
        PropertyConfigurator.configure("Server" + separator + "src"  + separator +  "main"
                + separator + "resources"  + separator + "log4j.properties");
        Server server = new Server();
        server.run();
    }
}