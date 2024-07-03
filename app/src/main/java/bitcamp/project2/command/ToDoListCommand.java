package bitcamp.project2.command;

import bitcamp.project2.App;
import bitcamp.project2.util.Calender;
import bitcamp.project2.util.Prompt;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bitcamp.project2.util.DailyList;


public class ToDoListCommand {
    private static ToDoListCommand instance;
    public static ToDoListCommand getInstance()
    {
        if (instance == null)
        {
            instance = new ToDoListCommand();
        }
        return instance;
    }
    public ArrayList<DailyList> dailyLists = new ArrayList<>();
    String[] listMenu = new String[]{"상세일정확인", "일정체크", "뒤로가기"};

    public void addSchedule() {
        while (true) {
            DailyList dailyList = new DailyList();

            try {
                String dateStr = Prompt.input("추가하실 연과 월을 입력해주세요 (yyyy-MM): ");
                String[] dateParts = dateStr.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                Calender.showCalendar(year, month);
                String dateStr2 = Prompt.input("추가하실 일을 입력해주세요 (dd): ");
                dailyList.setDate(Date.valueOf(dateStr + "-" + dateStr2));
            } catch (Exception e) {
                System.out.println("올바른 날짜 형식이 아닙니다.");
                continue;
            }
            while (true) {
                try {
                    String timeStr = Prompt.input("시간을 입력하세요 (HH:mm): ");
                    dailyList.setTime(java.sql.Time.valueOf(timeStr + ":00"));
                    break;
                } catch (Exception e) {
                    System.out.println("올바른 시간 형식이 아닙니다.");
                }
            }
            dailyList.setContent(Prompt.input("상세 일정을 추가해주세요: "));
            dailyList.setNo(dailyLists.size() + 1);
            dailyList.setCheck(false);
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


    public void listCheck() {
        printAll();
        try {
            if(dailyLists.size() == 0)
            {
                System.out.println("현재 배열에 저장되어있는 값이 없습니다");
                return;
            }
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

            System.out.println("==============================");
            // 정렬된 리스트 출력
            for (DailyList test : filteredLists) {
                if (test.getCheck()) {
                    System.out.println("\033[9m" + test.getTime() + " | " + test.getContent() + "\033[0m");
                } else {
                    System.out.println(test.getTime() + " | " + test.getContent());
                }
            }
            System.out.println("==============================");

        } catch (IllegalArgumentException e) {
            System.out.println("올바른 날짜 형식이 아닙니다.");
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }

    }

    public void dailyListCheck() {
        printAll();
        try {
            if(dailyLists.size() == 0)
            {
                System.out.println("현재 배열에 저장되어있는 값이 없습니다");
                return;
            }
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

            System.out.println("==============================");
            for (DailyList test : filteredLists) {
                System.out.printf(test.getTime() + " " + test.getContent());
                String flag = Prompt.input(" > 현재 일정을 진행 하셨습니까? y / n");
                if (flag.equals("y")) {
                    for (int i = 0; i < dailyLists.size(); i++) {
                        if (dailyLists.get(i).equals(test)) {
                            DailyList dailyList = dailyLists.get(i);
                            dailyList.setCheck(true);
                            dailyLists.set(i, dailyList);
                            break;
                        }
                    }
                }
            }
            System.out.println("==============================");
        } catch (IllegalArgumentException e) {
            System.out.println("올바른 날짜 형식이 아닙니다.");
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void listSchedule() {
        String command;
        printMainMenu();
        while (true) {
            try {
                command = Prompt.input("일정 확인 > ");
                if (command.equals("메뉴") || command.equals("menu")) {
                    printMainMenu();
                } else {
                    int menuNumber = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(listMenu, menuNumber);
                    if (menuTitle == null) {
                        System.out.println("유효한 숫자를 입력 해주세요.");
                        continue;
                    } else if (menuTitle.equals("뒤로가기")) {
                        break;
                    }
                    switch (menuTitle) {
                        case "상세일정확인":
                            listCheck();
                            break;
                        case "일정체크":
                            dailyListCheck();
                            break;

                        default:
                            System.out.println("유효하지않은 메인메뉴 번호입니다. 다시 입력해주세요");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("문자 입력은 menu 만 가능합니다. 다시 입력해주세요");
            }
        }
    }

    public void updateSchedule() {
        if(dailyLists.size() == 0)
        {
            System.out.println("현재 배열에 저장되어있는 값이 없습니다");
            return;
        }
        printAll();
        int dailyListNo = Prompt.inputInt("번호를 입력하세요 :");
        DailyList dailyListToUpdate = null;
        for (Object obj : dailyLists.toArray()) {
            DailyList dailyList = (DailyList) obj;
            if (dailyList.getNo() == dailyListNo) {
                dailyListToUpdate = dailyList;
                break;
            }
        }
        if (dailyListToUpdate != null) {
            while (true) {
                try {
                    String dateStr = Prompt.input("변경을 원하고자 하는 월을 입력해주세요 (yyyy-MM): ");
                    String[] dateParts = dateStr.split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    Calender.showCalendar(year, month);
                    String dateStr2 = Prompt.input("변경을 원하고자 하는 일을 입력해주세요 (dd): ");
                    dailyListToUpdate.setDate(Date.valueOf(dateStr + "-" + dateStr2));
                } catch (Exception e) {
                    System.out.println("올바른 날짜 형식이 아닙니다.");
                    continue;
                }

                try {
                    String timeStr = Prompt.input("시간을 입력하세요 (HH:mm): ");
                    dailyListToUpdate.setTime(Time.valueOf(timeStr + ":00"));
                } catch (Exception e) {
                    System.out.println("올바른 시간 형식이 아닙니다.");
                    continue;
                }
                dailyListToUpdate.setContent(Prompt.input("상세 일정을 추가해주세요 : ", dailyListToUpdate.getContent()));
                System.out.println("변경했습니다.");
                break;
            }
        } else {
            System.out.println("해당 번호가 없습니다.");
        }
    }

    public void deleteSchedule() {
        if(dailyLists.size() == 0)
        {
            System.out.println("현재 배열에 저장되어있는 값이 없습니다");
            return;
        }
        printAll();
        // 목록 띄우고 번호 묻기1
        int dailyListNo = Prompt.inputInt("삭제하실 번호를 입력하세요 : ");
        DailyList dailyListToRemove = null;
        for (Object obj : dailyLists.toArray()) {
            DailyList dailyList = (DailyList) obj;
            if (dailyList.getNo() == dailyListNo) {
                dailyListToRemove = dailyList;
                break;
            }
        }
        if (dailyListToRemove != null) {
            dailyLists.remove(dailyListToRemove);
            System.out.printf("%s %s %s 일정을 삭제했습니다.\n", dailyListToRemove.getDate(), dailyListToRemove.getTime(), dailyListToRemove.getContent());
        } else {
            System.out.println("해당 번호가 없습니다.");
        }
    }

    public void printAll()
    {

        Collections.sort(dailyLists, new Comparator<DailyList>() {
            @Override
            public int compare(DailyList d1, DailyList d2) {
                return d1.getTime().compareTo(d2.getTime());
            }
        });
        System.out.println("==============================");

        for(DailyList dailyList : dailyLists)
        {
            System.out.println(dailyList.getNo() + " | " + dailyList.getDate() + " | " +dailyList.getTime() + " | " + dailyList.getContent());
        }
        System.out.println("==============================");
    }

    // 메뉴타이틀 추출 메서드
    String getMenuTitle(String[] menu, int menuNumber) {
        return validation(menu, menuNumber) ? menu[menuNumber - 1] : null;
    }

    // 입력값 유효판별 메서드
    Boolean validation(String[] menu, int menuNumber) {
        return menuNumber >= 1 && menuNumber <= menu.length;
    }

    // 메인 메뉴목록 출력 메서드
    public void printMainMenu() {
        System.out.println("========== 메뉴 ===========");
        for (int i = 0; i < listMenu.length; i++) {
            System.out.printf("  %d  |  %s\n", i + 1, listMenu[i]);
        }
        System.out.println("===========================");
    }

}

