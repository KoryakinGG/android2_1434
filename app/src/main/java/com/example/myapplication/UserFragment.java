package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
// по сути класс для размещения Юзера на экране
public class UserFragment extends Fragment {
    private User user;
    private TextView userName_userLastname_View;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        user = new User();
        user.setUserName("Ivan");
        user.setUserLastName("Ivanov");
    }
    //Этот метод должен возвращать представление, которое будет использоваться этим фрагментом.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        /* LayoutInflater - указываем, что с каким xml мы хотим работать.
        ViewGroup – родительский ViewGroup-элемент для создаваемого View. LayoutParams от этого ViewGroup присваиваются создаваемому View.
          attachToRoot (Bundle) – присоединять ли создаваемый View к root. Если true, то root становится родителем создаваемого View.
          Т.е. это равносильно команде root.addView(View).  Если false – то создаваемый View просто получает LayoutParams от root,
          но его дочерним элементом не становится.*/
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        // связывает fragment_user.xml файл с нашей переменной TextVeiw, через view
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        // делаем строку, кладем в нее инфо
        String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        // размещаем ее на фрагменте
        userName_userLastname_View.setText(userName);
        // возвращаем результат - новое представление
        return view;
    }
}
