package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static ru.levelp.chatlevelup_11_12.R.id.toolbar;


public class UserListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private List<User> users;
//    private Handler handler = new Handler();
//    private Boolean visible = false;
    private Realm realm;
    private Toolbar toolbar;

    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(UserListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Toast.makeText(UserListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_example);
        realm = Realm.getDefaultInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setTitle("Title");

        createFakeUsers();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserListAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
    }

    private void createFakeUsers() {
//        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 0; i < 10; i++) {
            realm.copyToRealmOrUpdate(new User(String.valueOf(i), "User " + i));
        }
        realm.commitTransaction();
//        realm.close();
    }

    private void performUsers(){
//        Realm realm = Realm.getDefaultInstance();

        users = realm.where(User.class).findAll();


//        realm.close();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
