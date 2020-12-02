package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserList userList = UserList.get();
        //служит для управления фрагментами
        FragmentManager fragmentManager = getSupportFragmentManager();
        // создаем новый фрагмент
        Fragment fragment = new UserListFragment();
        /* кладем в фрагментМенеджер  фреймЛойаут xml Активити_мейн (через add), наш фрагмент
        * метод БегинТранзакшн поменщает данные элементы на экран
        * метод Коммит нужен чтобы закрепить элементы на экране*/
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }
}