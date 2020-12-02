package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

// Синглетный класс (может быть создан только один объект), могу дополнить, что это один из паттернов проектирования Singleton
public class UserList {
    private static UserList userList;
    private List users;
 // создаем геттер для получения листа, который проверяет, что такого обхекта нет нигде.
    public static UserList get(){
        if(userList == null){
            userList = new UserList();
        }
        return userList;
    }
    // ну, тут в конструкторе просто прописываем минимальную логику для заполнения листа
    private UserList(){
        users = new ArrayList();
        for(int i=0; i<100; i++){
            User user = new User();
            user.setUserName("ИМЯ_"+i);
            user.setUserLastName("Фамилия_"+i);
            users.add(user);
        }
    }
    public List getUsers(){
        return users;
    }
}