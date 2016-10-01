package cn.com.findfine.timebank.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.TBApplication;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.bean.FrontCover;

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

    /**
     * 插入事件
     * @param eventInfo
     * @return
     */
    public synchronized boolean insertEvent(EventInfo eventInfo) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventContract.EVENT_ID, eventInfo.getEventId());
        values.put(EventContract.TITLE, eventInfo.getTitle());
        values.put(EventContract.CREATE_TIME, eventInfo.getCreateTime() / 1000);
        values.put(EventContract.TARGET_TIME, eventInfo.getTargetTime() / 1000);
        values.put(EventContract.EVENT_CONTENT, eventInfo.getContent());
        values.put(EventContract.EVENT_TYPE, eventInfo.getType());
        values.put(EventContract.IS_COMPLETED, eventInfo.isCompleted() ? 1 : 0);
        long insert = db.insert(EventContract.TABLE_NAME, null, values);
        return insert != -1;
    }

    public synchronized boolean updateEvent(EventInfo eventInfo) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventContract.TITLE, eventInfo.getTitle());
        values.put(EventContract.CREATE_TIME, eventInfo.getCreateTime() / 1000);
        values.put(EventContract.TARGET_TIME, eventInfo.getTargetTime() / 1000);
        values.put(EventContract.EVENT_CONTENT, eventInfo.getContent());
        values.put(EventContract.EVENT_TYPE, eventInfo.getType());
        values.put(EventContract.IS_COMPLETED, eventInfo.isCompleted() ? 1 : 0);
        int update = db.update(EventContract.TABLE_NAME, values, EventContract.EVENT_ID + "=?", new String[]{eventInfo.getEventId()});
        return update >= 0;
    }

    /**
     * 查询大于指定时间的事件
     * @param time
     * @return
     */
    public synchronized List<EventInfo> queryAllEventByCurrentTime(long time) {
        List<EventInfo> eventInfos = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EventContract.TABLE_NAME, null, EventContract.TARGET_TIME + ">=?", new String[]{String.valueOf(time / 1000)}, null, null, EventContract.TARGET_TIME + " ASC");
        while (cursor.moveToNext()) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.setId(cursor.getInt(cursor.getColumnIndex(EventContract._ID)));
            eventInfo.setEventId(cursor.getString(cursor.getColumnIndex(EventContract.EVENT_ID)));
            eventInfo.setTitle(cursor.getString(cursor.getColumnIndex(EventContract.TITLE)));
            eventInfo.setContent(cursor.getString(cursor.getColumnIndex(EventContract.EVENT_CONTENT)));
            eventInfo.setCreateTime(cursor.getLong(cursor.getColumnIndex(EventContract.CREATE_TIME)) * 1000);
            eventInfo.setTargetTime(cursor.getLong(cursor.getColumnIndex(EventContract.TARGET_TIME)) * 1000);
            eventInfo.setType(cursor.getInt(cursor.getColumnIndex(EventContract.EVENT_TYPE)));
            int tem = cursor.getInt(cursor.getColumnIndex(EventContract.IS_COMPLETED));
            eventInfo.setCompleted(tem == 1);
            eventInfos.add(eventInfo);
        }
        cursor.close();
        return eventInfos;
    }

    /**
     * 查询所有事件
     * @return
     */
    public synchronized List<EventInfo> queryAllEvent() {
        List<EventInfo> eventInfos = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EventContract.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.setId(cursor.getInt(cursor.getColumnIndex(EventContract._ID)));
            eventInfo.setEventId(cursor.getString(cursor.getColumnIndex(EventContract.EVENT_ID)));
            eventInfo.setTitle(cursor.getString(cursor.getColumnIndex(EventContract.TITLE)));
            eventInfo.setContent(cursor.getString(cursor.getColumnIndex(EventContract.EVENT_CONTENT)));
            eventInfo.setCreateTime(cursor.getLong(cursor.getColumnIndex(EventContract.CREATE_TIME)) * 1000);
            eventInfo.setTargetTime(cursor.getLong(cursor.getColumnIndex(EventContract.TARGET_TIME)) * 1000);
            eventInfo.setType(cursor.getInt(cursor.getColumnIndex(EventContract.EVENT_TYPE)));
            int tem = cursor.getInt(cursor.getColumnIndex(EventContract.IS_COMPLETED));
            eventInfo.setCompleted(tem == 1);
            eventInfos.add(eventInfo);
        }
        cursor.close();
        return eventInfos;
    }

    /**
     * 通过ID删除事件
     * @param id
     * @return
     */
    public synchronized int deleteEventById(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int delete = db.delete(EventContract.TABLE_NAME, EventContract._ID + "=?", new String[]{String.valueOf(id)});
        return delete;
    }

    /**
     * 增加封面
     * @param frontCover
     */
    public synchronized void insertFrontCover(FrontCover frontCover) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FrontCoverContract.EVENT_ID, frontCover.getEventId());
        values.put(FrontCoverContract.SETUP_TIME, frontCover.getSetupTime());
        db.insert(FrontCoverContract.TABLE_NAME, null, values);
    }

    /**
     * 通过ID删除封面
     * @param id
     */
    public synchronized void deleteFrontCoverById(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int delete = db.delete(FrontCoverContract.TABLE_NAME, FrontCoverContract._ID + "=?", new String[]{String.valueOf(id)});
    }

    /**
     * 查询所有的封面
     * @return
     */
    public synchronized List<FrontCover> queryAllFrontCover() {
        List<FrontCover> frontCovers = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(EventContract.TABLE_NAME, null, null, null, null, null, null);
        return frontCovers;
    }
}
