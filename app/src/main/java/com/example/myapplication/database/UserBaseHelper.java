package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.myapplication.database.UserDbSchema.*; // импортировали наш класс с полями таблицы и ее названием

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1; // версия базы данных
    private static final String DATABASE_NAME = "userBase.db"; // имя базы данных

    // конструктор базы данных
    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // создаем базу данных
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ UserTable.NAME_DB_TABLE +"("+
                "_id integer primary key autoincrement," +
                UserTable.Cols.UUID+"," +
                UserTable.Cols.USERNAME+"," +
                UserTable.Cols.USERLASTNAME+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
