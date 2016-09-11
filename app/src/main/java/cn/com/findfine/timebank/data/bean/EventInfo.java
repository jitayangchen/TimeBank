package cn.com.findfine.timebank.data.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangchen on 16/9/3.
 */
public class EventInfo {

    private int id;
    private int type;
    private String title;
    private long createTime;
    private long targetTime;
    private long timeLeft;
    private String content;
    private boolean isCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(long targetTime) {
        this.targetTime = targetTime;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        String formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(targetTime * 1000));
        return "EventInfo{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", targetTime=" + targetTime +
                ", targetTime=" + formatTime +
                ", timeLeft=" + timeLeft +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
