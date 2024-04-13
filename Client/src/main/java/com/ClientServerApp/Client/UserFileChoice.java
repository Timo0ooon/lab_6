package com.ClientServerApp.Client;

import com.ClientServerApp.Write;

import java.util.HashMap;

import static com.ClientServerApp.MyInput.MyInput.input;

public class UserFileChoice {
    public static String execute(HashMap<Integer, String> choice) {

        StringBuilder userChoice = new StringBuilder("Files:\n");
        for (Integer key: choice.keySet()) {
            userChoice.append("\t\t").append(key).append(". ").append(choice.get(key)).append("\n");
        }
        System.out.println(userChoice);

        String fileName;
        while (true) {
            try {
                Write.writeMessage("Write a number to work with file: ");
                Integer value = Integer.valueOf(input());
                if (choice.containsKey(value)) {
                    fileName = choice.get(value);
                    break;
                }
                Write.writeError("The number must be from the list!");
            }
            catch (NumberFormatException e) {
                Write.writeError("The value must be Integer!");
            }
        }
        return fileName;
    }
}
