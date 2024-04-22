package com.ClientServerApp.CommandManager;

import com.ClientServerApp.CommandManager.Commands.Command;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.AddCommandsToFile;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.LoadScript;
import com.ClientServerApp.CommandManager.Commands.Exit;
import com.ClientServerApp.CommandManager.Commands.Help.Help;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.MyConsts;
import com.ClientServerApp.Request.Request;
import com.ClientServerApp.Response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandManager() {
        commands.put("add_commands_to_file", new AddCommandsToFile());
        commands.put("help", new Help());
        commands.put("exit", new Exit());
    }

    public Response find(String userLine, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        Response response = null;
        userLine = userLine.toLowerCase();

        if (MyConsts.commandsOnClient.contains(userLine.toLowerCase())) {
            commands.get(userLine.toLowerCase()).execute();
            response = new Response("Done!", true);
        }
        else if (MyConsts.hybridCommands.contains(userLine)) {
            for (Request request: LoadScript.makeRequests()) {

                objectOutputStream.writeObject(request);
                objectOutputStream.flush();

                response = (Response) objectInputStream.readObject();

            }
        }
        else if (MyConsts.commandsOnServer.contains(userLine.split(" ")[0].trim())) {
            Request request;
            String command = userLine.split(" ")[0];
            if (command.equals("insert") || command.equals("update_by_id"))
                request = new Request(userLine, new HumanBeing());
            else
                request = new Request(userLine);

            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            response = (Response) objectInputStream.readObject();
        }
        return response;
    }
}
