package com.ClientServerApp.CollectionManager.Commands;

import com.ClientServerApp.Model.HumanBeing.HumanBeing;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

public class Info implements Command {

    @Override
    public String execute(Hashtable<Integer, HumanBeing> collection) {
        List<LocalDate> dates = collection.values().stream().map(HumanBeing::getCreationDate).toList();
        LocalDate lastDay = dates.stream().max((el, el1) -> {
            if (el.equals(el1))
                return 0;
            else if (el.isBefore(el1))
                return -1;
            return 1;
        }).get();

        return "Type of collection: " + collection.getClass() + "\n" + "Size of collection: " + collection.size() + "\n" + "Last day of editing" + lastDay;
    }
}