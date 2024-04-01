package com.ClientServerApp.Client;

import com.ClientServerApp.CommandManager.CommandManager;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.ExecuteScript;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.ClientServerApp.MyInput.MyInput.input;

public class Client {
    private static final CommandManager commandManager = new CommandManager();

    public void run() {
        int port;

        while (true) {
            try {
                System.out.print("Write port to connect to server: ");
                port = Integer.parseInt(input());
                break;
            }

            catch(NumberFormatException e) {
                System.out.print("Value must be integer! ");
            }
        }

        try (
                Socket socket = new Socket("localhost", port);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ) {
            System.out.println("Write 'help' to see commands");

            while (true) {
                System.out.print("Write command: ");
                String userLine = input().toLowerCase();

                if (userLine.equals("execute_script")) {
                    for (Request request: ExecuteScript.makeRequest()) {
                        String command = request.getCommand();

                        if (commandManager.getCommands().containsKey(command))
                            commandManager.find(command);
                        else {
                            if (request.getCommand().split(" ")[0].equals("insert"))
                                request = new Request("insert", new HumanBeing());

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
                    if (userLine.split(" ")[0].equals("insert"))
                        request = new Request(userLine, new HumanBeing());
                    else
                        request = new Request(userLine);

                    objectOutputStream.writeObject(request);
                    objectOutputStream.flush();

                    String response = (String) objectInputStream.readObject();
                    System.out.println(response);

                }
            }
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
