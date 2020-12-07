package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.UserBaseHelper;
import com.example.myapplication.database.UserDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Синглетный класс (может быть создан только один объект)
public class UserList {
    // декларируем статик переменную юзерлист
    private static UserList userList;
    private Context context;
    private SQLiteDatabase database;
    private List users;

    // проверяем, если юзерлист не создан, то создаем, если создан, то возвращаем
    public static UserList get(Context context){
        if(userList == null){
            userList = new UserList(context);
        }
        return userList;
    }
    // конструктор юзерлист, подлючен к базе
    private UserList(Context context){
        this.context = context.getApplicationContext(); // тот контекст в котором работает приложение (если во фрагменте - значит контекст фрагмента)
        database = new UserBaseHelper(context).getWritableDatabase(); // надо чтобы отправлять запросы в базу данных
    }

    // возвращаем список юзеров
    public List getUsers(){
        users = new ArrayList();
        UserCursorWrapper cursor = queryUsers(null,null);
        try {
            cursor.moveToFirst();
            Log.d("СМОТРИМ СКОЛЬКО БЫЛО В ЛИСТЕ", Integer.toString(users.size()));
            while (!cursor.isAfterLast()){// типо hasNext в итераторе, смотрит если следующая строка
                users.add(cursor.getUser());      // добавляет пользователя в список, чтобы вывести на экран
                cursor.moveToNext();   // перемещаем курсор на сл. строку
                Log.d("СМОТРИМ СКОЛЬКО ДОБАВИЛОСЬ В ЛИСТ", Integer.toString(users.size()));
            }
        }finally {
            cursor.close();
        }
        return users;
    }
    // добавлям юзера
    public void addUser(User user){
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME_DB_TABLE, null, values);
    }

    // внутренний метод для добавления юзера, распределяет по полям в базу полученного юзера
    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.USERLASTNAME, user.getUserLastName());
        return values;
    }

    // внутренний метод для запроса юзера из базы
    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs){
        // смотрим где курсор
        Cursor cursor = database.query(
                UserDbSchema.UserTable.NAME_DB_TABLE,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }

    public void dropDB(){
        database.delete(UserDbSchema.UserTable.NAME_DB_TABLE,null,null);
    }

    public void updateUser (User user) {
        String uuidString = user.getUuid().toString(); // получаем UUID строки
        ContentValues values = getContentValues(user); // контент вальюс имеет связку столбцов и значений (это метод который выше)
        //апдейтим базу: имя базы, поменять параметры в этих столбцах (values), у того чей UUID совпадает с тем, что в последнем параметре
        database.update(UserDbSchema.UserTable.NAME_DB_TABLE, values, UserDbSchema.UserTable.Cols.UUID + "=?", new String[]{uuidString});
    }

    public void deleteUser(UUID uuid){
        database.delete(UserDbSchema.UserTable.NAME_DB_TABLE, "uuid = ?", new String[]{String.valueOf(uuid)});
    }

}