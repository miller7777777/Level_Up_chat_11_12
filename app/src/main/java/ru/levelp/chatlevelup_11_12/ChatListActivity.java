package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.levelp.chatlevelup_11_12.storage.ChatDatabase;

import static android.os.Build.VERSION_CODES.N;

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView chatListRecyclerView;
    private ChatListAdapter adapter;
    private List<Chat> chats;
    private ChatDatabase chatDB;
    private Boolean visible = false;
    FloatingActionButton addChatBtn;
    private final String TAG = "ChatListActivity";
    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(ChatListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Log.d(ChatListAdapter.class.getSimpleName(), new Date(System.currentTimeMillis()).toString());
        Toast.makeText(ChatListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        chatDB = new ChatDatabase();

        chatListRecyclerView = (RecyclerView) findViewById(R.id.chat_list_recyclerview);
        chatListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addChatBtn = (FloatingActionButton) findViewById(R.id.fb_add_chat);


//        chats= new ArrayList<>();
//
//
//            chats.add(new Chat("Вася", "Петя"));
//            chats.add(new Chat("Вася", "Саша Грей"));
//            chats.add(new Chat("Вася", "Милена Лисицина"));
//            chats.add(new Chat("Вася", "Angelica"));
//            chats.add(new Chat("Вася", "Sheri Vi"));
//            chats.add(new Chat("Вася", "Goldie"));
//            chats.add(new Chat("Вася", "Gina Gerson"));
//            chats.add(new Chat("Вася", "Victoria Tiffany"));
//            chats.add(new Chat("Вася", "Ngiap"));
//            chats.add(new Chat("Вася", "Ksusha"));
//            chats.add(new Chat("Вася", "Дед"));
//            chats.add(new Chat("Вася", "Виталий"));
//            chats.add(new Chat("Вася", "Валентин"));
//            chats.add(new Chat("Вася", "Август"));
//            chats.add(new Chat("Вася", "Иван"));
//        chatDB.copyOrUpdate(new Chat("Василий", "Киса"));

//        chatDB.copyOrUpdate(chats);
//        chatDB.getAll().getClass().toString();
//        List<Chat> chatNew = chatDB.getAll();
//        Log.d(TAG, chatNew.getClass().toString());
//        chats = null;
//        chats = chatDB.getAll();
//        Log.d(TAG, chats.getClass().toString());
//

        createFakeChats();

        for (Chat chat : chats) {
            Log.d(TAG, chat.getParticipant());
        }


        addChatBtn.setOnClickListener(v -> {
//            chats.add(new Chat("Вася", "кто-то"));
            adapter.notifyDataSetChanged();
        });

        Collections.sort(chats, Chat.compare);

        adapter = new ChatListAdapter(chats, clickListener);
        chatListRecyclerView.setAdapter(adapter);
    }

    private void createFakeChats() {
        List newChats= new ArrayList<>();


        newChats.add(new Chat("Вася", "Петя"));
        newChats.add(new Chat("Вася", "Саша Грей"));
        newChats.add(new Chat("Вася", "Милена Лисицина"));
        newChats.add(new Chat("Вася", "Angelica"));
        newChats.add(new Chat("Вася", "Sheri Vi"));
        newChats.add(new Chat("Вася", "Goldie"));
        newChats.add(new Chat("Вася", "Gina Gerson"));
        newChats.add(new Chat("Вася", "Victoria Tiffany"));
        newChats.add(new Chat("Вася", "Ngiap"));
        newChats.add(new Chat("Вася", "Ksusha"));
        newChats.add(new Chat("Вася", "Дед"));
        newChats.add(new Chat("Вася", "Виталий"));
        newChats.add(new Chat("Вася", "Валентин"));
        newChats.add(new Chat("Вася", "Август"));
        newChats.add(new Chat("Вася", "Иван"));

        chatDB.copyOrUpdate(newChats);

        performChats();

    }

    private void performChats() {

        chats = chatDB.getAll();

        chatDB.addChangeListener(element -> {
            if(adapter != null){

                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onlistChanged(int position){
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        visible = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatDatabase secondRealmInstance = new ChatDatabase();
                while (visible){
                    secondRealmInstance.copyOrUpdate(new Chat(UUID.randomUUID().toString().substring(0, 10), UUID.randomUUID().toString().substring(0, 10)));
//                    String s = String.valueOf(chatDB.getAll().size());
//                    Log.d("ChatDB", s);
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                secondRealmInstance.close();
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        chatDB.close();
        super.onDestroy();
    }
}
