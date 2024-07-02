package bitcamp.project2.command;

import bitcamp.project2.util.Calender;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.ToDoList;

import java.time.LocalDate;
import java.util.LinkedList;


public class ToDoListCommand {
    LinkedList<ToDoList> toDoLists = new LinkedList<>();

    public void addSchedule(String type) {
        ToDoList toDoList = new ToDoList();

        Calender.showCalendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue());

        try {
            toDoList.setDate(Prompt.input("추가하고자 하는 날짜를 입력해주세요 (yyyy-MM-dd): "));
        } catch (Exception e) {
            System.out.println("날짜 형식이 올바르지 않습니다.");
            return;
        }

        toDoList.setTime(Prompt.input("시간을 입력하세요 (HH:mm): "));
        toDoList.setContent(Prompt.input("상세 일정을 추가해주세요: "));
        toDoList.setNo(ToDoList.getNextSeqNo());

        this.toDoLists.add(toDoList);

        System.out.println("일정이 등록되었습니다.");
    }
}
