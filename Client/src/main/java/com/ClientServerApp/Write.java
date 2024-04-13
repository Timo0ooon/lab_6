package com.ClientServerApp;

public class Write {
    public static void writeMessage(String line) {
        System.out.print("[Message]  \t\t" + line);
    }
    public static void writeError(String line) { System.out.println("[Error]  \t\t" + line); }
}
