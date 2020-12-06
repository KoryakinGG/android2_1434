package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserFragment extends Fragment {
    private User user;
    private TextView userName_userLastname_View;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user"); // Принимаем объект user
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        userName_userLastname_View = view.findViewById(R.id.userName_userLastname_View);
        String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName();
        userName_userLastname_View.setText(userName);

        return view;
    }
}