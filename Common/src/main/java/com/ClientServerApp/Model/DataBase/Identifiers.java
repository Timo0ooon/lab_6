package com.ClientServerApp.Model.DataBase;

import java.util.ArrayList;

public class Identifiers {
    private static final ArrayList<Integer> idList = new ArrayList<>();

    public static void add(Integer id) {
        idList.add(id);
    }

    public static Integer generate() {
        int newID = 0;

        if (!idList.isEmpty())
            newID = idList.get(idList.size() - 1) + 1;

        idList.add(newID);

        return newID;
    }

    public static ArrayList<Integer> get() { return idList; }
    public static void delete(Integer ID) {
        idList.remove(ID);
    }
}
