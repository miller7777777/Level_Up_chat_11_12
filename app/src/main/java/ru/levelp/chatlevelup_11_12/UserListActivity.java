package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.levelp.chatlevelup_11_12.storage.UserDatabase;




public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private List<User> users;
        private Handler handler = new Handler();
    private Boolean visible = false;
//    private Realm realm;
    private UserDatabase userDB;
    private Toolbar toolbar;
    private final String TAG = "UserListActivity";

    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(UserListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Toast.makeText(UserListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_example);

        userDB = new UserDatabase();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setTitle("UserList");

        createFakeUsers();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserListAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
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

//        Костыль. Юзеры должны выхватываться из БД.
//        users = newUsers;
//        users = userDB.getAll();
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
    protected void onStart() {
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
    protected void onDestroy() {
        userDB.close();
        super.onDestroy();
    }
}
