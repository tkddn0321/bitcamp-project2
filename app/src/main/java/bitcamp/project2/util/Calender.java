package bitcamp.project2.util;

import bitcamp.project2.command.ToDoListCommand;
import java.util.Date;
import java.util.Calendar;

public class Calender {
    static Calendar cal = Calendar.getInstance();
    static int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    static int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    static String[] days = new String[day_count];
    static String[] weeks = {"일","월","화","수","목","금","토"};
    static int month = cal.get(Calendar.MONTH)+1;
    String[] diary = new String[day_count];
    int [][] data = new int[week_count][7];
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

   static ToDoListCommand toDoListCommand = ToDoListCommand.getInstance();

    public void settingCalender()
    {
        for (int i=0; i<day_count; i++) {
            diary[i] = new String();
        }

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int first_day = cal.get(Calendar.DAY_OF_WEEK);

        int count = 1;

        for (int i=0; i<data.length; i++) { // 달력의 정보를 보여주기 위하여 이차원배열에 데이터 저장
            for(int j=0; j<data[i].length; j++) {
                if(i==0 && j<first_day-1) {
                    data[i][j] = 0;
                }
                else if (count > day_count) {
                    // 이번 달의 마지막 날을 초과한 경우
                    data[i][j] = 0;
                }
                else {
                    // 그 외의 경우는 날짜값을 할당하고, 날짜값 1 증가
                    data[i][j] = count++;
                }

            }
        }
    }
/*
    public static void check_count(String[] diary) {
        for(int i=0; i<day_count; i++) {
            if (!diary[i].isEmpty())
                days[i] = "*";
            else
                days[i] = null;
        }
    }*/
    // 달력을 보여주는 메소드
    public static void showCalendar(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);  // 월은 0부터 시작하므로 1을 빼줍니다.

        int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] days = new String[day_count];
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
        //년 월 주 단위로 표시하는 메뉴
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
        //일 출력해주는 for문
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    System.out.print("    ");
                } else {
                    String dayStr = String.format("%2d", data[i][j]);
                    if (toDoListCommand.dailyLists.size() == 0)
                    {
                        if (j == 0) {
                            System.out.printf("\033[0;31m%4s\033[0m", dayStr); // 일요일 빨간색
                        } else if (j == 6) {
                            System.out.printf("\033[0;34m%4s\033[0m", dayStr); // 토요일 파란색
                        } else {
                            System.out.printf("%4s", dayStr);
                        }
                    }else
                    {
                    for (int a = 0 ; a < toDoListCommand.dailyLists.size(); a++)
                    {
                        Date date = toDoListCommand.dailyLists.get(a).getDate();
                        cal.setTime(date);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        String listDay = Integer.toString(day);
                        if (listDay.equals(dayStr))
                        {
                            System.out.printf("\033[0;33m%4s\033[0m", dayStr);
                        }else {
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
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void promptAndShowCalendar() {
        String dateStr = Prompt.input("확인하려는 월을 입력하세요 : (yyyy-MM) ");
        try {
            String[] dateParts = dateStr.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            showCalendar(year, month);
        } catch (Exception e) {
            System.out.println("올바른 날짜 형식이 아닙니다. yyyy-MM 형식으로 입력해주세요.");
        }
    }
}
