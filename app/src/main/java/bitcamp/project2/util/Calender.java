package bitcamp.project2.util;

import bitcamp.project2.command.ToDoListCommand;
import java.util.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Calender { // 클래스 이름 수정
    static Calendar cal = Calendar.getInstance();
    static int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    static int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    static String[] weeks = {"일", "월", "화", "수", "목", "금", "토"};
    static int month = cal.get(Calendar.MONTH) + 1;
    String[] diary = new String[day_count];
    int[][] data = new int[week_count][7];
    static ToDoListCommand toDoListCommand = ToDoListCommand.getInstance();

    public String[] getDiary() {
        return diary;
    }

    public void setDiary(String[] diary) {
        this.diary = diary;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void settingCalender() {
        for (int i = 0; i < day_count; i++) {
            diary[i] = new String();
        }

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int first_day = cal.get(Calendar.DAY_OF_WEEK);

        int count = 1;

        for (int i = 0; i < data.length; i++) { // 달력의 정보를 보여주기 위하여 이차원 배열에 데이터 저장
            for (int j = 0; j < data[i].length; j++) {
                if (i == 0 && j < first_day - 1) {
                    data[i][j] = 0;
                } else if (count > day_count) {
                    // 이번 달의 마지막 날을 초과한 경우
                    data[i][j] = 0;
                } else {
                    // 그 외의 경우는 날짜값을 할당하고, 날짜값 1 증가
                    data[i][j] = count++;
                }
            }
        }
    }

    // 달력을 보여주는 메소드
    public static void showCalendar(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);  // 월은 0부터 시작하므로 1을 빼줍니다.

        int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[][] data = new int[week_count][7];

        int first_day = cal.get(Calendar.DAY_OF_WEEK);
        int count = 1;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (i == 0 && j < first_day - 1) {
                    data[i][j] = 0;
                } else if (count > day_count) {
                    data[i][j] = 0;
                } else {
                    data[i][j] = count++;
                }
            }
        }

        // 년 월 주 단위로 표시하는 메뉴
        System.out.println("      <" + year + "년 " + month + "월 달력" + ">     ");
        System.out.println("------------------------------");
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                System.out.printf("\033[0;31m%3s\033[0m", weeks[i]); // 일요일 빨간색
            } else if (i == 6) {
                System.out.printf("\033[0;34m%3s\033[0m", weeks[i]); // 토요일 파란색
            } else {
                System.out.printf("%3s", weeks[i]);
            }
        }
        System.out.println();
        System.out.println("------------------------------");

        // 날짜 중복 출력을 방지하기 위한 Set 객체를 초기화합니다.
        Set<String> printedDays = new HashSet<>();

        // 일 출력해주는 for문
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    System.out.print("    ");
                } else {
                    String dayStr = String.format("%2d", data[i][j]);
                    // 날짜 중복 출력을 방지합니다.
                    boolean isPrinted = false;
                    for (int a = 0; a < toDoListCommand.dailyLists.size(); a++) {
                        Date date = toDoListCommand.dailyLists.get(a).getDate();
                        Calendar dateCal = Calendar.getInstance();
                        dateCal.setTime(date);
                        int dateMonth = dateCal.get(Calendar.MONTH) + 1; // 월은 0부터 시작
                        int day = dateCal.get(Calendar.DAY_OF_MONTH);
                        String dayKey = dateMonth + "-" + day;
                        if (dateMonth == month && day == data[i][j] && !printedDays.contains(dayKey)) {
                            printedDays.add(dayKey);
                            System.out.printf("\033[0;33m%4s\033[0m", dayStr);
                            isPrinted = true;
                            break;
                        }
                    }
                    if (!isPrinted) {
                        if (j == 0) {
                            System.out.printf("\033[0;31m%4s\033[0m", dayStr); // 일요일 빨간색
                        } else if (j == 6) {
                            System.out.printf("\033[0;34m%4s\033[0m", dayStr); // 토요일 파란색
                        } else {
                            System.out.printf("%4s", dayStr);
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void promptAndShowCalendar() {
        abc:
        while (true) {
            try {
                String dateStr = Prompt.input("확인하려는 월을 입력하세요 : (yyyy-MM) ");
                if(!Pattern.matches("\\d{4}-\\d{2}", dateStr)){
                    System.out.println("올바른 날짜 형식이 아닙니다. yyyy-MM 형식으로 입력해주세요.");
                    continue abc;
                }
                String[] dateParts = dateStr.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                showCalendar(year, month);
            } catch (Exception e) {
                System.out.println("올바른 날짜 형식이 아닙니다. yyyy-MM 형식으로 입력해주세요.");
            }
            break;
        }
    }
}
