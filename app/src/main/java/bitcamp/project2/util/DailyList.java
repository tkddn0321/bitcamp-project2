package bitcamp.project2.util;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DailyList {
    int no;
    Date date;
    Time time;
    String content;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DailyList dailyLIst = (DailyList) object;
        return no == dailyLIst.no && Objects.equals(date, dailyLIst.date) && Objects.equals(time, dailyLIst.time) && Objects.equals(content, dailyLIst.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, date, time, content);
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
}
