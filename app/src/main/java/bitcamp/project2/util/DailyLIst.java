package bitcamp.project2.util;

import java.time.LocalTime;

public class DailyLIst {
    LocalTime time;
    String content;

    public DailyLIst(LocalTime time, String content)
    {
        this.content = content;
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
