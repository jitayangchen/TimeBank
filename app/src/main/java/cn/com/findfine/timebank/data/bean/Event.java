package cn.com.findfine.timebank.data.bean;

/**
 * Created by yangchen on 16/9/3.
 */
public class Event {

    private int icon;
    private String title;
    private String createTime;
    private String targetTime;
    private String timeLeft;
    private String content;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Event{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", targetTime='" + targetTime + '\'' +
                ", timeLeft='" + timeLeft + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
