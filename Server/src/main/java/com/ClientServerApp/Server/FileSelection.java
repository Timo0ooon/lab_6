package com.ClientServerApp.Server;

import com.ClientServerApp.Response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.io.File.separator;

public class FileSelection {
    public static Response execute() {
        final File file = new File("Server" + separator + "src" + separator + "main" + separator +
                "java" + separator + "com" + separator + "ClientServerApp" + separator + "Data" + separator);

        final HashMap<Integer, String> choice = new HashMap<>();
        final List<File> fileList = List.of(Objects.requireNonNull(file.listFiles()));

        for (int i = 0; i < fileList.size(); i++) {
            choice.put(i, fileList.get(i).getName());
        }

        return new Response(choice);
    }

    public static void main(String[] args) {
        System.out.println(FileSelection.execute());
    }
}
