package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.levelp.chatlevelup_11_12.storage.UserDatabase;

public class UserListFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private List<User> users;
    private Boolean visible = false;
    private UserDatabase userDB;
    private Toolbar toolbar;
    private final String TAG = "UserListActivity";

    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(UserListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Toast.makeText(v.getContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);

        userDB = new UserDatabase();

        createFakeUsers();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        adapter = new UserListAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);

        return v;
    }

    private void createFakeUsers() {

        ArrayList<User> newUsers = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            userDB.copyOrUpdate(new User(String.valueOf(i), "User " + i));
            newUsers.add(new User(String.valueOf(i), "User " + i));
            Log.d(TAG, "Добавили юзера в коллекцию: " + i);
        }

        userDB.copyOrUpdate(newUsers);
        Log.d(TAG, "Передали коллекцию юзеров в БД");


        performUsers();
    }

    private void performUsers() {

        users = userDB.getAll();
        userDB.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void onListChanged(int position){

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        visible = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDatabase secondRealmInstance = new UserDatabase();
                while (visible){
                    secondRealmInstance.copyOrUpdate(new User(UUID.randomUUID().toString(), "User " + (int) (Math.random()*1000)));

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                secondRealmInstance.close();
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        userDB.close();
        super.onDestroy();
    }
}
