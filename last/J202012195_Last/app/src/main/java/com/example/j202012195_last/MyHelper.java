package com.example.j202012195_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE phoneTBL (name VARCHAR(20) PRIMARY KEY, phoneNum VARCHAR(20));");
        sqLiteDatabase.execSQL("INSERT INTO phoneTBL VALUES('홍길동', '010-1234-5678');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS phoneTBL;");
        onCreate(sqLiteDatabase);
    }
}
