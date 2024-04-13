package com.ClientServerApp.CommandManager.Commands.ExecuteScript;


import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Request.Request;
import com.ClientServerApp.Write;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadScript {
    public static ArrayList<Request> makeRequests() {
        ArrayList<String> commands = load();
        ArrayList<Request> requests = new ArrayList<>();

        for (String command: commands) {
            command = command.toLowerCase();
            if (command.equals("insert") || command.equals("update_by_id"))
                requests.add(new Request(command, new HumanBeing()));
            else
                requests.add(new Request(command));
        }
        return requests;
    }

    private static ArrayList<String> load() {
        ArrayList<String> commands = new ArrayList<>();
        final String separator = File.separator;

        try (
                FileReader fileReader = new FileReader("Client" + separator +
                        "src" + separator + "main" + separator + "java" + separator + "com" + separator +
                        "ClientServerApp" + separator + "CommandManager" + separator + "Commands" +
                        separator + "ExecuteScript" + separator + "commands.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {

            String userCommand;

            while ((userCommand = bufferedReader.readLine()) != null) {
                commands.add(userCommand);
            }
        }

        catch (IOException e) {
            Write.writeError(e.getMessage());
        }

        return commands;
    }
}
