package com.ClientServerApp.CommandManager.Commands.ExecuteScript;

import com.ClientServerApp.CommandManager.Commands.Command;
import static com.ClientServerApp.MyInput.MyInput.input;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddCommandsToFile implements Command {
    @Override
    public void execute() {
        try (
                FileWriter fileWriter = new FileWriter("Client\\src\\main\\java\\com\\ClientServerApp\\CommandManager\\Commands\\ExecuteScript\\commands.csv");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ) {

            for (String userLine: this.workWithUser()) {
                bufferedWriter.write(userLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
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
