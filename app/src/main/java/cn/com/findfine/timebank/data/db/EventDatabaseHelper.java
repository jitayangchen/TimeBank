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

    public static final String DB_NAME = "Event_List.db";
    public static final int DB_VERSION = 1;

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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        for (int version = oldV + 1; version <= newV; version++) {
            upgradeTo(sqLiteDatabase, version);
        }
    }

    /**
     * Upgrade database from (version - 1) to version.
     */
    private void upgradeTo(SQLiteDatabase sqLiteDatabase, int version) {
        switch (version) {
            case 1:
                createEventTable();
                break;
            default:
                throw new IllegalStateException("Don't know how to upgrade to " + version);
        }
    }

    private void createEventTable() {

    }
}
