package com.example.a1220_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {

    public myDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strSQLCreatePhoneBookTable = "CREATE TABLE phoneBookTBL (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), phoneNum VARCHAR(20));";
        sqLiteDatabase.execSQL(strSQLCreatePhoneBookTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String strSqlDropPhoneBookTable = "DROP TABLE IF EXISTS phoneBookTBL;";
        sqLiteDatabase.execSQL(strSqlDropPhoneBookTable);
        onCreate(sqLiteDatabase);
    }
}
