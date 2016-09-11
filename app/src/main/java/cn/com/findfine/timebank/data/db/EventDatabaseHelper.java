package cn.com.findfine.timebank.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.com.findfine.timebank.Config;
import cn.com.findfine.timebank.log.TLog;

/**
 * Created by yangchen on 16/9/10.
 */
public class EventDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Event.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "Event_list";          // 表名
    public static final String _ID = "_id";                        // 事件ID
    public static final String TITLE = "title";                    // 标题
    public static final String CREATE_TIME = "create_time";        // 创建时间
    public static final String TARGET_TIME = "target_time";        // 目标时间
    public static final String EVENT_CONTENT = "event_content";    // 事件内容
    public static final String EVENT_TYPE = "event_type";          // 事件类型
    public static final String IS_COMPLETED = "is_completed";      // 是否完成

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    TITLE + " TEXT," +
                    CREATE_TIME + " INTEGER," +
                    TARGET_TIME + " INTEGER," +
                    EVENT_CONTENT + " TEXT," +
                    EVENT_TYPE + " INTEGER," +
                    IS_COMPLETED + " INTEGER)";

    public EventDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        if (Config.IS_DEBUG) {
            TLog.i("populating new database");
        }

        onUpgrade(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        for (int version = oldVersion + 1; version <= newVersion; version++) {
            upgradeTo(sqLiteDatabase, version);
        }
    }

    /**
     * Upgrade database from (version - 1) to version.
     */
    private void upgradeTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                createEventTable(db);
                break;
            default:
                throw new IllegalStateException("Don't know how to upgrade to " + version);
        }
    }

    private void createEventTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
}
