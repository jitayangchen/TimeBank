package cn.com.findfine.timebank.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangchen on 16/9/3.
 */
public class EventInfo implements Parcelable {

    private int id;
    private String eventId;
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(eventId);
        parcel.writeInt(type);
        parcel.writeString(title);
        parcel.writeLong(createTime);
        parcel.writeLong(targetTime);
        parcel.writeLong(timeLeft);
        parcel.writeString(content);
        parcel.writeByte((byte) (isCompleted ? 1 : 0));
    }

    public static final Creator<EventInfo> CREATOR = new Creator<EventInfo>() {
        @Override
        public EventInfo createFromParcel(Parcel in) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.id = in.readInt();
            eventInfo.eventId = in.readString();
            eventInfo.type = in.readInt();
            eventInfo.title = in.readString();
            eventInfo.createTime = in.readLong();
            eventInfo.targetTime = in.readLong();
            eventInfo.timeLeft = in.readLong();
            eventInfo.content = in.readString();
            eventInfo.isCompleted = in.readByte() != 0;
            return eventInfo;
        }

        @Override
        public EventInfo[] newArray(int size) {
            return new EventInfo[size];
        }
    };
}
