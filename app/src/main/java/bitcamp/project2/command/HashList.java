package bitcamp.project2.command;

import bitcamp.project2.util.DailyLIst;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class HashList {
   private static HashMap<LocalDate, ArrayList<DailyLIst>> ToDoList = new HashMap<>();

    public static HashMap<LocalDate, ArrayList<DailyLIst>> getToDoList() {
        return ToDoList;
    }

    public void setToDoList(HashMap<LocalDate, ArrayList<DailyLIst>> toDoList) {
        ToDoList = toDoList;
    }
}
