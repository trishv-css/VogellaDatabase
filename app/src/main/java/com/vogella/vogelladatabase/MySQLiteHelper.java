package com.vogella.vogelladatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Trish Valeri on 4/1/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";     // name of the table
    public static final String COLUMN_ID = "_id";               // name of id field
    public static final String COLUMN_COMMENT = "comment";      // name of the comment field
    public static final String COLUMN_RATING = "rating";        // name of the rating field

    private static final String DATABASE_NAME = "commments.db"; // name of the database
    private static final int DATABASE_VERSION = 2;              // version of the database

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COMMENT + " text not null, "
            + COLUMN_RATING + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
