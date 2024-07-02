package bitcamp.project2.command;

import bitcamp.project2.util.Calender;
import bitcamp.project2.util.Prompt;
import java.time.LocalDate;
import java.util.ArrayList;
import bitcamp.project2.util.DailyList;


public class ToDoListCommand {
    public ArrayList<DailyList> dailyLists = new ArrayList<>();
    private Calender calender;


    public void addSchedule() {
        while (true) {
            Calender.showCalendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());

            DailyList dailyList = new DailyList();

            try {
                String dateStr = Prompt.input("추가하고자 하는 날짜를 입력해주세요 (yyyy-MM-dd): ");
                dailyList.setDate(java.sql.Date.valueOf(dateStr));
            } catch (Exception e) {
                System.out.println("올바른 날짜 형식이 아닙니다.");
                continue;
            }

            try {
                String timeStr = Prompt.input("시간을 입력하세요 (HH:mm): ");
                dailyList.setTime(java.sql.Time.valueOf(timeStr + ":00"));
            } catch (Exception e) {
                System.out.println("올바른 시간 형식이 아닙니다.");
                continue;
            }

            dailyList.setContent(Prompt.input("상세 일정을 추가해주세요: "));
            dailyList.setNo(dailyLists.size());

            dailyLists.add(dailyList);

            String answer = Prompt.input("더 추가하시겠습니까? (y/n): ");
            if (answer.toLowerCase().equals("n")) {
                System.out.println("일정이 등록되었습니다.");
                break;
            }
        }
    }

}

