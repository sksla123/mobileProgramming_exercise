package com.example.j202012195_1217;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Sqlite groupTBL 생성 함수
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strSQLCreateGroupTable = "CREATE TABLE groupTBL (gName VARCHAR(20) PRIMARY KEY, gNumber INTEGER);";
        Log.d("SQL", strSQLCreateGroupTable);
        sqLiteDatabase.execSQL(strSQLCreateGroupTable);
    }

    // Sqlite groupTBL 초기화 함수
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String strSqlDropGroupTable = "DROP TABLE IF EXISTS groupTBL;";
        sqLiteDatabase.execSQL(strSqlDropGroupTable);
        Log.d("SQL", strSqlDropGroupTable);
        onCreate(sqLiteDatabase);
    }
}
