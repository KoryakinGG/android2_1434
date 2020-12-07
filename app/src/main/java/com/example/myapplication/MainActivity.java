package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager(); // создаем FragmentManager, который будет управлять фрагментами, создаем через getSupportFragmentManager()
    Fragment fragment = new UserListFragment(); // создаем фрагмент из класса



    @Override
    protected void onCreate(Bundle savedInstanceState) { // создает активность
        super.onCreate(savedInstanceState); // создает активность из файла activity_xml
//        Log.d("Активность", "Метод onCreate вызван");
        setContentView(R.layout.activity_main); // берет activity_xml файл из папки resources
//        fragment = new UserListFragment(); // создаем фрагмент из класса
        // чтобы разместить фрагмент, вызываем фрагмент менеджер,бегин транзакшн и через метод add добавляем фрагмент во фреймлойаут
        // метод addToBackStack добавляет фрагмент в стек
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).addToBackStack("main_fragment").commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Fragment fragment = new UserListFragment();
        // R.id.fragmentContainer - это FrameLayout из файла activity_main.xml
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        // если основной фрагмент не ноль и мы его видим, то выходим
        if (currentFragment != null && currentFragment.isVisible()) {
            super.onBackPressed();
        } else {
        // если не на основном фрагменте, то выходим на основной фрагмент
//            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment").commit();
        }
    }

    public static void changeFragment(View view, User user) {
        // Получаем хостинговую активность (в нашем случае MainActivity)
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Создаём менеджер фрагментов
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // создаём фрагмент
        Fragment fragment = new UserFragment();
        // Создаём bundle (это как коллекция)
        Bundle bundle = new Bundle();
        // Записываем user в bundle для передачи в фрагмент
        bundle.putSerializable("user", user);
        // Кладём Bundle в фрагмент
        fragment.setArguments(bundle);
        //Заменяем фрагмент
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }


}