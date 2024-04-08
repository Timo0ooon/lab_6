package com.ClientServerApp.Client;

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
                System.out.print("[Message] \t\tWrite a number to work with file: ");
                Integer value = Integer.valueOf(input());
                if (choice.containsKey(value)) {
                    fileName = choice.get(value);
                    break;
                }
                System.out.println("[Error]  \t\tThe number must be from the list!");
            }
            catch (NumberFormatException e) {
                System.out.println("[Error]  \t\tValue must be a number!");
            }
        }
        return fileName;
    }
}
