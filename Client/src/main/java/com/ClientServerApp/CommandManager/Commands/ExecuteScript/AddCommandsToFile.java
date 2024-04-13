package com.ClientServerApp.CommandManager.Commands.ExecuteScript;

import com.ClientServerApp.CommandManager.Commands.Command;
import com.ClientServerApp.Write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.ClientServerApp.MyInput.MyInput.input;

public class AddCommandsToFile implements Command {
    @Override
    public void execute() {
        final String separator = File.separator;

        try (
                FileWriter fileWriter = new FileWriter("Client" + separator +
                        "src" + separator + "main" + separator + "java" + separator + "com" + separator +
                        "ClientServerApp" + separator + "CommandManager" + separator + "Commands" +
                        separator + "ExecuteScript" + separator + "commands.csv");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ) {

            for (String userLine: this.workWithUser()) {
                bufferedWriter.write(userLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }

        catch (IOException e) {
            Write.writeError(e.getMessage());
        }

    }

    private ArrayList<String> workWithUser() {
        System.out.println("Write 'break' to save commands");

        ArrayList<String> userCommands = new ArrayList<>();

        while (true) {
            System.out.print("Write command: ");
            String userLine = input();

            if (userLine.equalsIgnoreCase("break"))
                break;

            userCommands.add(userLine.toLowerCase());
        }

        return userCommands;
    }
}
