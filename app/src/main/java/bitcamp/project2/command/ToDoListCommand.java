package bitcamp.project2.command;

import bitcamp.project2.App;
import bitcamp.project2.util.Calender;
import bitcamp.project2.util.Prompt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bitcamp.project2.util.DailyList;
import org.checkerframework.checker.units.qual.A;


public class ToDoListCommand {
    public ArrayList<DailyList> dailyLists = new ArrayList<>();
    private Calender calender;


    public void addSchedule() {
        Calender.showCalendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());

        while (true) {
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
        App a1 = new App();
        a1.printMainMenu();
    }

    public void listCheck()
    {
        try {
            // 사용자로부터 날짜 입력 받기
            String dateStr = Prompt.input("확인하고자 하는 날짜를 입력해주세요 (yyyy-MM-dd): ");
            java.sql.Date inputDate = java.sql.Date.valueOf(dateStr);

            // 날짜가 일치하는 DailyList 객체 필터링
            List<DailyList> filteredLists = new ArrayList<>();
            for (Object obj : dailyLists.toArray()) {
                DailyList test = (DailyList) obj;
                if (test.getDate().equals(inputDate)) {
                    filteredLists.add(test);
                }
            }

            // 필터링된 리스트를 시간 기준으로 정렬
            Collections.sort(filteredLists, new Comparator<DailyList>() {
                @Override
                public int compare(DailyList d1, DailyList d2) {
                    return d1.getTime().compareTo(d2.getTime());
                }
            });

            // 정렬된 리스트 출력
            for (DailyList test : filteredLists) {
                System.out.println(test.getTime() + " " + test.getContent());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("올바른 날짜 형식이 아닙니다.");
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }

        // 메뉴 출력
        App a1 = new App();
        a1.printMainMenu();
    }

}

