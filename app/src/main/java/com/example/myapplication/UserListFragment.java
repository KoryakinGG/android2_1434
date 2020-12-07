package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView; // элемент для отображения списка (делает прокручиваемый список)
    private UserList userList;
    private List<User> users;
    private UserAdapter userAdapter;
    private Button openAddUserActivity;
    private Button dropBD;

    @Override // Метод создаёт компонент View фрагмента из XML разметки
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
       // создаем представление (view), "раздуваем" компонент из шаблона fragment_user_list, который RecyclerView (т.е. список)
        View view = inflater.inflate(R.layout.fragment_user_list,viewGroup,false);
        // привязываем элемент  userRecyclerView к конкретному xml файлу userRecyclerView
        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        // упорядочиваем элементы RecyclerView через LayoutManager, говорим, что элементы будет идти списком (который мы будем прокручивать через RecyclerView)
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // кнопка для перехода на новую активность, привязываем ее к xml  --> переопределяем ее на переход на новый фрагмент
        openAddUserActivity = view.findViewById(R.id.openAddUserActivity);

        dropBD = view.findViewById(R.id.dropBD);

        // создаем или проверяем есть ли юзерлист на этой активности
        userList = UserList.get(getActivity());
        // получаем лист юзеров
        users = userList.getUsers();
        // создаем адаптер
        userAdapter = new UserAdapter(users);
        userRecyclerView.setAdapter(userAdapter);

        // при нажатии этой кнопки, тут нужно заменить активность на фрагмент
        openAddUserActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext(), AddUser.class));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                // Что ещё за магия???
                //Fragment fragmentAddUser= (FragmentAddUser)getFragmentManager().findFragmentById(R.id.fragmentAddUserContainer);
                Fragment fragment = new FragmentAddUser();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,  fragment).commit();
                //MainActivity.changeFragment(view,null,);
                // Ну как вариант. Можно и подругому. Но так тоже норм. Вы не правильно использовали метод replace.
                // replace(ГДЕ, на что) - первый аргумент говорит где менять, второн на что менять
            }
        });

        dropBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.dropDB();
            }
        });
        return view;
    }

    // Класс UserHolder формирует элементы списка, будем слушать весь элемент, через клик листнер
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView userItemTextView;
        private User itemUser;
        // создаем конструтор UserHolder
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user,viewGroup,false));
            // itemView - это элемент списка
            userItemTextView = itemView.findViewById(R.id.userItem);
            // устанавливаем клик листнер
            itemView.setOnClickListener(this);
        }
        public void bind(User user){
            itemUser = user;
            String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n---------";
            userItemTextView.setText(userName); // Устанавливаем текст элемента списка
        }
        // обработка клик листнера, вызываем метод из майнАктивити, кладем в него представление и юзера, по которому кликаем
        @Override
        public void onClick(View view) {
            MainActivity.changeFragment(view, itemUser);
        }
    }

    // Класс UserAdapter отдаёт элементы в RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> users;
        // конструктор ЮзерАдаптер
        public UserAdapter(List<User> users){
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            userHolder.bind(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
