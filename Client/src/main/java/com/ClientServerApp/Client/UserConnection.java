package com.ClientServerApp.Client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import static com.ClientServerApp.MyInput.MyInput.input;

public class UserConnection {
    public static Socket connect() {
        while (true) {
            try {
                System.out.print("[Message] \t\tWrite port to connect to server: ");
                int port = Integer.parseInt(input());

                try {
                    Socket socket = new Socket("localhost", port);
                    System.out.println("[Message] \t\tConnected!");

                    return socket;

                }
                catch (ConnectException e) {
                    System.out.println("[Error]  \t\tWrong port!");
                }
                catch (IOException e) {
                    System.out.println("[Error] " + "\t\t" + e.getMessage());
                }
            }
            catch(NumberFormatException e) {
                System.out.println("[Error]   \t\tValue must be integer! ");
            }
        }
    }
}
