package com.ClientServerApp.CommandManager.Commands.Help;

import com.ClientServerApp.CommandManager.Commands.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Help implements Command {
    @Override
    public void execute() {
        final String separator = File.separator;

        try (
                FileReader fileReader = new FileReader("Client" + separator +
                        "src" + separator + "main" + separator + "java" + separator + "com" + separator +
                        "ClientServerApp" + separator + "CommandManager" + separator + "Commands" +
                        separator + "Help" + separator + "help.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

        ) {
            StringBuilder stringBuilder = new StringBuilder("[Commands]:\n");
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append("*").append("\t").append(line).append("\n");
            }
            System.out.println(stringBuilder);

        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}