package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {
    private User user;
    private TextView userName_userLastname_View;
    private Button updateBtn;
    private Button deleteBtn;
    private TextView updateName;
    private TextView updateLastname;
    private UserList userList;
    long userId=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user"); // Принимаем объект user
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_user,container,false);
        userList = UserList.get(getActivity());
        // соединяем переменные из xml и класса
        updateBtn = view.findViewById(R.id.updateBtn);
        updateName = view.findViewById(R.id.editName);
        updateLastname = view.findViewById(R.id.editLastname);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        // выводим список на экран
        final String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName);

        // по нажатию кнопки делаем добавление в базу
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // получаем из полей в xml данные в строку
                String newUserName = updateName.getText().toString();
                String newUserLastname = updateLastname.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                userList.updateUser(user);
                Toast.makeText(getActivity(),"Данные изменены",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.deleteUser(user.getUuid());
                Toast.makeText(getActivity(),"Данные удалены",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        return view;
    }

}