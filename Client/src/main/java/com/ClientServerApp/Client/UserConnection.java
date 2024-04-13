package com.ClientServerApp.Client;

import com.ClientServerApp.Write;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import static com.ClientServerApp.MyInput.MyInput.input;

public class UserConnection {
    public static Socket connect() {
        while (true) {
            try {
                System.out.print("[Message] \t\tWrite port to connect to server: ");
                String userLine = input();
                int port = Integer.parseInt(userLine);

                try {
                    Socket socket = new Socket("localhost", port);
                    Write.writeMessage("Connected!\n");

                    return socket;

                }
                catch (ConnectException e) {
                    Write.writeError("Wrong port!");
                }
                catch (IOException e) {
                    Write.writeError(e.getMessage());
                }
            }
            catch(NumberFormatException e) {
                Write.writeError("Value must be integer!");
            }
        }
    }
}
