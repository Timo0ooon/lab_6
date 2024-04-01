package com.ClientServerApp.CommandManager.Commands.ExecuteScript;


import com.ClientServerApp.Request.Request;

import java.io.BufferedReader;
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

        try (
                FileReader fileReader = new FileReader("Client\\src\\main\\java\\com\\ClientServerApp\\CommandManager\\Commands\\ExecuteScript\\commands.csv");
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
