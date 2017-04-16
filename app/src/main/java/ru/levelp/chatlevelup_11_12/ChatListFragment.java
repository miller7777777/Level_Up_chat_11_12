package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import ru.levelp.chatlevelup_11_12.storage.ChatDatabase;

public class ChatListFragment extends Fragment{

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
        Toast.makeText(v.getContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
    };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat_list, container, false);
//        String value = getArguments().getString("key");

        chatDB = new ChatDatabase();

        createFakeChats();

        chatListRecyclerView = (RecyclerView) v.findViewById(R.id.chat_list_recyclerview);
        chatListRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        addChatBtn = (FloatingActionButton) v.findViewById(R.id.fb_add_chat);

        adapter = new ChatListAdapter(chats, clickListener);
        chatListRecyclerView.setAdapter(adapter);



        addChatBtn.setOnClickListener(v1 -> {
//            chats.add(new Chat("Вася", "кто-то"));
            adapter.notifyDataSetChanged();
        });

        Collections.sort(chats, Chat.compare);



        return v;
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
                ChatDatabase sRIn = new ChatDatabase();
                while (visible){
                    sRIn.copyOrUpdate(new Chat(UUID.randomUUID().toString().substring(0, 10), "Chat " + (int) (Math.random()*1000)));
//                    String s = String.valueOf(chatDB.getAll().size());
//                    Log.d("ChatDB", s);


                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                sRIn.close();
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        chatDB.close();
        super.onDestroy();
    }
}
