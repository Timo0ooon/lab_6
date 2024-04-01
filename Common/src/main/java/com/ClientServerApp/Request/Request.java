package com.ClientServerApp.Request;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 111L;
    private final String command;
    private HumanBeing human;

    public Request(String command) {
        this.command = command.toLowerCase();
    }

    public Request(String command, HumanBeing human) {
        this.command = command;
        this.human = human;
    }

    public String getCommand() { return command; }

    public HumanBeing getHuman() {
        return human;
    }

    public void setHuman(HumanBeing human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", human=" + human +
                '}';
    }
}