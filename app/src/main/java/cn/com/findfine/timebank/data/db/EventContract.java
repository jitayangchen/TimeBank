package cn.com.findfine.timebank.data.db;

/**
 * Created by yangchen on 16/9/15.
 */
public final class EventContract {

    public static final String TABLE_NAME = "Event_list";          // 表名
    public static final String _ID = "_id";                        // ID
    public static final String EVENT_ID = "event_id";              // 事件ID
    public static final String TITLE = "title";                    // 标题
    public static final String CREATE_TIME = "create_time";        // 创建时间
    public static final String TARGET_TIME = "target_time";        // 目标时间
    public static final String EVENT_CONTENT = "event_content";    // 事件内容
    public static final String EVENT_TYPE = "event_type";          // 事件类型
    public static final String IS_COMPLETED = "is_completed";      // 是否完成

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    EVENT_ID + " TEXT," +
                    TITLE + " TEXT," +
                    CREATE_TIME + " INTEGER," +
                    TARGET_TIME + " INTEGER," +
                    EVENT_CONTENT + " TEXT," +
                    EVENT_TYPE + " INTEGER," +
                    IS_COMPLETED + " INTEGER)";
}
