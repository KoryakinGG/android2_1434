/*
package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddUser extends AppCompatActivity {
    private Button addUserBtn;
    private EditText nameEditText;
    private EditText lastnameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        // привязываем кнопки
        nameEditText = findViewById(R.id.nameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        addUserBtn = findViewById(R.id.addUserBtn);
        // действие по нажатию на кнопку
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // получаем стринги с именем и фамилией
                String userName = nameEditText.getText().toString();
                String userLastname = lastnameEditText.getText().toString();
                // создаем юзера и кладем в него имя и фио
                User user = new User();
                user.setUserName(userName);
                user.setUserLastName(userLastname);
                // получаем объект юзерлист и призываем геттер на него
                UserList userList = UserList.get(AddUser.this);
                // добавляем в юзер лист этого созданного юзера
                userList.addUser(user);
                // вызываем метод перехода обратно к MainActivity
                onBackPressed();
            }
        });
    }
}*/
