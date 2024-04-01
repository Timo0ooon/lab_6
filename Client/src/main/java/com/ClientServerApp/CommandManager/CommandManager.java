package com.ClientServerApp.CommandManager;

import com.ClientServerApp.CommandManager.Commands.Command;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.AddCommandsToFile;
import com.ClientServerApp.CommandManager.Commands.ExecuteScript.ExecuteScript;
import com.ClientServerApp.CommandManager.Commands.Exit;
import com.ClientServerApp.CommandManager.Commands.Help.Help;

import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandManager() {
        commands.put("add_commands_to_file", new AddCommandsToFile());
        commands.put("help", new Help());
        commands.put("exit", new Exit());
    }

    public void find(String userLine) {
        if (this.commands.containsKey(userLine.toLowerCase()))
            commands.get(userLine.toLowerCase()).execute();
        else
            System.out.println("Unknown command!");
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
