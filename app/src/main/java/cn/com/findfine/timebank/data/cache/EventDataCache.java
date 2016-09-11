package cn.com.findfine.timebank.data.cache;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.data.bean.EventInfo;

/**
 * Created by yangchen on 16/9/11.
 */
public class EventDataCache {

    private List<EventInfo> eventInfos = null;

    public static EventDataCache getInstance() {
        return SingletonProvider.instance;
    }

    private EventDataCache() {
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

    public void addAll(List<EventInfo> eventInfos) {
        if (this.eventInfos == null) {
            this.eventInfos = new ArrayList<>();
        } else if (this.eventInfos.size() > 0) {
            this.eventInfos.clear();
        }
        this.eventInfos.addAll(eventInfos);
    }

    public void insert(EventInfo eventInfo) {
        if (eventInfos == null) {
            eventInfos = new ArrayList<>();
        }
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
