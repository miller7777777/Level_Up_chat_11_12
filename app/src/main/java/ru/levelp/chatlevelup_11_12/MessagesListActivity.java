package ru.levelp.chatlevelup_11_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class MessagesListActivity extends AppCompatActivity {

    private RecyclerView messageListRecyclerView;
    private MessageListAdapter adapter;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);

        messageListRecyclerView = (RecyclerView) findViewById(R.id.message_list_recyclerview);
        messageListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Message> messages = new ArrayList<Message>();
    }
}
