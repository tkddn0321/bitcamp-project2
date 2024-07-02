package bitcamp.project2;

import java.util.Calendar;
import java.util.Scanner;

public class Calender {
    static Calendar cal = Calendar.getInstance();
    static int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    static int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    static String[] days = new String[day_count];
    static String[] weeks = {"일","월","화","수","목","금","토"};
    static int month = cal.get(Calendar.MONTH)+1;
    String[] diarys = new String[day_count];
    int [][] data = new int[week_count][7];

    public String[] getDiarys() {
        return diarys;
    }

    public void setDiarys(String[] diarys) {
        this.diarys = diarys;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

   public void settingCalender()
    {
        for (int i=0; i<day_count; i++) {
            diarys[i] = new String();
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

    public static void check_count(String[] diarys) {
        for(int i=0; i<day_count; i++) {
            if (diarys[i].isEmpty() == false)
                days[i] = "*";
            else
                days[i] = null;
        }
    }
    // 달력을 보여주는 메소드
    public static void show_calendar(int[][] data, String[] diarys) {

        check_count(diarys);
        System.out.println("                      <"+month+"월 달력"+">                      ");
        System.out.println("----------------------------------------------------");
        for (int i=0; i<7; i++) {
            System.out.printf("%3s\t",weeks[i]);
        }
        System.out.println();
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < data.length; i++) {// 배열 출력을 위한 반복문
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    System.out.print("\t");
                } else {
                    System.out.printf("%3s\t",
                            (days[data[i][j] - 1] == "*") ? days[data[i][j] - 1] + data[i][j] : data[i][j]);
                }
            }
            System.out.println("\n");
        }

    }

}
