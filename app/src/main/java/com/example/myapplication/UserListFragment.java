package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    // Метод создаёт компонент View фрагмента из XML разментки
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_list,viewGroup,false);
        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        /* setLayoutManager - РесайклерВью без него работать не будет, нужно чтобы он знал как отображать элементы,
        * устанавливаем Менеджер и в него кладем нижние элементы
        * new LinearLayoutManager - позволяет создавать списком элементы
        * getActivity() - позволяет получить текущую активность*/
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        UserList userList = UserList.get(); // создаем переменную UserList чтобы получить список пользователей
        List<User> users = userList.getUsers(); // получаем список пользователей и записываем в переменную users
        userAdapter = new UserAdapter(users); // создаем переменную ЮзерАдаптер и кладем туда список юзеров
        userRecyclerView.setAdapter(userAdapter); // устанавливаем Адаптер для userRecyclerView

        return view;
    }

    private class UserHolder extends RecyclerView.ViewHolder{
        private TextView userItem;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user,viewGroup,false));
            userItem = itemView.findViewById(R.id.userItem);
        }
        // связываем текст который приходит в списке Юзерс с элементом ЮзерАйтем
        // короче, просто красиво выводим юхеров на экран
        public void bind(User user){
            String userName = "Имя: "+user.getUserName()+"\n"+"Фамилия: "+user.getUserLastName()+"\n---------";
            userItem.setText(userName);
        }
    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> users;
        public UserAdapter(List<User> users){
            this.users = users;
        }

        // создает нам элемент списка, когда мы листаем список вверх
        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }
        // привязываем контент к какому-то списку.
        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            userHolder.bind(user);
        }
        // переопределяем метод - возвоащаем количество юзеров в списке
        @Override
        public int getItemCount() {
            return users.size();
        }
    }

}
