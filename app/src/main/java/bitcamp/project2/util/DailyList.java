package bitcamp.project2.util;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DailyList {
    int no;
    Date date;
    Time time;
    String content;
    Boolean check;

    public DailyList(){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyList dailyList = (DailyList) o;
        return Objects.equals(date, dailyList.date) &&
                Objects.equals(time, dailyList.time) &&
                Objects.equals(content, dailyList.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, content);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCheck() { return check; }

    public void setCheck(Boolean check) { this.check = check; }
}
