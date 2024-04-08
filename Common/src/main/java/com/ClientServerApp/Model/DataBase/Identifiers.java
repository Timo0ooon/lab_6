package com.ClientServerApp.Model.DataBase;

import java.util.TreeSet;

public class Identifiers {
    private static final TreeSet<Integer> idSet = new TreeSet<>();

    public static void add(Integer id) {
        idSet.add(id);
    }

    public static Integer generate() {
        int newID = 0;

        if (!idSet.isEmpty()) {
            newID = idSet.last() + 1;
        }

        idSet.add(newID);

        return newID;
    }

    public static TreeSet<Integer> get() { return idSet; }
    public static void delete(Integer ID) {
        idSet.remove(ID);
    }
}
