package ru.levelp.chatlevelup_11_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static android.os.Build.VERSION_CODES.M;

public class MessagesListActivity extends AppCompatActivity {

    private RecyclerView messageListRecyclerView;
    private MessageListAdapter adapter;
    private ArrayList<Message> messages;
    private OnListItemClickListener clickListener = (v, position) -> {
        Log.d(MessageListAdapter.class.getSimpleName(), "Clicked pos: " + position);
        Log.d(MessageListAdapter.class.getSimpleName(), new Date(System.currentTimeMillis()).toString());
        Toast.makeText(MessagesListActivity.this, "Clicked " + position, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        Log.e(MessagesListActivity.class.getSimpleName(), "Draw screen");

        messageListRecyclerView = (RecyclerView) findViewById(R.id.message_list_recyclerview);
        Log.e(MessagesListActivity.class.getSimpleName(), "Define RecyclerView");

        messageListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.e(MessagesListActivity.class.getSimpleName(), "Set up RecyclerView LayoutManager");

        ArrayList<Message> messages = new ArrayList<Message>();


        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "В фоне работает FoneService.java, который, в отдельном потоке, каждые 15 секунд делает запрос на сервер."));
        messages.add(new Message("Miller777", "Victoria Tiffany", "луашоа цлаощфыза выылфооа ылвщ ФВЩЗловщ"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "ЛЫРВФпр гфыиипыос вригва"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "уаорфыалфжьбмси офысо мслывв"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "лывомщ"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "всатфалофзб"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "алофуащздфуаб"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "валыоащфла"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "ваьофал шрашфиа шраш"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "вфыьатшфоа уарфшар уар"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аофрашф ушрагф угаргфыра"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "афоарфш фашрфша фа фша"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аофшщао фцавшфра ацшфа"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "вафыьроа фуашфра афа"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "фаво аафйуа  афа ф"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "аьыфлоа афушуооа па фпаф"));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "афыало афф афаш афф"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "афаол афа а фаф а"));
        messages.add(new Message("Miller777", "Victoria Tiffany", "афваш аф ашфышуа п аппауфаупупцкпиым "));
        messages.add(new Message("Victoria Tiffany", "Victoria Tiffany", "ал памышпг пып щыплолыз п"));

        Log.e(MessagesListActivity.class.getSimpleName(), "Collection full");
        Log.d(MessagesListActivity.class.getSimpleName(), "Collection full");


        Collections.sort(messages, Message.compare);

        Log.e(MessagesListActivity.class.getSimpleName(), "Collection sorted");


        adapter = new MessageListAdapter(messages, clickListener);
        Log.e(MessagesListActivity.class.getSimpleName(), "Adapter created");

        messageListRecyclerView.setAdapter(adapter);

        Log.e(MessagesListActivity.class.getSimpleName(), "Adapter assigned to Activity");

    }
}
