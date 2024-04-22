package com.ClientServerApp.Client;

import com.ClientServerApp.Write;

import java.io.*;
import static java.io.File.separator;

public class Welcome {
    public static String execute() {
        try (BufferedReader bufferedReader = new BufferedReader( new FileReader("Client" + separator + "src" + separator + "main"
                + separator + "java" + separator + "com" + separator + "ClientServerApp" + separator + "Client" + separator + "Greetings.txt"))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder("\n\n\n");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return String.valueOf(stringBuilder);
        }

        catch (IOException e) {
            Write.writeError(e.getMessage());
        }
        return "";
    }
}
