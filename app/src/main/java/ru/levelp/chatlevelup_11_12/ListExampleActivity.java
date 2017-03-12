package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class ListExampleActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ListExampleAdapter adapter;
    private ArrayList<User> users;
//    private Handler handler = new Handler();
//    private Boolean visible = false;

    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(ListExampleAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Toast.makeText(ListExampleActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_example);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setTitle("Title");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            users.add(new User("User " + i, "Description " + 1));
//            users.add(new User("User "));
        }

        adapter = new ListExampleAdapter(users, clickListener);
        recyclerView.setAdapter(adapter);
    }

//    public void onListChanged(int position){
//
////        adapter.notifyDataSetChanged();
//        adapter.notifyItemInserted(position);
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        visible = true;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while (visible){
//                    users.add(new User("User " + users.size()));
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            onListChanged(users.size() - 1);
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//    }
//
//    @Override
//    protected void onStop() {
//        visible = false;
//        super.onStop();
//    }
}
