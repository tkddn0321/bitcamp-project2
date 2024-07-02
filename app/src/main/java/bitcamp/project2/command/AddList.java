package bitcamp.project2.command;

import bitcamp.project2.util.DailyLIst;
import bitcamp.project2.util.Prompt;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class AddList {
    HashMap<LocalDate, ArrayList<DailyLIst>> testLIst = HashList.getToDoList();

    public void listAdd()
    {
        int intDay = Prompt.inputInt("추가하고자 하는 날자를 입력해주세요 > ");
        String stringDay = Integer.toString(intDay);
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
        LocalDate day = LocalDate.parse(stringDay, formatterDay);
        while (true)
        {
            String timeString = Prompt.input("상세 일정을 추가합니다 시간을 입력해 주세요 ex) 14:00 > ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(timeString, formatter);
            String content = Prompt.input("내용을 입력해 주세요 > ");
            DailyLIst dailyLIst = new DailyLIst(time, content);
            addEvent(day, dailyLIst);
            String more = Prompt.input("더 추가하시겠습니까? (y/n) > ");
            if (more.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
    public void addEvent(LocalDate day, DailyLIst dailyLIst) {
        ArrayList<DailyLIst> events = testLIst.get(day);
        if (events == null) {
            // 해당 날에 일정이 없으면 새로운 리스트를 생성하여 추가
            events = new ArrayList<>();
            testLIst.put(day, events);
        }
        // 일정 추가
        events.add(dailyLIst);
    }
}
