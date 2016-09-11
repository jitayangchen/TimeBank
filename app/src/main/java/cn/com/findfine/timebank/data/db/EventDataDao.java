package cn.com.findfine.timebank.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.TBApplication;
import cn.com.findfine.timebank.data.bean.EventInfo;

/**
 * Created by yangchen on 16/9/10.
 */
public class EventDataDao {

    private EventDatabaseHelper databaseHelper;

    public static EventDataDao getInstance() {
        return SingletonProvider.instance;
    }

    private EventDataDao() {
        databaseHelper = new EventDatabaseHelper(TBApplication.getContext());
    }

    private static class SingletonProvider {
        private static EventDataDao instance = new EventDataDao();
    }

    public synchronized long insert(EventInfo eventInfo) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventDatabaseHelper.TITLE, eventInfo.getTitle());
        values.put(EventDatabaseHelper.CREATE_TIME, eventInfo.getCreateTime());
        values.put(EventDatabaseHelper.TARGET_TIME, eventInfo.getTargetTime());
        values.put(EventDatabaseHelper.EVENT_CONTENT, eventInfo.getContent());
        values.put(EventDatabaseHelper.EVENT_TYPE, eventInfo.getType());
        values.put(EventDatabaseHelper.IS_COMPLETED, eventInfo.isCompleted() ? 1 : 0);
        long insert = db.insert(EventDatabaseHelper.TABLE_NAME, null, values);
        return insert;
    }

    public synchronized List<EventInfo> queryAllByCurrentTime(long time) {
        List<EventInfo> eventInfos = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EventDatabaseHelper.TABLE_NAME, null, EventDatabaseHelper.TARGET_TIME + "<=?", new String[]{String.valueOf(time)}, null, null, null);
        while (cursor.moveToNext()) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.setId(cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper._ID)));
            eventInfo.setTitle(cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.TITLE)));
            eventInfo.setContent(cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.EVENT_CONTENT)));
            eventInfo.setCreateTime(cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.CREATE_TIME)));
            eventInfo.setTargetTime(cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.TARGET_TIME)));
            eventInfo.setType(cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper.EVENT_TYPE)));
            int tem = cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper.IS_COMPLETED));
            eventInfo.setCompleted(tem == 1);
            eventInfos.add(eventInfo);
        }
        cursor.close();
        return eventInfos;
    }

    public synchronized List<EventInfo> queryAll() {
        List<EventInfo> eventInfos = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EventDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.setId(cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper._ID)));
            eventInfo.setTitle(cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.TITLE)));
            eventInfo.setContent(cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.EVENT_CONTENT)));
            eventInfo.setCreateTime(cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.CREATE_TIME)));
            eventInfo.setTargetTime(cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.TARGET_TIME)));
            eventInfo.setType(cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper.EVENT_TYPE)));
            int tem = cursor.getInt(cursor.getColumnIndex(EventDatabaseHelper.IS_COMPLETED));
            eventInfo.setCompleted(tem == 1);
            eventInfos.add(eventInfo);
        }
        cursor.close();
        return eventInfos;
    }

    public synchronized int deleteById(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int delete = db.delete(EventDatabaseHelper.TABLE_NAME, EventDatabaseHelper._ID + "=?", new String[]{String.valueOf(id)});
        return delete;
    }
}
