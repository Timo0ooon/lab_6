package com.ClientServerApp.Client;

import com.ClientServerApp.CommandManager.CommandManager;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.ExecuteScript;
import com.ClientServerApp.Model.DataBase.Identifiers;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static com.ClientServerApp.MyInput.MyInput.input;

public class Client {
    private static final CommandManager commandManager = new CommandManager();

    public void run() {

        try (
                Socket socket = UserConnection.connect();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ) {
            ArrayList<Integer> idList = (ArrayList<Integer>) objectInputStream.readObject();
            for (int id: idList) {
                Identifiers.add(id);
            }

            System.out.println("[Message] \t\tWrite 'help' to see commands");

            while (true) {
                System.out.print("[Message] \t\tWrite command: ");
                String userLine = input().toLowerCase();

                if (userLine.equals("execute_script")) {
                    for (Request request: ExecuteScript.makeRequest()) {
                        String command = request.getCommand();

                        if (commandManager.getCommands().containsKey(command))
                            commandManager.find(command);
                        else {
                            String onlyCommand = command.split(" ")[0];
                            if (onlyCommand.equals("insert") || onlyCommand.equals("update_by_id") )
                                request = new Request(request.getCommand(), new HumanBeing());

                            objectOutputStream.writeObject(request);
                            objectOutputStream.flush();

                            String response = (String) objectInputStream.readObject();
                            System.out.println(response);
                        }
                    }
                }

                else if (commandManager.getCommands().containsKey(userLine))
                    commandManager.find(userLine);

                else {
                    Request request;
                    String command = userLine.split(" ")[0];
                    if (command.equals("insert") || command.equals("update_by_id"))
                        request = new Request(userLine, new HumanBeing());
                    else
                        request = new Request(userLine);

                    objectOutputStream.writeObject(request);
                    objectOutputStream.flush();

                    String response = (String) objectInputStream.readObject();
                    System.out.println("[Message] \t\t" + response);

                }
            }
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println("[Error]  \t\t" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
