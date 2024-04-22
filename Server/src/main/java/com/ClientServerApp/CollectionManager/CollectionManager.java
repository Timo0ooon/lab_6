package com.ClientServerApp.CollectionManager;

import com.ClientServerApp.CollectionManager.Commands.*;
import com.ClientServerApp.DataProvider.Reader.CSVReader;
import com.ClientServerApp.Model.HumanBeing.HumanBeing;
import com.ClientServerApp.Request.Request;
import com.ClientServerApp.Response.Response;
import com.ClientServerApp.Server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class CollectionManager {

    private Hashtable<Integer, HumanBeing> collection;
    private final HashMap<String, Command> commands = new HashMap<>();
    private final HashMap<String, CommandWithArgument> commandsWithArguments = new HashMap<>();
    private final HashMap<String, CommandWithTwoArguments> commandsWithTwoArguments = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    private final ArrayList<Integer> idList = new ArrayList<>();

    public CollectionManager(String fileName) {
        CSVReader reader = new CSVReader();
        final String separator = File.separator;

        this.collection = reader.read("Server" + separator + "src" + separator + "main" + separator +
                "java" + separator + "com" + separator + "ClientServerApp" + separator + "Data" + separator + fileName);
        if (this.collection == null)
            this.collection = new Hashtable<>();

        idList.addAll(this.collection.values().stream().map(HumanBeing::getId).toList());

        this.logger.info("[Server]: collection loaded!");

        this.commands.put("average_of_impact_speed", new AverageOfImpactSpeed());
        this.commands.put("clear", new Clear());
        this.commands.put("info", new Info());
        this.commands.put("save", new Save(fileName));
        this.commands.put("show", new Show());
        this.commands.put("max_by_impact_speed", new MaxByImpactSpeed());

        this.commandsWithArguments.put("remove_key", new RemoveKey());
        this.commandsWithArguments.put("remove_greater", new RemoveGreater());
        this.commandsWithArguments.put("remove_lower", new RemoveLower());

        this.commandsWithTwoArguments.put("update_by_id", new UpdateByID());
        this.commandsWithTwoArguments.put("insert", new Insert());
    }

    public Response findCommand(Request request) {

        HumanBeing human = request.getHuman();
        String userLine = request.getCommand();

        this.logger.info("[Server]: finds command: " + userLine);

        if (userLine.isEmpty() || userLine.replaceAll(" ", "").isEmpty())
            return new Response("Unknown command!", false);

        String[] userLines = userLine.split(" ");
        String command = userLines[0];
        String argument = null;
        if (userLines.length == 2) {
            argument = userLines[1].replaceAll("\\{", "").replaceAll("}", "");
        }

        if (commands.containsKey(command))
            return this.commands.get(command).execute(this.collection);

        else if (commandsWithArguments.containsKey(command))
            return this.commandsWithArguments.get(command).execute(this.collection, argument);

        else if (commandsWithTwoArguments.containsKey(command))
            return this.commandsWithTwoArguments.get(command).execute(this.collection, argument, human);

        return new Response("Unknown command!", false);
    }

    public ArrayList<Integer> getIdList() { return idList; }
}
