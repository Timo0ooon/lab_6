package com.ClientServerApp.CommandManager.Commands.ExecuteScript;


import com.ClientServerApp.Request.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExecuteScript {
    public static ArrayList<Request> makeRequest() {
        ArrayList<String> commands = load();
        ArrayList<Request> requests = new ArrayList<>();

        for (String command: commands) {
            requests.add(new Request(command));
        }
        return requests;
    }

    public static ArrayList<String> load() {
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
            System.out.println(e.getMessage());
        }

        return commands;
    }
}
