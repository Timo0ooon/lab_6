package com.ClientServerApp.Model.DataBase;

import java.util.ArrayList;

public class Identifiers {
    public static ArrayList<Integer> idList = new ArrayList<>();

    public static Integer generate() {
        int newID = 0;

        if (!idList.isEmpty())
            newID = idList.get(idList.size() - 1) + 1;

        idList.add(newID);

        return newID;
    }

    public static boolean delete(Integer ID) {
        if (idList.contains(ID)) {
            idList.remove(ID);
            return true;
        }
        return false;
    }
}
