package bitcamp.project2.vo;

public class ToDoList {
    private int no;
    private String date;
    private String time;
    private String content;

    public static int getNextSeqNo() {
        return nextSeqNo;
    }

    public static void setNextSeqNo(int nextSeqNo) {
        ToDoList.nextSeqNo = nextSeqNo;
    }

    private static int nextSeqNo = 1;

    // getters and setters
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ToDoList that = (ToDoList) obj;
        return no == that.no;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(no);
    }


}
