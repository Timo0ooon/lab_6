package com.ClientServerApp.CommandManager.Commands.Help;

import com.ClientServerApp.CommandManager.Commands.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Help implements Command {
    @Override
    public void execute() {
        try (
                FileReader fileReader = new FileReader("Client\\src\\main\\java\\com\\ClientServerApp\\CommandManager\\Commands\\Help\\help.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

        ) {
            StringBuilder stringBuilder = new StringBuilder("Commands:\n");
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