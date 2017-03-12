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

public class ChatListActivity extends AppCompatActivity {

    private RecyclerView chatListRecyclerView;
    private ChatListAdapter adapter;
    private ArrayList<Chat> chats;
    FloatingActionButton addChatBtn;
    private OnListItemClickListener clickListener = new OnListItemClickListener() {
        @Override
        public void OnClick(View v, int position) {
            Log.d(ChatListAdapter.class.getSimpleName(), "Clicked pos: " + position);
            Log.d(ChatListAdapter.class.getSimpleName(), new Date(System.currentTimeMillis()).toString());
            Toast.makeText(ChatListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        chatListRecyclerView = (RecyclerView) findViewById(R.id.chat_list_recyclerview);
        chatListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addChatBtn = (FloatingActionButton) findViewById(R.id.fb_add_chat);


        ArrayList<Chat> chats= new ArrayList<>();


            chats.add(new Chat("Вася", "Петя"));
            chats.add(new Chat("Вася", "Саша Грей"));
            chats.add(new Chat("Вася", "Милена Лисицина"));
            chats.add(new Chat("Вася", "Angelica"));
            chats.add(new Chat("Вася", "Sheri Vi"));
            chats.add(new Chat("Вася", "Goldie"));
            chats.add(new Chat("Вася", "Gina Gerson"));
            chats.add(new Chat("Вася", "Victoria Tiffany"));
            chats.add(new Chat("Вася", "Ngiap"));
            chats.add(new Chat("Вася", "Ksusha"));
            chats.add(new Chat("Вася", "Дед"));
            chats.add(new Chat("Вася", "Виталий"));
            chats.add(new Chat("Вася", "Валентин"));
            chats.add(new Chat("Вася", "Август"));
            chats.add(new Chat("Вася", "Иван"));

        addChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chats.add(new Chat("Вася", "кто-то"));
                adapter.notifyDataSetChanged();
            }
        });

        Collections.sort(chats, Chat.compare);
//        for(Chat chat: chats)
//            System.out.println(chat);




        adapter = new ChatListAdapter(chats, clickListener);
        chatListRecyclerView.setAdapter(adapter);

    }
}
