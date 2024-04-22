package com.ClientServerApp.Client;

import com.ClientServerApp.CommandManager.CommandManager;
import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Write;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import static com.ClientServerApp.MyInput.MyInput.input;

/**
 * Client class processes requests. After the client connects it does:
 * 1) receives a list of files that you can work with.
 * 2) receives a list of busy identifiers.
 * 3) executes commands or sends them to the server.
 */
public class Client {
    private static final CommandManager commandManager = new CommandManager();

    public void run() {

        try (
                Socket socket = UserConnection.connect();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())
        ) {
            Object object;
            Response response;

            System.out.println(Welcome.execute());

            //*****\\                                     [User File choice]                                     //*****\\
            response = (Response) objectInputStream.readObject();
            object = response.getObject();
            HashMap<Integer, String> choice = new HashMap<>();
            if (object instanceof HashMap<?, ?>
                    && ((HashMap<?, ?>) object).keySet().stream().allMatch(el -> el instanceof Integer)
                    && ((HashMap<?, ?>) object).values().stream().allMatch(el -> el instanceof String)) {
                choice = (HashMap<Integer, String>) object;
            }
            objectOutputStream.writeObject(UserFileChoice.execute(choice));
            objectOutputStream.flush();

            //*****\\                                     [Setting identifiers]                                   //*****\\
            response = (Response) objectInputStream.readObject();
            object = response.getObject();
            ArrayList<Integer> idList = new ArrayList<>();
            if (object instanceof ArrayList<?> && ((ArrayList<?>) object).stream().allMatch(el -> el instanceof Integer)) {
                idList = (ArrayList<Integer>) object;
            }

            for (int id: idList) {
                Identifiers.add(id);
            }

            Write.writeMessage("Write 'help' to see commands\n");


            //*****\\                                     [User is working]                                   //*****\\
            while (true) {
                Write.writeMessage("Write command: ");
                String userLine = input().toLowerCase();
                if (userLine.replaceAll(" ", "").isEmpty())
                    continue;

                response = commandManager.find(userLine, objectInputStream, objectOutputStream);
                if (response != null)
                    Write.writeMessage(response.getMessage() + "\n");
                else {
                    Write.writeError("Unknown command!");
                }
            }
        }

        catch (IOException | ClassNotFoundException e) {
            Write.writeError(e.getMessage());
        }
        catch (Exception e) {
            Write.writeError("Unknown error!");
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
