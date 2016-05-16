package com.stiles.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stiles on 16/5/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASENAME = "phonebook.db";
    public final static String TABLENAME = "contacts";
    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String PHONENUM = "phone_num";
    public final static String EMAIL = "email";

    public final static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + TABLENAME + " (" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                PHONENUM + " text, " +
                EMAIL + " text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
