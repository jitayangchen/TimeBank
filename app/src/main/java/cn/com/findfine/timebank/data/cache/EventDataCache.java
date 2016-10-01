package cn.com.findfine.timebank.data.cache;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.data.bean.EventInfo;

/**
 * Created by yangchen on 16/9/11.
 */
public class EventDataCache {

    private List<EventInfo> eventInfos = new ArrayList<>();

    public static EventDataCache getInstance() {
        return SingletonProvider.instance;
    }

    private EventDataCache() {
    }

    public List<EventInfo> getEventInfos() {
        return eventInfos;
    }

    private static class SingletonProvider {
        private static EventDataCache instance = new EventDataCache();
    }

    public EventInfo get(int position) {
        if (eventInfos != null && eventInfos.size() > 0) {
            return eventInfos.get(position);
        }
        return null;
    }

    /**
     * 通过事件ID获取Position
     * @param eventId
     * @return
     */
    public int getPositionByEventId(String eventId) {
        if (TextUtils.isEmpty(eventId)) {
            return 0;
        }
        for (int i = 0; i < eventInfos.size(); i++) {
            EventInfo eventInfo = eventInfos.get(i);
            if (eventId.equals(eventInfo.getEventId())) {
                return i;
            }
        }
        return 0;
    }

    public void addAll(List<EventInfo> eventInfos) {
        if (this.eventInfos.size() > 0) {
            this.eventInfos.clear();
        }
        this.eventInfos.addAll(eventInfos);
    }

    public void insert(EventInfo eventInfo) {
        int position = eventInfos.size();
        for (int i = 0; i < eventInfos.size(); i++) {
            EventInfo tem = eventInfos.get(i);
            if (eventInfo.getTargetTime() < tem.getTargetTime()) {
                position = i;
                break;
            }
        }
        eventInfos.add(position, eventInfo);
    }

    public void clear() {
        if (eventInfos != null) {
            eventInfos.clear();
            eventInfos = null;
        }
    }

}
