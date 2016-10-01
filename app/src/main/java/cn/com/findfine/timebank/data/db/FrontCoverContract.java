package cn.com.findfine.timebank.data.db;

/**
 * Created by yangchen on 16/9/15.
 */
public final class FrontCoverContract {

    public static final String TABLE_NAME = "Front_Cover";            // 表名
    public static final String _ID = "_id";                           // 封面ID
    public static final String EVENT_ID = "event_id 事件ID";          // 事件ID
    public static final String SETUP_TIME = "setup_time";           // 封面设置时间

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    EVENT_ID + " INTEGER," +
                    SETUP_TIME + " INTEGER)";
}
