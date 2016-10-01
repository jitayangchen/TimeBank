package cn.com.findfine.timebank.data.bean;

/**
 * Created by yangchen on 16/9/15.
 */
public class FrontCover {

    private int id;
    private int eventId;
    private long setupTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }
}
