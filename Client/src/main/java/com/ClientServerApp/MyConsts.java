package com.ClientServerApp;

import java.io.*;
import java.util.ArrayList;

public class MyConsts {
    public static final ArrayList<String> commandsOnServer = new ArrayList<>();
    public static final ArrayList<String> commandsOnClient = new ArrayList<>();
    public static final ArrayList<String> hybridCommands = new ArrayList<>();
    static {
        String separator = File.separator;
        try (
                FileReader fileReader = new FileReader("Client" + separator +
                "src" + separator + "main" + separator + "java" + separator + "com" + separator +
                "ClientServerApp" + separator + "CommandManager" + separator + "Commands" +
                separator + "Help" + separator + "help.csv");

                BufferedReader bufferedReader = new BufferedReader(fileReader);

             ) {
            String commandHelpLine;
            while ((commandHelpLine = bufferedReader.readLine()) != null) {
                String[] values = commandHelpLine.split(" ");
                if (values.length > 3) {
                    switch (values[0]) {
                        case "[Server]" -> commandsOnServer.add(values[1]);
                        case "[Client]" -> commandsOnClient.add(values[1]);
                        case "[Hybrid]" -> hybridCommands.add(values[1]);
                    }

                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(MyConsts.commandsOnClient);
    }
}
