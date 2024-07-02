package bitcamp.project2.command;

import bitcamp.project2.util.DailyLIst;
import bitcamp.project2.util.Prompt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class FindList {
    HashMap<LocalDate, ArrayList<DailyLIst>> testLIst = HashList.getToDoList();
    public void listFind()
    {
        int intDay = Prompt.inputInt("찾고자하는 날자를 입력해주세요 > ");
        String stringDay = Integer.toString(intDay);
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
        LocalDate day = LocalDate.parse(stringDay, formatterDay);

        ArrayList<DailyLIst> events = testLIst.get(day);

        for(DailyLIst dailyLIst : events)
        {

            System.out.println(dailyLIst.getTime()+ "/" + dailyLIst.getContent());
        }
    }
}
