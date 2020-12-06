package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentAddUser extends Fragment {
    private Button addUserBtn1;
    private EditText nameEditText;
    private EditText lastnameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user_fragment, container, false);

        addUserBtn1 = view.findViewById(R.id.addUserBtn1);
        addUserBtn1.setOnClickListener(new View.OnClickListener() {
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
                UserList userList = UserList.get(getActivity());
                // добавляем в юзер лист этого созданного юзера
                userList.addUser(user);        
            }
        });
        return view;
    }
}