package com.example.mariyan.startform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Mariyan on 31.1.2015 г..
 */
public class MyDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int VERSION = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + RankingConstants.TABLE_NAME + " ("
            + RankingConstants._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RankingConstants.COLUMN_NICKNAME + " TEXT NOT NULL, "
            + RankingConstants.COLUMN_TIME + " REAL NOT NULL);";
//            + RankingConstants.COLUMN_AVATAR +  " INTEGER NOT NULL) "


    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXIST " + RankingConstants.TABLE_NAME;

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);

        onCreate(sqLiteDatabase);
    }

    public class RankingConstants implements BaseColumns {
        public static final String TABLE_NAME = "ranking";
        public static final String COLUMN_NICKNAME = "nickname";
        public static final String COLUMN_TIME = "time";
//        public static final String COLUMN_AVATAR = "avatar";


        private RankingConstants() {

        }

    }

}
